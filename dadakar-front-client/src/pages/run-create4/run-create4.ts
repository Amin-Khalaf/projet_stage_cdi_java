import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NavController, NavParams, LoadingController, ToastController } from 'ionic-angular';

import { LocalDate, LocalTime, Duration } from 'js-joda';

import { RunPriceService } from '../../services/runprice.service';
import { RunService } from '../../services/run.service';

import { Run } from '../../models/run.model';
import { SubRun } from '../../models/subrun.model';
import { WayPoint } from '../../models/waypoint.model';
import { Toll } from '../../models/toll.model';
import { Address } from '../../models/address.model';

@Component({
  selector: 'page-run-create4',
  templateUrl: 'run-create4.html',
})
export class RunCreate4Page implements OnInit {
  runValues: any;
  totalPrice = 0;
  formIsValid = true;

  constructor(public navCtrl: NavController, public navParams: NavParams, private priceService: RunPriceService, private loadingCtrl: LoadingController,
    private toastCtrl: ToastController, private runService: RunService) {
  }

  ngOnInit() {
    const loader = this.loadingCtrl.create({
      content: 'Recherche des prix de base ...'
    });
    loader.present();
    this.runValues = this.navParams.data;
    // get the base prices
    let power = this.runValues.vehicle.power;
    this.priceService.findByPower(power).subscribe(
      data => {
        if (data) {
          // set base price on go trip
          for (let address of this.runValues.addresses) {
            address.price = {
              min: Math.round(address.distance * data.minPrice / 1000 + address.toll / 4),
              price: Math.round(address.distance * data.startingPrice / 1000 + address.toll / 4),
              max: Math.round(address.distance * data.maxPrice / 1000 + address.toll / 4)
            };
          }
          this.getTotalPrice();
        } else {
          const toast = this.toastCtrl.create({
            message: 'Impossible de récupérer les prix de bases ...',
            duration: 2000,
            position: 'top'
          });
          toast.present();
        }
        loader.dismiss();
      });
  }

  getTotalPrice() {
    this.totalPrice = 0;
    this.formIsValid = true;
    for (let address of this.runValues.addresses) {
      address.price.price = Number(address.price.price);
      this.totalPrice += address.price.price;
      this.formIsValid = this.formIsValid && (address.price.price >= address.price.min) && (address.price.price <= address.price.max);
    }
    // console.log(this.totalPrice);
    // console.log(this.formIsValid);
  }

