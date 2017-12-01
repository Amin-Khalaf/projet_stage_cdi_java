import { App } from 'ionic-angular';
import { Component } from '@angular/core';

import { AuthProvider } from '../../providers/auth';

import { UserProfilePage } from '../../pages/user-profile/user-profile';
import { AboutPage } from '../../pages/about/about';

@Component({
  selector: 'menu-banned',
  templateUrl: 'menu-banned.html'
})
export class MenuBannedComponent {

  constructor(private nav: App, private authProvider: AuthProvider) { }

  home() {
    this.nav.getActiveNavs()[0].popToRoot();
  }

  myAccount() {
    this.nav.getActiveNavs()[0].push(UserProfilePage);
  }

  logout() {
    this.authProvider.logout();
    this.nav.getActiveNavs()[0].popToRoot();
  }

  about() {
    this.nav.getActiveNavs()[0].push(AboutPage);
  }

}
