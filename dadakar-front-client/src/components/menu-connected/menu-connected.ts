import { App } from 'ionic-angular';
import { Component } from '@angular/core';

import { AuthProvider } from '../../providers/auth';

import { HomePage } from '../../pages/home/home';

@Component({
  selector: 'menu-connected',
  templateUrl: 'menu-connected.html'
})
export class MenuConnectedComponent {

    constructor(private appCtrl: App, private authProvider: AuthProvider) {}

    home() {
        this.appCtrl.getActiveNavs()[0].push(HomePage);
    }

    createRun() {
        this.appCtrl.getActiveNavs()[0].push(HomePage);
    }

    viewMyRuns() {
        this.appCtrl.getActiveNavs()[0].push(HomePage);
    }

    myAccount() {
        this.appCtrl.getActiveNavs()[0].push(HomePage);
    }

    logout() {
        this.authProvider.logout();
    }

    about() {
        this.appCtrl.getActiveNavs()[0].push(HomePage);
    }

}
