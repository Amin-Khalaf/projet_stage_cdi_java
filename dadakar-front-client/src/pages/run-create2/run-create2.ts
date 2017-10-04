import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { NavController, NavParams } from 'ionic-angular';
import { LocalDate, LocalDateTime } from 'js-joda';

import { Luggage } from '../../models/enums/luggage.model';

@Component({
  selector: 'page-run-create2',
  templateUrl: 'run-create2.html',
})
export class RunCreate2Page implements OnInit {
  formRunCreate2: FormGroup;
  runValues: any;
  tomorrow: string;
  minBackDate: string;
  maxDate: string;
  luggageTypes = [{ value: Luggage.PETIT, text: 'Petit' }, { value: Luggage.MOYEN, text: 'Moyen' }, { value: Luggage.GRAND, text: 'Grand' }];
  formIsValid: false;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.minBackDate = this.tomorrow = LocalDate.now().plusDays(1).toString() + 'T00:00Z';
    this.maxDate = LocalDate.now().plusMonths(6).toString();
  }

  ngOnInit() {
    this.runValues = this.navParams.data;
    let isRoundTrip = false;
    let startDateTrip = null;
    let startDateBack = null;
    let nbPassengers = 1;
    let luggage = null;
    let flexibility = '00:00';
    this.formRunCreate2 = new FormGroup({
      'isRoundTrip': new FormControl(isRoundTrip),
      'startDateTrip': new FormControl(startDateTrip, Validators.required),
      'startDateBack': new FormControl(startDateBack),
      'nbPassengers': new FormControl(nbPassengers, Validators.required),
      'luggage': new FormControl(luggage, Validators.required),
      'flexibility': new FormControl(flexibility, Validators.required)
    });
  }

  setBackMinDate(tripDate: FormControl) {
    if (tripDate.value) {
      this.minBackDate = LocalDateTime.of(tripDate.value.year, tripDate.value.month, tripDate.value.day, tripDate.value.hour, tripDate.value.minute).toString();
    }
  }

  validate(values: any) {
    this.formIsValid = values.startDateTrip
      && (values.isRoundTrip == false || (values.isRoundTrip && values.startDateBack && values.startDateBack > values.startDateTrip))
      && values.nbPassengers && values.luggage && values.flexibility;
  }

  onGoToStep3() {
    this.runValues.isRoundTrip = this.formRunCreate2.value.isRoundTrip;
    this.runValues.startDateTrip = this.formRunCreate2.value.startDateTrip;
    if (this.formRunCreate2.value.isRoundTrip) {
      this.runValues.startDateBack = this.formRunCreate2.value.startDateBack;
    }
    this.runValues.nbPassengers = this.formRunCreate2.value.nbPassengers;
    this.runValues.luggage = this.formRunCreate2.value.luggage;
    this.runValues.flexibility = this.formRunCreate2.value.flexibility;
    console.log(this.runValues);
  }

}
