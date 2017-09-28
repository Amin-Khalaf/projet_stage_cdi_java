import { Component, ViewChild } from '@angular/core';
import { LoadingController, MenuController, NavController, ToastController} from 'ionic-angular';
import { NgModel } from '@angular/forms';
import * as sha1 from 'js-sha1';

import { AuthProvider } from '../../providers/auth';
import { UserSignupPage } from '../../pages/user-signup/user-signup';

import { Account } from '../../models/account.model';
import { Role } from '../../models/enums/role.model';

@Component({
  selector: 'page-signup',
  templateUrl: 'signup.html',
})
export class SignupPage {

    @ViewChild('username')
    usernameModel: NgModel;
    private account: Account;

    constructor(private readonly authProvider: AuthProvider, private readonly loadingCtrl: LoadingController, private menu: MenuController, private readonly navCtrl: NavController, private readonly toastCtrl: ToastController) {
        this.menu.close();
    }

    handleError(error: any) {
        if(error.status === 409) {
            this.showSuccesToast('EXISTS');
        } else {
            let message = "Erreur innatendue";
            const toast = this.toastCtrl.create({
                message,
                duration: 5000,
                position: 'bottom'
            });

            toast.present();

        }

    }

    private showSuccesToast(jwt) {
        if(jwt !== 'EXISTS') {
            this.navCtrl.push(UserSignupPage);
        } else {
            let message = '';
            const toast = this.toastCtrl.create({
                message,
                duration: 3000,
                position: 'bottom'
            });

            toast.present();

            this.usernameModel.control.setErrors({'usernameTaken': true});

        }
    }

    signup(values: any) {
        let loading = this.loadingCtrl.create({
            spinner: 'bubbles',
            content:"Enregistrement..."
        });

        loading.present();

        this.account = {
            username: values.username,
            password: sha1(values.password),
            role: Role.USER,
            banned: false,
            deleted: false
        };

        this.authProvider
            .signup(this.account)
            .finally(() => loading.dismiss())
            .subscribe((jwt) => this.showSuccesToast(jwt), err => this.handleError(err));
    }

}
