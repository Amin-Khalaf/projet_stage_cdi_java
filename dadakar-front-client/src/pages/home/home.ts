import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

import { RunCreate1Page } from '../run-create1/run-create1';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

    runCreate1 = RunCreate1Page;

  constructor(public navCtrl: NavController) {

  }

  onGoToCreateRun(){
    this.navCtrl.push(this.runCreate1);
  }

}
