import { Component, ViewChild } from '@angular/core';
import { Platform, NavController, MenuController } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { AuthProvider } from '../providers/auth';

import { HomePage } from '../pages/home/home';
import { UserSignupPage } from '../pages/user-signup/user-signup';
import { HomeConnectedPage } from '../pages/home-connected/home-connected';
import { RunCreate1Page } from '../pages/run-create1/run-create1';

import { UserService } from '../services/user.service';

import { User } from '../models/user.model';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {

    rootPage:any = null;
    user: User = null;
    home = HomeConnectedPage;
    runcreate = RunCreate1Page;
    @ViewChild('content') menuNav: NavController;

    constructor(private authProvider: AuthProvider, private platform: Platform, private statusBar: StatusBar, private splashScreen: SplashScreen, private userService: UserService, private menuCtrl: MenuController) {
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
                    if(this.user) {
                        this.rootPage = HomePage;
                    } else {
                        this.rootPage = UserSignupPage;
                    }
                });
            } else {
                this.rootPage = HomePage;
            }
        });

        this.authProvider.checkLogin();

    }

    onLoad(page: any){
      this.menuNav.push(page);
      this.menuCtrl.close();
    }

}
