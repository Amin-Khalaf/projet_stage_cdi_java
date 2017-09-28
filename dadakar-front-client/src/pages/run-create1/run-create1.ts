import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NavController, NavParams } from 'ionic-angular';

@Component({
  selector: 'page-run-create1',
  templateUrl: 'run-create1.html',
})
export class RunCreate1Page {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  onGoToStep2(form: NgForm){
    
  }

}
