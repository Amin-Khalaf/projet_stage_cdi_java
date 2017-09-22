import { Component } from '@angular/core';
import { MenuController, NavController } from 'ionic-angular';

import { RunCreate1Page } from '../run-create1/run-create1';

@Component({
  selector: 'page-home-connected',
  templateUrl: 'home-connected.html',
})
export class HomeConnectedPage {

  runCreate1 = RunCreate1Page;

  constructor(private menuCtrl: MenuController, private navCtrl: NavController) {
      this.menuCtrl.close();
  }
  onGoToCreateRun(){
    this.navCtrl.push(this.runCreate1);
  }

}
