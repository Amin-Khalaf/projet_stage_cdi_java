import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { NavController, NavParams, LoadingController, ToastController } from 'ionic-angular';

import { Vehicle } from '../../models/vehicle.model';

import { RunCreate3Page } from '../run-create3/run-create3';

declare var google;

@Component({
  selector: 'page-run-create2',
  templateUrl: 'run-create2.html',
})
export class RunCreate2Page implements OnInit {
  formRunCreate2: FormGroup;
  runValues: any;
  minBackDate: string;
  luggageTypes = [{ value: 'PETIT', text: 'Petit' }, { value: 'MOYEN', text: 'Moyen' }, { value: 'GRAND', text: 'Grand' }];
  formIsValid: false;
  userVehicles: Vehicle[];

  constructor(public navCtrl: NavController, public navParams: NavParams, private loadingCtrl: LoadingController, private toastCtrl: ToastController) {
  }

  ngOnInit() {
    this.runValues = this.navParams.data;
    // set min date time for back trip
    this.minBackDate = this.runValues.addresses[this.runValues.addresses.length - 1].dateTime;
    // extract vehicles from user data
    this.userVehicles = this.runValues.user.vehicles;
    // setup form
    let isRoundTrip = false;
    let startDateBack = null;
    let vehicles = null;
    let nbPassengers = 1;
    let luggage = null;
    let flexibility = '00:00';
    this.formRunCreate2 = new FormGroup({
      'isRoundTrip': new FormControl(isRoundTrip),
      'startDateBack': new FormControl(startDateBack),
      'vehicles': new FormControl(vehicles),
      'nbPassengers': new FormControl(nbPassengers, Validators.required),
      'luggage': new FormControl(luggage, Validators.required),
      'flexibility': new FormControl(flexibility, Validators.required)
    });
  }

  validate(values: any) {
    this.formIsValid = (values.isRoundTrip == false || (values.isRoundTrip && values.startDateBack))
      && values.vehicles && values.nbPassengers && values.luggage && values.flexibility;
  }

  onGoToStep3() {
    this.runValues.isRoundTrip = this.formRunCreate2.value.isRoundTrip;
    for (let vehicle of this.runValues.user.vehicles) {
      if (vehicle.vehicleId == this.formRunCreate2.value.vehicles)
        this.runValues.vehicle = vehicle;
    }
    this.runValues.nbPassengers = this.formRunCreate2.value.nbPassengers;
    this.runValues.luggage = this.formRunCreate2.value.luggage;
    this.runValues.flexibility = this.formRunCreate2.value.flexibility;
    if (this.runValues.isRoundTrip) {
      // calculate the back trip
      const loader = this.loadingCtrl.create({
        content: 'Calcul du trajet retour ...'
      });
      loader.present();

      let backAddresses = [];
      for (let i = this.runValues.addresses.length - 1; i >= 0; i--) {
        let newAddress = {
          dateTime: null,
          distance: 0,
          district: this.runValues.addresses[i].district,
          town: this.runValues.addresses[i].town,
          place: this.runValues.addresses[i].place,
          toll: 0,
          price: 0
        };
        backAddresses.push(newAddress);
      }
      this.runValues.backAddresses = backAddresses;
      // build data to be sent to google map direction service
      let directionData: any;
      if (this.runValues.backAddresses.length > 2) {
        // we have some waypoints
        directionData = { origin: '', destination: '', waypoints: [], travelMode: google.maps.TravelMode['DRIVING'] };
        for (let i = 1; i < this.runValues.addresses.length - 1; i++) {
          if (i == this.runValues.backAddresses.length - 2) {
            // last waypoint, no | at the end
            directionData.waypoints.push({ location: this.runValues.backAddresses[i].district + ',' + this.runValues.backAddresses[i].town + ', Sénégal', stopover: true });
          } else {
            directionData.waypoints.push({ location: this.runValues.backAddresses[i].district + ',' + this.runValues.backAddresses[i].town + ', Sénégal|', stopover: true });
          }
        }
      } else {
        // no waypoints
        directionData = { origin: '', destination: '', travelMode: google.maps.TravelMode['DRIVING'] };
      }
      // set origin, destination values
      directionData.origin = this.runValues.backAddresses[0].district + ',' + this.runValues.backAddresses[0].town + ', Sénégal';
      directionData.destination = this.runValues.backAddresses[this.runValues.backAddresses.length - 1].district + ',' + this.runValues.backAddresses[this.runValues.backAddresses.length - 1].town + ', Sénégal';
      // create the direction service
      let directionsService = new google.maps.DirectionsService;
      // call the service
      directionsService.route(directionData, (res, status) => {
        loader.dismiss();

        if (status == google.maps.DirectionsStatus.OK) {
          let stepDateTime = new Date(this.formRunCreate2.value.startDateBack);
          // get the list of datetime and distance of each step (start -> (step1 ->)( ... ->) end)
          // google send back durations stocked as timestamp
          // set start datetime for first step and distance 0
          this.runValues.backAddresses[0].dateTime = new Date(stepDateTime).toISOString();
          this.runValues.backAddresses[0].distance = 0;
          for (let i = 0; i < res.routes[0].legs.length; i++) {
            // set arrival datetime to next point
            stepDateTime.setTime(stepDateTime.getTime() + res.routes[0].legs[i].duration.value * 1000);
            this.runValues.backAddresses[i + 1].dateTime = new Date(stepDateTime).toISOString();
            // set distance to next point
            this.runValues.backAddresses[i + 1].distance = res.routes[0].legs[i].distance.value;
          }
          // console.log(this.runValues);
          // go to step 3
          this.navCtrl.push(RunCreate3Page, this.runValues);
        } else {
          // console.log(status);
          const toast = this.toastCtrl.create({
            message: 'Erreur lors de l\'appel du service de calcul de trajet ...',
            duration: 1500,
            position: 'top'
          });
          toast.present();
        }
      });

    } else {
      // no back trip to calculate, just go to step 3
      this.navCtrl.push(RunCreate3Page, this.runValues);
    }
  }

}
