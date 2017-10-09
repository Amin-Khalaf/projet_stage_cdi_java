import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { NavController, NavParams } from 'ionic-angular';

@Component({
  selector: 'page-run-create3',
  templateUrl: 'run-create3.html',
})
export class RunCreate3Page implements OnInit {
  formRunCreate3: FormGroup;
  runValues: any;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ngOnInit() {
    this.runValues = this.navParams.data;
    // récupération des estimations de km et horaires de google maps

  }

}
