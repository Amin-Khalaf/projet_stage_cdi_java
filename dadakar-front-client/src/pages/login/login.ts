import { Component } from '@angular/core';
import { LoadingController, NavController, ToastController } from "ionic-angular";
import * as sha1 from 'js-sha1';

import { AuthProvider } from '../../providers/auth';
import { SignupPage } from '../signup/signup';

import { Account } from '../../models/account.model'

@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {

    account: Account = null;

    constructor(private readonly authProvider: AuthProvider, private readonly loadingCtrl: LoadingController, private readonly navCtrl: NavController, private readonly toastCtrl: ToastController){}

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
        this.navCtrl.push(SignupPage);
    }

}
