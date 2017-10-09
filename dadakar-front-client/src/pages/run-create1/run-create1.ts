import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, FormArray } from '@angular/forms';
import { NavController, NavParams, LoadingController, ToastController } from 'ionic-angular';
import { LocalDate } from 'js-joda';

import { Account } from '../../models/account.model';
import { User } from '../../models/user.model';

import { UserService } from '../../services/user.service';

import { AuthProvider } from '../../providers/auth';

import { RunCreate2Page } from '../run-create2/run-create2';

declare var google;

@Component({
  selector: 'page-run-create1',
  templateUrl: 'run-create1.html',
})
export class RunCreate1Page implements OnInit {
  formRunCreate1: FormGroup;
  tomorrow: string;
  formIsValid = false;
  account: Account;
  user: User = { vehicles: [] };

  constructor(public navCtrl: NavController, public navParams: NavParams,
    private fb: FormBuilder, private loadingCtrl: LoadingController, private toastCtrl: ToastController, private userService: UserService,
    private authProvider: AuthProvider) {
    let loading = this.loadingCtrl.create({
      spinner: 'bubbles',
      content: 'Chargement en cours...'
    });
    loading.present();
    this.authProvider.authUser.subscribe(jwt => {
      if (jwt) {
        // console.log(jwt);
        this.account = jwt.accountDTO;
        // console.log(this.account);
        this.userService.findByAccountId(this.account.accountId).subscribe(data => {
          this.user = data;
          // console.log(this.user);
          loading.dismiss();
        });
      }
    });
  }

  ngOnInit() {
    this.tomorrow = LocalDate.now().plusDays(1).toString() + 'T00:00Z'
    let startAddress = this.fb.group({
      town: null,
      district: null,
      place: null
    });
    let endAddress = this.fb.group({
      town: null,
      district: null,
      place: null
    });
    let places = new FormArray([]);
    let startDateTrip = new FormControl(null);
    this.formRunCreate1 = this.fb.group({
      startAddress: startAddress,
      endAddress: endAddress,
      places: places,
      startDateTrip: startDateTrip
    });
  }

  onAddPlace() {
    (<FormArray>this.formRunCreate1.get('places')).push(this.fb.group({
      town: null,
      district: null,
      place: null
    }));
  }

  removePlace(index: number) {
    (<FormArray>this.formRunCreate1.get('places')).removeAt(index);
    this.validateForm();
  }

  validateForm() {
    this.formIsValid = this.formRunCreate1.get('startAddress').value.town != null
      && this.formRunCreate1.get('startAddress').value.district != null
      && this.formRunCreate1.get('startAddress').value.place != null
      && this.formRunCreate1.get('endAddress').value.town != null
      && this.formRunCreate1.get('endAddress').value.district != null
      && this.formRunCreate1.get('endAddress').value.place != null
      && this.formRunCreate1.get('startDateTrip').value != null;
    if (this.formIsValid) {
      let places = (<FormArray>this.formRunCreate1.get('places')).value;
      if (places) {
        for (let place of places) {
          this.formIsValid = place.town != null && place.district != null && place.place != null;
          if (!this.formIsValid) {
            break;
          }
        }
      }
    }
  }

  getDirectionsInfo(runValues: any): any {

  }

  onGoToStep2() {
    const loader = this.loadingCtrl.create({
      content: 'Calcul du trajet ...'
    });
    loader.present();
    // store form data
    let runValues = { user: this.user, addresses: [this.formRunCreate1.get('startAddress').value], startDateTrip: this.formRunCreate1.get('startDateTrip').value };
    let places = (<FormArray>this.formRunCreate1.get('places')).value;
    if (places != null && places.length > 0) {
      for (let place of places) {
        runValues.addresses.push(place);
      }
    }
    runValues.addresses.push(this.formRunCreate1.get('endAddress').value);

    // build data to be sent to google map direction service
    let directionData: any;
    if (runValues.addresses.length > 2) {
      // we have some waypoints
      directionData = { origin: '', destination: '', waypoints: [], travelMode: google.maps.TravelMode['DRIVING'] };
      for (let i = 1; i < runValues.addresses.length - 1; i++) {
        if (i == runValues.addresses.length - 2) {
          // last waypoint, no | at the end
          directionData.waypoints.push({ location: runValues.addresses[i].district + ',' + runValues.addresses[i].town + ', Sénégal', stopover: true });
        } else {
          directionData.waypoints.push({ location: runValues.addresses[i].district + ',' + runValues.addresses[i].town + ', Sénégal|', stopover: true });
        }
      }
    } else {
      // no waypoints
      directionData = { origin: '', destination: '', travelMode: google.maps.TravelMode['DRIVING'] };
    }
    // set origin, destination values
    directionData.origin = runValues.addresses[0].district + ',' + runValues.addresses[0].town + ', Sénégal';
    directionData.destination = runValues.addresses[runValues.addresses.length - 1].district + ',' + runValues.addresses[runValues.addresses.length - 1].town + ', Sénégal';
    // create the direction service
    let directionsService = new google.maps.DirectionsService;
    // call the service
    directionsService.route(directionData, (res, status) => {
      loader.dismiss();

      if (status == google.maps.DirectionsStatus.OK) {
        let stepDateTime = new Date(runValues.startDateTrip);
        // get the list of datetime and distance of each step (start -> (step1 ->)( ... ->) end)
        // google send back durations stocked as timestamp
        // set start datetime for first step and distance 0
        runValues.addresses[0].dateTime = stepDateTime.toISOString();
        runValues.addresses[0].distance = 0;
        for (let i = 0; i < res.routes[0].legs.length; i++) {
          // set arrival datetime at step end  point
          stepDateTime.setTime(stepDateTime.getTime() + res.routes[0].legs[i].duration.value * 1000);
          runValues.addresses[i + 1].dateTime = stepDateTime.toISOString();
          // set distance at step end point
          runValues.addresses[i + 1].distance = res.routes[0].legs[i].distance.value;
        }
        console.log(runValues);
        // go to step 2
        this.navCtrl.push(RunCreate2Page, runValues);
      } else {
        console.log(status);
        const toast = this.toastCtrl.create({
          message: 'Erreur lors de l\'appel du service de calcul de trajet ...',
          duration: 1500,
          position: 'top'
        });
        toast.present();
      }
    });
  }

}
