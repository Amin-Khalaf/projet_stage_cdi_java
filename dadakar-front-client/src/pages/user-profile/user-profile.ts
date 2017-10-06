import { Component, ViewChild } from '@angular/core';
import { DomSanitizer, SafeStyle } from '@angular/platform-browser';
import { Camera, CameraOptions } from '@ionic-native/camera';
import { ActionSheetController, AlertController, LoadingController, MenuController, ModalController, NavController, Platform } from 'ionic-angular';

import { VehicleDetailsComponent } from '../../components/vehicle-details/vehicle-details';

import { Account } from '../../models/account.model';
import { Image } from '../../models/image.model';
import { Message } from '../../models/message.model';
import { Rating } from '../../models/rating.model';
import { User } from '../../models/user.model';
import { Vehicle } from '../../models/vehicle.model';

import { ViewRatingsPage } from '../../pages/view-ratings/view-ratings';

import { AuthProvider } from '../../providers/auth';

import { AccountService } from '../../services/account.service';
import { ImgService } from '../../services/image.service';
import { MessageService } from '../../services/message.service';
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
    badgeStyle: SafeStyle = this.sanitizer.bypassSecurityTrustStyle('top: 38px; right: 5px;');
    connected: boolean = true;
    drivingLicence: string;
    idCard: string;
    messageNotRead: number = 0;
    messages: Message[] = [];
    nbRatings: number = 0;
    note: number = 0;
    photo: string;
    photoVehicles: string[] = [];
    user: User;
    users: User[] = [];
    userVehicles: Vehicle[] = [];

    //validator
    accountIsUpdating: boolean;
    mailIsUpdating: boolean;
    nameIsUpdating: boolean;

    constructor(private accountService: AccountService, private actionSheet: ActionSheetController, private alert: AlertController, private authProvider: AuthProvider, private camera: Camera, private imgService: ImgService, private loader: LoadingController, private menu: MenuController, private msgService: MessageService, private modal: ModalController, private nav: NavController, private platform: Platform, private sanitizer: DomSanitizer, private userService: UserService) {
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

    private deleteVehicle(vehicle: Vehicle, index: number): void {
        this.user.vehicles.splice(index, 1);
        this.userService.update(this.user).subscribe(() => {
            this.getUser();
        });
    }

    private getImage(user: User): void {
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

    private getMessages(): void {
        this.messageNotRead = 0;
        this.msgService.findByReceiverId(this.user.userId).subscribe(data => {
            if(data) {
                this.messages = data;
                for(let i = 0, j = this.messages.length; i < j; i++) {
                    if(!this.messages[i].seen) this.messageNotRead++;
                }
            }
        });
    }

    private getPhotoVehicles(user: User): void {
        this.photoVehicles = [];
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

    private getRandomName(): string {
        const char: string = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        var name: string = '';
        for(let i = 0; i < 10; i++) name += char.substr(Math.random() * 62,1);
        return name + ".jpeg";
    }

    private getRatings() {
        this.note = 0;
        let ratingValue: number = 0;
        let ratings: Rating[] = this.user.ratings;
        this.nbRatings = ratings.length;
        for(let i = 0, j = ratings.length; i < j; i++) {
            ratingValue += ratings[i].value;
        }
        this.note = ratingValue / ratings.length;
    }

    private getUser(): void {
        this.users = [];
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
            this.getMessages();
            this.getRatings();
        });
    }

    private getVehicleDetails(vehicle: Vehicle, isNew: boolean, index: number): void {
        let oldPhoto: string = vehicle.photo;
        let oldCarRegistration = vehicle.carRegistration;
        let profile = this.modal.create(VehicleDetailsComponent, {
            isNew: isNew,
            vehicle: vehicle
        });
        profile.onDidDismiss(data => {
            if(data) {
                if(isNew) {
                    let photo: Image = {
                        image: data.photo,
                        name: data.vehicle.photo,
                        type: 'image/jpeg'
                    };
                    this.imgService.add(photo).subscribe();
                    let carRegistration: Image = {
                        image: data.carRegistration,
                        name: data.vehicle.carRegistration,
                        type: 'image/jpeg'
                    };
                    this.imgService.add(carRegistration).subscribe();
                    this.user.vehicles.push(data.vehicle);
                } else {
                    if(oldPhoto !== data.vehicle.photo) {
                        let photo: Image = {
                            image: data.photo,
                            name: data.vehicle.photo,
                            type: 'image/jpeg'
                        };
                        this.imgService.delete(oldPhoto).subscribe();
                        this.imgService.add(photo).subscribe();
                    }
                    if(oldCarRegistration !== data.vehicle.carRegistration) {
                        let carRegistration: Image = {
                            image: data.carRegistration,
                            name: data.vehicle.carRegistration,
                            type: 'image/jpeg'
                        };
                        this.imgService.delete(oldCarRegistration).subscribe();
                        this.imgService.add(carRegistration).subscribe();
                    }
                    this.user.vehicles[index] = data.vehicle;
                }
                this.userService.update(this.user).subscribe(() => this.getUser() );
            }
        });
        profile.present();
    }

    private menuBannedOrDeletedActive(): void {
        this.activeMenu = 'menu-banned';
        this.menu.enable(true, 'menu-banned');
        this.menu.enable(false, 'menu-not-connected');
        this.menu.enable(false, 'menu-connected');
    }

    private menuConnectedActive(): void {
        this.activeMenu = 'menu-connected';
        this.menu.enable(false, 'menu-banned');
        this.menu.enable(false, 'menu-not-connected');
        this.menu.enable(true, 'menu-connected');
    }

    private menuNotConnectedActive(): void {
        this.activeMenu = 'menu-not-connected';
        this.menu.enable(false, 'menu-banned');
        this.menu.enable(true, 'menu-not-connected');
        this.menu.enable(false, 'menu-connected');
    }

    private takePicture(sourceType: number, destination: number): void {
        // destination : 0 for idCard, 1 for drivingLicence, 2 for user
        let photo: Image;
        const option: CameraOptions = {
            destinationType: 0,
            encodingType: this.camera.EncodingType.JPEG,
            mediaType: this.camera.MediaType.PICTURE,
            quality: 100,
            sourceType: sourceType
        }
        this.camera.getPicture(option).then((value) => {
            photo = {
                image: value,
                name: this.getRandomName(),
                type: 'image/jpeg'
            }
            switch (destination) {
                case 0:
                    if(this.user.idCard) this.imgService.delete(this.user.idCard).subscribe();
                    this.imgService.add(photo).subscribe(() => {
                        this.user.idCard = photo.name;
                        this.userService.update(this.user).subscribe(() => {
                            this.getUser();
                        });
                    });
                    break;
                case 1:
                    if(this.user.drivingLicence) this.imgService.delete(this.user.drivingLicence).subscribe();
                    this.imgService.add(photo).subscribe(() => {
                        this.user.drivingLicence = photo.name;
                        this.userService.update(this.user).subscribe(() => {
                            this.getUser();
                        });
                    });
                    break;
                case 2:
                    if(this.user.photo) this.imgService.delete(this.user.photo).subscribe();
                    this.imgService.add(photo).subscribe(() => {
                        this.user.photo = photo.name;
                        this.userService.update(this.user).subscribe(() => {
                            this.getUser();
                        });
                    });
                    break;
            }
        });
    }

    private updateBadgePosition() {
        if(!this.accountIsUpdating && !this.mailIsUpdating && !this.nameIsUpdating) this.badgeStyle = this.sanitizer.bypassSecurityTrustStyle('top: 38px; right: 5px;'); // normal position
        if((this.accountIsUpdating && !this.mailIsUpdating && !this.nameIsUpdating) || (!this.accountIsUpdating && this.mailIsUpdating && !this.nameIsUpdating)) this.badgeStyle = this.sanitizer.bypassSecurityTrustStyle('top: 28px; right: 5px;'); // updating account only or mail only
        //if(!this.accountIsUpdating && this.mailIsUpdating && !this.nameIsUpdating) this.badgeStyle = 'top: 28px; right: 5px;'; // updating mail only
        if(!this.accountIsUpdating && !this.mailIsUpdating && this.nameIsUpdating) this.badgeStyle = this.sanitizer.bypassSecurityTrustStyle('top: 23px; right: 5px;'); // updating name only
        if(this.accountIsUpdating && this.mailIsUpdating && !this.nameIsUpdating) this.badgeStyle = this.sanitizer.bypassSecurityTrustStyle('top: 18px; right: 5px;'); // updating account and mail together
        if((this.accountIsUpdating && !this.mailIsUpdating && this.nameIsUpdating) || (!this.accountIsUpdating && this.mailIsUpdating && this.nameIsUpdating)) this.badgeStyle = this.sanitizer.bypassSecurityTrustStyle('top: 13px; right: 5px;'); // updating account and name together or mail and name together
        //if(!this.accountIsUpdating && this.mailIsUpdating && this.nameIsUpdating) this.badgeStyle = 'top: 13px; right: 5px;'; // updating mail and name together
        if(this.accountIsUpdating && this.mailIsUpdating && this.nameIsUpdating) this.badgeStyle = this.sanitizer.bypassSecurityTrustStyle('top: 8px; right: 5px;'); // updating all together
    }

    addVehicle(): void {
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
        this.getVehicleDetails(vehicle, true, -1);
    }

    deleteAccount(): void {
        if(this.notFirst) {
             this.notFirst = false;
         } else {
            if(!this.account.deleted) {
                let supprimer = this.alert.create({
                    title: "Suppression de compte",
                    message: "Etes vous sur de vouloir supprimer votre compte ?",
                    buttons: [
                        {
                            text: 'Supprimer',
                            handler: () => {
                                this.account.deleted = true;
                                this.accountService.update(this.account).subscribe();
                                this.menuBannedOrDeletedActive();
                            }
                        },
                        {
                            text: 'Annuler',
                            handler: () => {
                                this.notFirst = true; // block the second call of delete() due to uncheck
                                this.account.deleted = false;
                                this.deleteToggle.checked = false;
                            }
                        }
                    ]
                });
                supprimer.present();
            } else {
                this.account.deleted = false;
                this.accountService.update(this.account).subscribe();
                this.menuConnectedActive();
            }
        }
    }

    photoClick(type: number): void {
        // type : 0 for idCard, 1 for drivingLicence, 2 for user
        let actionSheetPhoto = this.actionSheet.create({
            title: (type == 0) ? "Changer de pièce d'identité" : (type == 1) ? "Changer de permis de conduire" : "Changer de photo de profil",
            cssClass: 'action-sheets-basic-page',
            buttons: [
                {
                    text: 'utiliser l\'appareil photo',
                    icon: !this.platform.is('ios') ? 'camera' : '',
                    handler: () => {
                        this.takePicture(1, type);
                    }
                },
                {
                    text: 'Utiliser une photo présente dans l\'appareil',
                    icon: !this.platform.is('ios') ? 'image' : '',
                    handler: () => {
                        this.takePicture(0, type);
                    }
                },
                {
                    text: 'Annuler',
                    role: 'cancel',
                    icon: !this.platform.is('ios') ? 'close' : '',
                    handler: () => {}
                }
            ]
        });
        actionSheetPhoto.present();
    }

    readMessages(): void {
        //TODO: this.nav.push(MsgPage);
        console.log(this.messages);
    }

    saveAccount(): void {
        this.accountIsUpdating = false;
        this.updateBadgePosition();
        this.accountService.update(this.account).subscribe();
    }

    saveMail(): void {
        this.mailIsUpdating = false;
        this.updateBadgePosition();
        this.userService.update(this.user).subscribe();
    }

    saveName(): void {
        this.nameIsUpdating = false;
        this.updateBadgePosition();
        this.userService.update(this.user).subscribe();
    }

    updateAccount(): void {
        this.accountIsUpdating = true;
        this.updateBadgePosition();
    }

    updateAccountCancel(): void {
        this.accountIsUpdating = false;
        this.updateBadgePosition();
    }

    updateMail(): void {
        this.mailIsUpdating = true;
        this.updateBadgePosition();
    }

    updateMailCancel(): void {
        this.mailIsUpdating = false;
        this.updateBadgePosition();
    }

    updateName(): void {
        this.nameIsUpdating = true;
        this.updateBadgePosition();
    }

    updateNameCancel(): void {
        this.nameIsUpdating = false;
        this.updateBadgePosition();
    }

    userVehicleClick(vehicle: Vehicle, index: number): void {
        let actionSheetVehicle = this.actionSheet.create({
            title: "Details du vehicule",
            cssClass: 'action-sheets-basic-page',
            buttons: [
                {
                    text: 'Modifier les informations',
                    icon: !this.platform.is('ios') ? 'create' : '',
                    handler: () => {
                        this.getVehicleDetails(vehicle, false, index);
                    }
                },
                {
                    text: 'Supprimer le véhicule',
                    role: 'destructive',
                    icon: !this.platform.is('ios') ? 'trash' : '',
                    handler: () => {
                        this.deleteVehicle(vehicle, index);
                    }
                },
                {
                    text: 'Annuler',
                    role: 'cancel',
                    icon: !this.platform.is('ios') ? 'close' : '',
                    handler: () => {}
                }
            ]
        });
        actionSheetVehicle.present();

    }

    viewRates() {
        if(this.nbRatings) {
            this.nav.push(ViewRatingsPage, {
                user: this.user
            });
        }
    }

}
