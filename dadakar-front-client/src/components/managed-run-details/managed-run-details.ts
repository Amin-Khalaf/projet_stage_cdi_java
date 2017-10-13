import { Component } from '@angular/core';
import { ActionSheetController, ModalController, NavController, NavParams, Platform, ViewController } from 'ionic-angular';
import { LocalDate, LocalTime } from 'js-joda';

import { UserProfileComponent } from '../../components/user-profile/user-profile';
import { UserRatingComponent } from '../../components/user-rating/user-rating';

import { Rating } from '../../models/rating.model';
import { Run } from '../../models/run.model';
import { Search } from '../../models/search.model';
import { SubRun } from '../../models/subrun.model';
import { User } from '../../models/user.model';
import { Vehicle } from '../../models/vehicle.model';

import { ResState } from '../../models/enums/resstate.model';

import { ViewRatingsPage } from '../../pages/view-ratings/view-ratings';

import { ImgService } from '../../services/image.service';
import { RunService } from '../../services/run.service';

@Component({
    selector: 'managed-run-details',
    templateUrl: 'managed-run-details.html'
})

export class ManagedRunDetailsComponent {

    connected: boolean;
    isDriver: boolean;
    nbRatings: number;
    note: number = 0;
    passengers: any[][] = [[]];
    passengerId: string = '';
    passengersPhotos: string[][] = [[]];
    photo: string;
    photos: string[] = [];
    photoVehicule: string;
    rateFirst: boolean;
    run: Run;
    search: Search;
    subRuns: SubRun[] = [];
    userId: string;
    userState: string[][] = [[]];
    vehicle: Vehicle;

    constructor(private actionSheet: ActionSheetController, private imgService: ImgService, private modal: ModalController, private nav: NavController, private params: NavParams, private platform: Platform, private runService: RunService, private view: ViewController) {
        this.connected = this.params.get('connected');
        this.userId = this.params.get('id');
        this.run = this.params.get('run');
        this.nbRatings = this.run.driver.ratings.length;
        this.subRuns = this.run.subRuns;
        this.isDriver = (this.userId == this.run.driver.accountId);
        this.getAvatar(this.run.driver.photo, false);
        this.getRatings();
        this.setState();
        this.getPassengersPhotosAndRxState();
    }

    dismiss(data: any): void {
        this.view.dismiss(data);
    }

    driverClick() {
        //stop event if user want to view rates
        if(this.rateFirst) {
            this.rateFirst = false;
        } else {
            // interact only if user is not the driver
            if(!this.isDriver) {
                let buttons: any[] = [];
                let buttonCancel = {text: 'Annuler', role: 'cancel', icon: !this.platform.is('ios') ? 'close' : '', handler: () => {}};
                let buttonProfile = {text: 'Voir le profil', icon: !this.platform.is('ios') ? 'person' : '', handler: () => {
                        this.viewProfile(this.run.driver);
                    }
                };
                let buttonRate = {text: 'noter l\'utilisateur', icon: !this.platform.is('ios') ? 'star-outline' : '', handler: () => {
                        this.rate(this.run.driver);
                    }
                };
                buttons.push(buttonRate);
                buttons.push(buttonProfile);
                buttons.push(buttonCancel);
                let action = this.actionSheet.create({
                    buttons: buttons
                });
                action.present();
            }
        }
    }

    getAvatar(fileName: string, isVehicle: boolean): void {
        if(this.connected && fileName != '') {
            this.imgService.findByFileName(fileName).subscribe(data => {
                if(data) {
                    if(isVehicle) {
                        this.photoVehicule = 'data:image/jpeg;base64,' + data;
                    } else {
                        this.photo = 'data:image/jpeg;base64,' + data;
                    }
                } else {
                    if(isVehicle) {
                        this.photoVehicule = '/assets/img/vehicule.png'
                    } else {
                        this.photo = '/assets/img/avatar.png';
                    }
                }
            });
        } else {
            if(isVehicle) {
                this.photoVehicule = '/assets/img/vehicule.png'
            } else {
                this.photo = '/assets/img/avatar.png';
            }
        }
    }

