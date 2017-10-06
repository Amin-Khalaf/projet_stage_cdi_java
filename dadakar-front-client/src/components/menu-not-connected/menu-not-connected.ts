import { App } from 'ionic-angular';
import { Component } from '@angular/core';
import { LoadingController, ToastController } from "ionic-angular";
import { NgForm } from '@angular/forms';
import * as sha1 from 'js-sha1';

import { AuthProvider } from '../../providers/auth';

import { SignupPage } from '../../pages/signup/signup';

import { Account } from '../../models/account.model'

@Component({
  selector: 'menu-not-connected',
  templateUrl: 'menu-not-connected.html'
})
export class MenuNotConnectedComponent {

    account: Account = null;
    loginIconIOS: string = 'ios-arrow-forward';
    loginIconMD: string = 'md-arrow-dropright';
    rootPage:any = null;
    showLogin: boolean = false;

    constructor(private appCtrl: App, private readonly authProvider: AuthProvider, private readonly loadingCtrl: LoadingController, private readonly toastCtrl: ToastController){

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

        login(values: any, form: NgForm) {
            let loading = this.loadingCtrl.create({
                spinner: 'bubbles',
                content: 'connexion...'
            });

            loading.present();

            this.account = {
                username: values.username,
                password: sha1(values.password)
            }

            form.controls['password'].reset();

            this.authProvider
            .login(this.account)
            .finally(() => loading.dismiss())
            .subscribe(() => {this.showLogin = !this.showLogin; }, err => this.handleError(err));

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
