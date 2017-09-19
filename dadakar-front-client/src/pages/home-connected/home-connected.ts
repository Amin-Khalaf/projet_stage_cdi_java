import { Component } from '@angular/core';
import { MenuController } from 'ionic-angular';

@Component({
  selector: 'page-home-connected',
  templateUrl: 'home-connected.html',
})
export class HomeConnectedPage {

  constructor(private menuCtrl: MenuController) {
      this.menuCtrl.close();
  }

}
