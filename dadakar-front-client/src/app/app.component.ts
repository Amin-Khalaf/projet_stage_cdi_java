import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { AuthProvider } from '../providers/auth';
import { HomePage } from '../pages/home/home';
import { LoginPage } from '../pages/login/login';
import { UserSignupPage } from '../pages/user-signup/user-signup'

import { UserService } from '../services/user.service';

import { User } from '../models/user.model';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {

  rootPage:any = null;
  user: User = null;

  constructor(private authProvider: AuthProvider, private platform: Platform, private statusBar: StatusBar, private splashScreen: SplashScreen, private userService: UserService) {
    this.platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      this.statusBar.styleDefault();
      this.splashScreen.hide();
    });

    this.authProvider.authUser.subscribe(jwt => {
        if(jwt) {
            this.userService.findByAccountId(jwt.accountDTO.accountId).subscribe(data => {
                this.user = data;
                if(this.user !== null) {
                    this.rootPage = HomePage;
                } else {
                    this.rootPage = UserSignupPage;
                }
            });
        } else {
            this.rootPage = LoginPage;
        }
    });

    this.authProvider.checkLogin();

  }
}
