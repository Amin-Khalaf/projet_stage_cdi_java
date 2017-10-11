import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NavController, NavParams } from 'ionic-angular';

import { RunCreate4Page } from '../run-create4/run-create4';

@Component({
  selector: 'page-run-create3',
  templateUrl: 'run-create3.html',
})
export class RunCreate3Page implements OnInit {
  runValues: any;
  formIsValid = true;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ngOnInit() {
    this.runValues = this.navParams.data;
  }

  onGoToStep4(form: NgForm) {
    // console.log(this.runValues);
    // duplicate toll price on back trip
    if (this.runValues.backAddresses != null) {
      for (let i = 1; i < this.runValues.addresses.length; i++) {
        this.runValues.backAddresses[this.runValues.backAddresses.length - i].toll = this.runValues.addresses[i].toll;
      }
    }
    this.navCtrl.push(RunCreate4Page, this.runValues);
  }

}
