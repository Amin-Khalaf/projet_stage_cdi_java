import { Component } from '@angular/core';
import { NavController, NavParams, MenuController } from 'ionic-angular';

@Component({
  selector: 'page-about',
  templateUrl: 'about.html',
})
export class AboutPage {

  constructor(public navCtrl: NavController, public navParams: NavParams, private menuCtrl: MenuController) {
    this.menuCtrl.close();
  }

}