    getAvatars(fileName: string, i: number, j: number): void {
        this.passengersPhotos = [[]];
        this.passengersPhotos[i] = [];
        if(this.connected && fileName != '') {
            this.imgService.findByFileName(fileName).subscribe(data => {
                if(data) {
                    this.passengersPhotos[i][j] = 'data:image/jpeg;base64,' + data;
                } else {
                    this.passengersPhotos[i][j] = '/assets/img/avatar.png';
                }
            });
        } else {
            this.passengersPhotos[i][j] = '/assets/img/avatar.png';
        }
    }

    getSubRunPassengers(i: number): any[] {
        let passenger = {
            passenger: null,
            passengerIndex: 0,
            nb: 0,
            state: ''
        }
        this.passengerId = '';
        let temp: any[] = [];
        for(let j = 0, l = this.run.subRuns[i].passengers.length; j < l; j++) {
            let condition: boolean;
            this.isDriver ? condition = this.run.subRuns[i].passengers[j].reservationState == ResState.PENDING || this.run.subRuns[i].passengers[j].reservationState == ResState.ACCEPTED :
            condition = true;
            if(condition) {
                if(this.run.subRuns[i].passengers[j].user.accountId == this.passengerId) {
                    passenger.nb++
                } else {
                    if(this.passengerId) temp.push(passenger);
                    this.passengerId = this.run.subRuns[i].passengers[j].user.accountId;
                    let state: string;
                    let resState: ResState = this.run.subRuns[i].passengers[j].reservationState;
                    (resState == ResState.PENDING) ? state = 'blue' : (resState == ResState.REFUSED) ? state = 'red' : (resState == ResState.ACCEPTED) ? state = 'green' : state = 'purple';
                    passenger = {
                        passenger: this.run.subRuns[i].passengers[j],
                        passengerIndex: j,
                        nb: 1,
                        state: state
                    }
                }
            }
        }
        if(passenger.nb) temp.push(passenger);
        return temp;
    }

    getPassengersPhotosAndRxState(): void {
        let subRuns: SubRun[] = this.run.subRuns;
        for(let i = 0, k = subRuns.length; i < k; i++) {
            this.userState[i] = [];
            if(subRuns[i].passengers != null) {
                for(let j = 0, l = subRuns[i].passengers.length; j < l; j++) {
                    this.getAvatars(subRuns[i].passengers[j].user.photo, i, j);
                }
            }
        }
    }

    getRatings(): void {
        this.note = 0;
        let ratingValue: number = 0;
        let ratings: Rating[] = this.run.driver.ratings;
        this.nbRatings = ratings.length;
        for(let i = 0, j = ratings.length; i < j; i++) {
            ratingValue += ratings[i].value;
        }
        this.note = ratingValue / ratings.length;
    }

