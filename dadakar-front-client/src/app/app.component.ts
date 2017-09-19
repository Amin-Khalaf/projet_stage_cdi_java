import { App } from 'ionic-angular';
import { Component, ViewChild } from '@angular/core';
import { LoadingController, Platform, ToastController } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import * as sha1 from 'js-sha1';

import { AuthProvider } from '../providers/auth';

import { HomeConnectedPage } from '../pages/home-connected/home-connected';
import { HomePage } from '../pages/home/home';
import { LoginPage } from '../pages/login/login';
import { SignupPage } from '../pages/signup/signup';
import { UserSignupPage } from '../pages/user-signup/user-signup'

import { UserService } from '../services/user.service';

import { Account } from '../models/account.model';
import { User } from '../models/user.model';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {

    account: Account = null;
    loginIconIOS: string = 'ios-arrow-forward';
    loginIconMD: string = 'md-arrow-dropright';
    rootPage:any = null;
    showLogin: boolean = false;
    user: User = null;

    constructor(private appCtrl: App, private authProvider: AuthProvider, private readonly loadingCtrl: LoadingController, private platform: Platform, private statusBar: StatusBar, private splashScreen: SplashScreen, private readonly toastCtrl: ToastController, private userService: UserService) {
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
                        this.rootPage = HomeConnectedPage;
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

    handleError(error: any) {
        let message: string;
        if(error.status && error.status === 401) {
            message = 'Le nom d\'utilisateur et/ou le mot de passe sont incorrects';
        } else {
            message = `Erreur innatendue : ${error.statusText}`;
        }

        const toast = this.toastCtrl.create({
            message,
            duration: 5000,
            position: 'bottom'
        });

        toast.present();

    }

    login(values: any) {
        let loading = this.loadingCtrl.create({
            spinner: 'bubbles',
            content: 'connexion...'
        });

        loading.present();

        this.account = {
            username: values.username,
            password: sha1(values.password)
        }

        this.authProvider
        .login(this.account)
        .finally(() => loading.dismiss())
        .subscribe(() => {}, err => this.handleError(err));

    }

    signup() {
        this.appCtrl.getActiveNavs()[0].push(SignupPage);
    }

    subLogin() {
        if(this.showLogin) {
            this.loginIconIOS = 'ios-arrow-forward';
            this.loginIconMD = 'md-arrow-dropright';
        } else {
            this.loginIconIOS = 'ios-arrow-down';
            this.loginIconMD = 'md-arrow-dropdown';
        }
        this.showLogin = !this.showLogin;
    }

}
