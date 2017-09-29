import { App } from 'ionic-angular';
import { Component } from '@angular/core';

import { AuthProvider } from '../../providers/auth';

import { HomePage } from '../../pages/home/home';
import { UserProfilePage } from '../../pages/user-profile/user-profile';

@Component({
  selector: 'menu-connected',
  templateUrl: 'menu-connected.html'
})
export class MenuConnectedComponent {

    constructor(private nav: App, private authProvider: AuthProvider) {}

    home() {
        this.nav.getActiveNavs()[0].popToRoot();
    }

    createRun() {
        this.nav.getActiveNavs()[0].push(HomePage);
    }

    viewMyRuns() {
        this.nav.getActiveNavs()[0].push(HomePage);
    }

    myAccount() {
        this.nav.getActiveNavs()[0].push(UserProfilePage);
    }

    logout() {
        this.authProvider.logout();
        this.nav.getActiveNavs()[0].popToRoot();
    }

    about() {
        this.nav.getActiveNavs()[0].push(HomePage);
    }

}