    passengerClick(subRunIndex: number, passenger: any) {
        let buttons: any[] = [];
        let buttonAccept = {text: 'Accepter', icon: !this.platform.is('ios') ? 'checkmark-circle' : '',handler: () => {
                for(let i = 0, j = this.run.subRuns.length; i < j; i++) {
                    for(let k = 0, l = this.run.subRuns[i].passengers.length; k < l; k++) {
                        if(this.run.subRuns[i].passengers[k] == passenger.passenger) {
                            this.run.subRuns[i].availableSeats-= passenger.nb;
                            for(let m = 0; m < passenger.nb; m++) this.run.subRuns[i].passengers[k + m].reservationState = ResState.ACCEPTED;
                        }
                    }
                }
                this.runService.update(this.run).subscribe(() => {this.setState(); this.getPassengersPhotosAndRxState();});
            }
        };
        let buttonCancel = {text: 'Annuler', role: 'cancel', icon: !this.platform.is('ios') ? 'close' : '', handler: () => {}};
        let buttonCancelReservation = {text: 'Annuler la reservation', icon: !this.platform.is('ios') ? 'close-circle' : '', handler: () => {
                for(let i = 0, j = this.run.subRuns.length; i < j; i++) {
                    for(let k = 0, l = this.run.subRuns[i].passengers.length; k < l; k++) {
                        if(this.run.subRuns[i].passengers[k] == passenger.passenger) {
                            // if run still reserved, free seats
                            if(this.run.subRuns[i].passengers[k].reservationState == ResState.ACCEPTED) this.run.subRuns[i].availableSeats += passenger.nb;
                            // change state to CANCELLED
                            for(let m = 0; m < passenger.nb; m++) this.run.subRuns[i].passengers[k + m].reservationState = ResState.CANCELLED;
                        }
                    }
                }
                // save the run
                this.runService.update(this.run).subscribe(() => {this.setState(); this.getPassengersPhotosAndRxState();});
            }
        };
        let buttonProfile = {text: 'Voir le profil', icon: !this.platform.is('ios') ? 'person' : '', handler: () => {
                this.viewProfile(this.run.subRuns[subRunIndex].passengers[passenger.passengerIndex].user);
            }
        };
        let buttonRate = {text: 'noter l\'utilisateur', icon: !this.platform.is('ios') ? 'star-outline' : '', handler: () => {
                this.rate(this.run.subRuns[subRunIndex].passengers[passenger.passengerIndex].user);
            }
        };
        let buttonRefuse = {text: 'Refuser', icon: !this.platform.is('ios') ? 'close-circle' : '', handler: () => {
                for(let i = 0; i < passenger.nb; i++) this.run.subRuns[subRunIndex].passengers[passenger.passengerIndex + i].reservationState = ResState.REFUSED;
                this.runService.update(this.run).subscribe(() => {this.setState(); this.getPassengersPhotosAndRxState();});
            }
        };
        let buttonRestoreReservation = {text: 'Reserver à nouveau', icon: !this.platform.is('ios') ? 'refresh-circle' : '', handler: () => {
                for(let i = 0, j = this.run.subRuns.length; i < j; i++) {
                    for(let k = 0, l = this.run.subRuns[i].passengers.length; k < l; k++) {
                        if(this.run.subRuns[i].passengers[k] == passenger.passenger) {
                            // change state to PENDING
                            for(let m = 0; m < passenger.nb; m++) this.run.subRuns[i].passengers[k + m].reservationState = ResState.PENDING;
                        }
                    }
                }
                // save the run
                this.runService.update(this.run).subscribe(() => {this.setState(); this.getPassengersPhotosAndRxState();});
            }
        };
        buttons.push(buttonCancel);
        if(this.isDriver) {
            let availableSeats: number = -1;
            for(let i = 0, j = this.run.subRuns.length; i < j; i++) {
                for(let k = 0, l = this.run.subRuns[i].passengers.length; k < l; k++) {
                    if(this.run.subRuns[i].passengers[i] == passenger.passenger) {
                        // check availableSeats
                        if(availableSeats == -1) availableSeats = this.run.subRuns[i].availableSeats;
                        if(this.run.subRuns[i].availableSeats < availableSeats) availableSeats = this.run.subRuns[i].availableSeats;
                    }
                }
            }
            if(passenger.nb <= availableSeats && (this.run.subRuns[subRunIndex].startDate > LocalDate.now() ||(this.run.subRuns[subRunIndex].startDate == LocalDate.now() && this.run.subRuns[subRunIndex].startTime > LocalTime.now().plusMinutes(30)))){
                //driver and available seats ok to reserve and run not passed
                buttons.push(buttonAccept);
                buttons.push(buttonRefuse);
                buttons.push(buttonProfile);
            } else {
                if(this.run.subRuns[subRunIndex].startDate > LocalDate.now() || (this.run.subRuns[subRunIndex].startDate == LocalDate.now() && this.run.subRuns[subRunIndex].startTime > LocalTime.now().plusMinutes(30))) {
                    //driver and run not passed but available seats not ok to reserve
                    buttons.push(buttonRefuse);
                    buttons.push(buttonProfile);
                } else {
                    //driver and run passed
                    buttons.push(buttonRate);
                    buttons.push(buttonProfile);
                }
            }
            let action = this.actionSheet.create({
                buttons: buttons
            });
            action.present();
        } else {
            if(this.run.subRuns[subRunIndex].startDate > LocalDate.now() || (this.run.subRuns[subRunIndex].startDate == LocalDate.now() && this.run.subRuns[subRunIndex].startTime > LocalTime.now().plusMinutes(30))) {
                //passenger and run not passed
                if(this.userId == this.run.subRuns[subRunIndex].passengers[passenger.passengerIndex].user.accountId) {
                    //user is this passenger can cancel if not, can re-book if cancel
                    if(this.run.subRuns[subRunIndex].passengers[passenger.passengerIndex].reservationState != ResState.CANCELLED) buttons.push(buttonCancelReservation);
                    else buttons.push(buttonRestoreReservation);
                } else {
                    //user is not the passenger so he could only see the passenger profile
                    buttons.push(buttonProfile);
                }
                let action = this.actionSheet.create({
                    buttons: buttons
                });
                action.present();
            } else {
                //passenger and run passed : only passenger whos not the active user can interact
                if(this.userId != this.run.subRuns[subRunIndex].passengers[passenger.passengerIndex].user.accountId) {
                    buttons.push(buttonRate);
                    buttons.push(buttonProfile);
                    let action = this.actionSheet.create({
                        buttons: buttons
                    });
                    action.present();
                }
            }
        }
        // buttons.push(buttonRate);
        // buttons.push(buttonRefuse);
        // buttons.push(buttonAccept);
        // buttons.push(buttonProfile);
        // buttons.push(buttonCancelReservation);
        // buttons.push(buttonRestoreReservation);
        // let action = this.actionSheet.create({
        //     buttons: buttons
        // });
        // action.present();
    }

