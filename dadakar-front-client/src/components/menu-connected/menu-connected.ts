import { App, MenuController } from 'ionic-angular';
import { Component } from '@angular/core';

import { AuthProvider } from '../../providers/auth';

import { HomePage } from '../../pages/home/home';
import { ManageRunsPage } from '../../pages/manage-runs/manage-runs';
import { UserProfilePage } from '../../pages/user-profile/user-profile';
import { RunCreate1Page } from '../../pages/run-create1/run-create1';
import { AboutPage } from "../../pages/about/about";

@Component({
  selector: 'menu-connected',
  templateUrl: 'menu-connected.html'
})
export class MenuConnectedComponent {

  constructor(private nav: App, private authProvider: AuthProvider, private menuCtrl: MenuController) { }

  home() {
    //        this.nav.getActiveNavs()[0].push(HomePage);
    this.nav.getActiveNavs()[0].popToRoot();
    this.menuCtrl.close();
  }

  createRun() {
    this.nav.getActiveNavs()[0].push(RunCreate1Page);
    this.menuCtrl.close();
  }

  viewMyRuns() {
    this.nav.getActiveNavs()[0].push(ManageRunsPage);
    this.menuCtrl.close();
  }

  myAccount() {
    this.nav.getActiveNavs()[0].push(UserProfilePage);
    this.menuCtrl.close();
  }

  logout() {
    this.authProvider.logout();
    this.nav.getActiveNavs()[0].popToRoot();
    this.menuCtrl.close();
  }

  about() {
    this.nav.getActiveNavs()[0].push(AboutPage);
    this.menuCtrl.close();
  }

}