  validateRun(form: NgForm) {
    // console.log(this.runValues);
    // create and save go trip
    const loader = this.loadingCtrl.create({
      content: 'Enregistrement en cours'
    });
    loader.present();

    // copy prices to back trip if exists
    if (this.runValues.backAddresses != null) {
      for (let i = 1; i < this.runValues.addresses.length; i++) {
        this.runValues.backAddresses[this.runValues.backAddresses.length - i].price = this.runValues.addresses[i].price;
      }
    }

    // prepare address, waypoint and tolls infos for each step
    for (let i = 0; i < this.runValues.addresses.length; i++) {
      let address: Address = {
        district: this.runValues.addresses[i].district,
        town: this.runValues.addresses[i].town
      };
      let waypoint: WayPoint = {
        meetingPoint: this.runValues.addresses[i].place,
        address: address
      };
      this.runValues.addresses[i].waypoint = waypoint;
      if (this.runValues.addresses[i].toll > 0) {
        let toll: Toll = {
          name: this.runValues.addresses[i - 1].town + ',' + this.runValues.addresses[i - 1].district
          + '-' + this.runValues.addresses[i].town + ',' + this.runValues.addresses[i].district,
          price: this.runValues.addresses[i].toll
        };
        this.runValues.addresses[i].tollEntity = toll;
      }
    }
    // create run
    let run: Run = {
      driver: this.runValues.user,
      vehicleId: this.runValues.vehicle.vehicleId,
      subRuns: [],
      luggageType: this.runValues.luggage,
      cancelled: false
    }

    // create a subRun from each address (except last one that can't be a starting point) to next one
    for (let i = 0; i < this.runValues.addresses.length - 1; i++) {
      // awfull management of switch between datetime and localdatetime to avoid zone adjustment
      let startDateStr: string = this.runValues.addresses[i].dateTime.toString().substr(0, 10);
      let startTimeStr: string = this.runValues.addresses[i].dateTime.toString().substr(11, 5);
      let endDateStr: string = this.runValues.addresses[i + 1].dateTime.toString().substr(0, 10);
      let endTimeStr: string = this.runValues.addresses[i + 1].dateTime.toString().substr(11, 5);
      let durationSeconds = Number(this.runValues.flexibility.toString().substr(0, 2)) * 3600 + Number(this.runValues.flexibility.toString().substr(3, 2)) * 60;
      let subRun: SubRun = {
        flexibility: Duration.ofSeconds(durationSeconds),
        startPlace: this.runValues.addresses[i].waypoint,
        endPlace: this.runValues.addresses[i + 1].waypoint,
        startDate: LocalDate.parse(startDateStr),
        startTime: LocalTime.parse(startTimeStr),
        estimatedEndDate: LocalDate.parse(endDateStr),
        estimatedEndTime: LocalTime.parse(endTimeStr),
        availableSeats: this.runValues.nbPassengers,
        startingPoints: [],
        tolls: [],
        price: this.runValues.addresses[i + 1].price.price
      };
      // add starting points : all startplace from begining of run to current startplace
      for (let j = 0; j <= i; j++) {
        subRun.startingPoints.push(this.runValues.addresses[j].waypoint);
      }
      // add toll if exists
      if (this.runValues.addresses[i + 1].toll > 0) {
        subRun.tolls.push(this.runValues.addresses[i + 1].tollEntity)
      }
      // add subrun to run
      run.subRuns.push(subRun)
    }

    // save Run
    // console.log(run);
    this.runService.add(run).subscribe((result) => {
      // console.log(result);

      // save back run if exists
      if (this.runValues.backAddresses != null) {
        let goRunId = result.runId;
        // prepare address, waypoint and tolls infos for each step
        for (let i = 0; i < this.runValues.backAddresses.length; i++) {
          let address: Address = {
            district: this.runValues.backAddresses[i].district,
            town: this.runValues.backAddresses[i].town
          };
          let waypoint: WayPoint = {
            meetingPoint: this.runValues.backAddresses[i].place,
            address: address
          };
          this.runValues.backAddresses[i].waypoint = waypoint;
          let toll: Toll = {
            name: this.runValues.addresses[i - 1].town + ',' + this.runValues.addresses[i - 1].district
            + '-' + this.runValues.addresses[i].town + ',' + this.runValues.addresses[i].district,
            price: this.runValues.backAddresses[i].toll
          };
          this.runValues.backAddresses[i].tollEntity = toll;
        }
        // create run
        let run: Run = {
          driver: this.runValues.user,
          vehicleId: this.runValues.vehicle.vehicleId,
          subRuns: [],
          luggageType: this.runValues.luggage,
          cancelled: false
        }

        // create a subRun from each address (except last one that can't be a starting point) to next one
        for (let i = 0; i < this.runValues.backAddresses.length - 1; i++) {
          // awfull management of switch between datetime and localdatetime to avoid zone adjustment
          let startDateStr: string = this.runValues.backAddresses[i].dateTime.toString().substr(0, 10);
          let startTimeStr: string = this.runValues.backAddresses[i].dateTime.toString().substr(11, 5);
          let endDateStr: string = this.runValues.backAddresses[i + 1].dateTime.toString().substr(0, 10);
          let endTimeStr: string = this.runValues.backAddresses[i + 1].dateTime.toString().substr(11, 5);
          let durationSeconds = Number(this.runValues.flexibility.toString().substr(0, 2)) * 3600 + Number(this.runValues.flexibility.toString().substr(3, 2)) * 60;
          let subRun: SubRun = {
            flexibility: Duration.ofSeconds(durationSeconds),
            startPlace: this.runValues.backAddresses[i].waypoint,
            endPlace: this.runValues.backAddresses[i + 1].waypoint,
            startDate: LocalDate.parse(startDateStr),
            startTime: LocalTime.parse(startTimeStr),
            estimatedEndDate: LocalDate.parse(endDateStr),
            estimatedEndTime: LocalTime.parse(endTimeStr),
            availableSeats: this.runValues.nbPassengers,
            startingPoints: [],
            tolls: [],
            price: this.runValues.backAddresses[i + 1].price.price
          };
          // add starting points : all startplace from begining of run to current startplace
          for (let j = 0; j <= i; j++) {
            subRun.startingPoints.push(this.runValues.backAddresses[j].waypoint);
          }
          // add toll if exists
          if (this.runValues.backAddresses[i + 1].toll > 0) {
            subRun.tolls.push(this.runValues.backAddresses[i + 1].tollEntity)
          }
          // add subrun to run
          run.subRuns.push(subRun)
        }

        // save Run
        // console.log(run);
        this.runService.add(run).subscribe((result) => {
          // console.log(result);
          const toast = this.toastCtrl.create({
            message: 'Trajets enregistrés.',
            duration: 2000,
            position: 'top'
          });
          loader.dismiss();
          toast.present();
          this.navCtrl.popToRoot();
        }
          , err => {
            // console.log(err);
            // remove the go run
            this.runService.del(goRunId);
            const toast = this.toastCtrl.create({
              message: 'Erreur lors de l\'enregistrement du trajet retour.',
              duration: 2000,
              position: 'top'
            })
            loader.dismiss();
            toast.present();
          });
      } else {
        // no back trip, go back to rootPage
        const toast = this.toastCtrl.create({
          message: 'Trajet enregistré.',
          duration: 2000,
          position: 'top'
        });
        loader.dismiss();
        toast.present();
        this.navCtrl.popToRoot();
      }
    }
      , err => {
        // console.log(err);
        const toast = this.toastCtrl.create({
          message: 'Erreur lors de l\'enregistrement du trajet.',
          duration: 2000,
          position: 'top'
        });
        loader.dismiss();
        toast.present();
      });
  }

}