    rate(user: User) {
        let rate = this.modal.create(UserRatingComponent, {
            user: user,
            raterAccountId: this.userId
        });
        rate.onDidDismiss(() => {
            this.runService.findById(this.run.runId).subscribe(data => {
                this.run = data;
                this.nbRatings = this.run.driver.ratings.length;
                this.subRuns = this.run.subRuns;
                this.getRatings();
            });
        });
        rate.present();
    }

    setState() {
        let subRuns: SubRun[] = this.run.subRuns;
        for(let i = 0, k = subRuns.length; i < k; i++) {
            if(subRuns[i].passengers != null) {
                for(let j = 0, l = subRuns[i].passengers.length; j < l; j++) {
                    (subRuns[i].passengers[j].reservationState.toString() == 'PENDING' || subRuns[i].passengers[j].reservationState.toString() == ResState.PENDING.toString()) ? subRuns[i].passengers[j].reservationState = ResState.PENDING :
                    (subRuns[i].passengers[j].reservationState.toString() == 'ACCEPTED' || subRuns[i].passengers[j].reservationState.toString() == ResState.ACCEPTED.toString()) ? subRuns[i].passengers[j].reservationState = ResState.ACCEPTED :
                    (subRuns[i].passengers[j].reservationState.toString() == 'REFUSED' || subRuns[i].passengers[j].reservationState.toString() == ResState.REFUSED.toString()) ? subRuns[i].passengers[j].reservationState = ResState.REFUSED :
                    (subRuns[i].passengers[j].reservationState.toString() == 'CANCELLED'  || subRuns[i].passengers[j].reservationState.toString() == ResState.CANCELLED.toString()) ? subRuns[i].passengers[j].reservationState = ResState.CANCELLED :
                    subRuns[i].passengers[j].reservationState = ResState.RUN_CANCELLED;
                }
            }
        }
    }

    viewProfile(user: User): void {
        let profile = this.modal.create(UserProfileComponent, {
            connected: this.connected,
            user: user
        });
        profile.present();
    }

    viewRates() {
        this.rateFirst = true;
        this.nav.push(ViewRatingsPage, {
            user: this.run.driver
        });
    }

}
