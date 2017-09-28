import { Component } from '@angular/core';
import { LoadingController, MenuController } from 'ionic-angular';

import { Account } from '../../models/account.model';
import { User } from '../../models/user.model';

import { AuthProvider } from '../../providers/auth';

import { ImgService } from '../../services/image.service';
import { UserService } from '../../services/user.service';

@Component({
    selector: 'page-user-profile',
    templateUrl: 'user-profile.html',
})
export class UserProfilePage {

    account: Account;
    activeMenu: string;
    connected: boolean = true;
    drivingLicence: string;
    idCard: string;
    photo: string;
    user: User;
    users: User[] = [];

    constructor(private authProvider: AuthProvider, private imgService: ImgService, private loader: LoadingController, private menu: MenuController, private userService: UserService) {
        this.menu.close();
        this.authProvider.authUser.subscribe(jwt => {
            if(jwt) {
                this.menuConnectedActive();
                this.account = jwt.accountDTO;
                this.getUser();
            } else {
                this.menuNotConnectedActive();
            }
        });
    }

    menuConnectedActive() {
        this.activeMenu = 'menu-connected';
        this.menu.enable(false, 'menu-not-connected');
        this.menu.enable(true, 'menu-connected');
    }

    menuNotConnectedActive() {
        this.activeMenu = 'menu-not-connected';
        this.menu.enable(true, 'menu-not-connected');
        this.menu.enable(false, 'menu-connected');
    }

    delete() {
        alert("Etes vous sur de vouloir supprimer votre compte ?");
    }

    getImage(user: User): void {
        this.imgService.findByFileName(user.photo).subscribe(data => {
            this.photo = 'data:image/jpeg;base64,' + data;
        });
        this.imgService.findByFileName(user.idCard).subscribe(data => {
            this.idCard = 'data:image/jpeg;base64,' + data;
        });
        this.imgService.findByFileName(user.drivingLicence).subscribe(data => {
            this.drivingLicence = 'data:image/jpeg;base64,' + data;
        })
    }

    getUser(): void {

        let loading = this.loader.create({
            spinner: 'bubbles',
            content: 'Chargement en cours...'
        });

        loading.present();

        this.userService.findByAccountId(this.account.accountId).finally(() => loading.dismiss()).subscribe(data => {
            this.user = data;
            this.users.push(this.user);
            this.getImage(this.users[0]);
        });

    }

}
