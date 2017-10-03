import { Component, ViewChild } from '@angular/core';
import { LoadingController, MenuController, ModalController } from 'ionic-angular';


import { VehicleDetailsComponent } from '../../components/vehicle-details/vehicle-details';

import { Account } from '../../models/account.model';
import { User } from '../../models/user.model';
import { Vehicle } from '../../models/vehicle.model';

import { AuthProvider } from '../../providers/auth';

import { AccountService } from '../../services/account.service';
import { ImgService } from '../../services/image.service';
import { UserService } from '../../services/user.service';

@Component({
    selector: 'page-user-profile',
    templateUrl: 'user-profile.html',
})
export class UserProfilePage {

    @ViewChild('deleteToggle') deleteToggle: any;
    notFirst: boolean = false;

    account: Account;
    activeMenu: string;
    connected: boolean = true;
    drivingLicence: string;
    idCard: string;
    photo: string;
    photoVehicles: string[] = [];
    user: User;
    users: User[] = [];
    userVehicles: Vehicle[] = [];

    constructor(private accountService: AccountService, private authProvider: AuthProvider, private imgService: ImgService, private loader: LoadingController, private menu: MenuController, private modal: ModalController, private userService: UserService) {
        this.menu.close();
        this.authProvider.authUser.subscribe(jwt => {
            if(jwt) {
                this.account = jwt.accountDTO;
                if(this.account.banned || this.account.deleted) this.menuBannedOrDeletedActive();
                else this.menuConnectedActive();
                this.getUser();
            } else {
                this.menuNotConnectedActive();
            }
        });
    }

    menuBannedOrDeletedActive() {
        this.activeMenu = 'menu-banned';
        this.menu.enable(true, 'menu-banned');
        this.menu.enable(false, 'menu-not-connected');
        this.menu.enable(false, 'menu-connected');
    }

    menuConnectedActive() {
        this.activeMenu = 'menu-connected';
        this.menu.enable(false, 'menu-banned');
        this.menu.enable(false, 'menu-not-connected');
        this.menu.enable(true, 'menu-connected');
    }

    menuNotConnectedActive() {
        this.activeMenu = 'menu-not-connected';
        this.menu.enable(false, 'menu-banned');
        this.menu.enable(true, 'menu-not-connected');
        this.menu.enable(false, 'menu-connected');
    }

    addVehicle() {
        let vId: string = '' + (Number(this.users[0].vehicles[this.users[0].vehicles.length - 1].vehicleId) + 1);
        let vehicle: Vehicle = {
            brand: '',
            color: '',
            carRegistration: '',
            model: '',
            name: '',
            photo: '',
            power: 1,
            registrationNumber: '',
            vehicleId:vId
        };
        this.getVehicleDetails(vehicle, true);
    }

    delete() {
        if(this.notFirst) {
             this.notFirst = false;
         } else {
            if(!this.account.deleted) {
                if(confirm("Etes vous sur de vouloir supprimer votre compte ?")) {
                    this.account.deleted = true;
                    this.accountService.update(this.account).subscribe();
                    this.menuBannedOrDeletedActive();
                } else {
                    this.notFirst = true; // block the second call of delete() due to uncheck
                    this.account.deleted = false;
                    this.deleteToggle.checked = false;
                }
            } else {
                this.account.deleted = false;
                this.accountService.update(this.account).subscribe();
                this.menuConnectedActive();
            }
        }
    }

    getImage(user: User): void {
        this.imgService.findByFileName(user.photo).subscribe(data => {
            if(data) {
                this.photo = 'data:image/jpeg;base64,' + data;
            } else {
                this.photo = '/assets/img/avatar.png';
            }
        });
        this.imgService.findByFileName(user.idCard).subscribe(data => {
            if(data) {
                this.idCard = 'data:image/jpeg;base64,' + data;
            } else {
                this.photo = '/assets/img/cni.jpg';
            }
        });
        this.imgService.findByFileName(user.drivingLicence).subscribe(data => {
            if(data) {
                this.drivingLicence = 'data:image/jpeg;base64,' + data;
            } else {
                this.photo = '/assets/img/pc.jpg';
            }
        })
    }

    getPhotoVehicles(user: User) {
        let vehicles: Vehicle[] = user.vehicles;
        for(let i = 0, j = vehicles.length; i < j; i++) {
            this.imgService.findByFileName(vehicles[i].photo).subscribe(data => {
                if(data) {
                    this.photoVehicles[i] = 'data:image/jpeg;base64,' + data;
                } else {
                    this.photoVehicles[i] = '/assets/img/vehicule.png';
                }
            });
        }
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
            this.userVehicles = this.user.vehicles;
            this.getImage(this.users[0]);
            this.getPhotoVehicles(this.users[0]);
        });

    }

    getVehicleDetails(vehicle: Vehicle, isNew: boolean) {
        let profile = this.modal.create(VehicleDetailsComponent, {
            isNew: isNew,
            vehicle: vehicle
        });
        profile.onDidDismiss(data => {
            console.log(data);
        });
        profile.present();
    }

}
