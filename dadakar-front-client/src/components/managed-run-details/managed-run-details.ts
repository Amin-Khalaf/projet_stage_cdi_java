import { Component } from '@angular/core';
import { ActionSheetController, ModalController, NavController, NavParams, Platform, ViewController } from 'ionic-angular';
import { LocalDate, LocalTime } from 'js-joda';

import { UserProfileComponent } from '../../components/user-profile/user-profile';

import { Passenger } from '../../models/passenger.model';
import { Rating } from '../../models/rating.model';
import { Run } from '../../models/run.model';
import { Search } from '../../models/search.model';
import { SubRun } from '../../models/subrun.model';
import { User } from '../../models/user.model';
import { Vehicle } from '../../models/vehicle.model';
import { WayPoint } from '../../models/waypoint.model';

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
        this.getPassengers();
    }

    dismiss(data: any): void {
        this.view.dismiss(data);
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

    getPassengers() {
        this.passengerId = '';
        this.passengers = [[]];
        this.setState();
        let subRuns: SubRun[] = this.run.subRuns;
        for(let i = 0, k = subRuns.length; i < k; i++ ) {
            let passenger = {
                passenger: null,
                passengerIndex: 0,
                nb: 0
            }
            for(let j = 0, l = subRuns[i].passengers.length; j < l; j++) {
                console.log(this.subRuns[i].passengers[j]);
                let condition: boolean;
                this.isDriver ? condition = subRuns[i].passengers[j].reservationState == ResState.PENDING || subRuns[i].passengers[j].reservationState == ResState.ACCEPTED :
                condition = true;
                if(condition) {
                    if(this.subRuns[i].passengers[j].user.accountId == this.passengerId) {
                        passenger.nb++
                    } else {
                        if(this.passengerId) this.passengers[i].push(passenger);
                        this.passengerId = this.subRuns[i].passengers[j].user.accountId;
                        passenger = {
                            passenger: this.subRuns[i].passengers[j],
                            passengerIndex: j,
                            nb: 1
                        }
                    }
                }
            }
            this.passengers[i].push(passenger);
        }
        this.getPassengersPhotosAndRxState();
    }

    getPassengersPhotosAndRxState(): void {
        let subRuns: SubRun[] = this.run.subRuns;
        for(let i = 0, k = subRuns.length; i < k; i++) {
            for(let j = 0, l = subRuns[i].passengers.length; j < l; j++) {
                this.getAvatars(subRuns[i].passengers[j].user.photo, i, j);
                (subRuns[i].passengers[j].reservationState == ResState.PENDING) ? this.userState[i][j] = 'blue' :
                 (subRuns[i].passengers[j].reservationState == ResState.REFUSED) ? this.userState[i][j] = 'red' :
                (subRuns[i].passengers[j].reservationState == ResState.ACCEPTED) ?  this.userState[i][j] = 'green' : this.userState[i][j] = 'purple';
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
                this.run.subRuns[subRunIndex].availableSeats-= passenger.nb;
                for(let i = 0; i < passenger.nb; i++) this.run.subRuns[subRunIndex].passengers[passenger.passengerIndex + i].reservationState = ResState.ACCEPTED;
                this.runService.update(this.run).subscribe(() => this.getPassengers());
            }
        };
        let buttonCancel = {text: 'Annuler', role: 'cancel', icon: !this.platform.is('ios') ? 'close' : '', handler: () => {}};
        let buttonCancelReservation = {text: 'Annuler la reservation', icon: !this.platform.is('ios') ? 'close-circle' : '', handler: () => {
                // if run still reserved, free seats
                if(this.run.subRuns[subRunIndex].passengers[passenger.passengerIndex].reservationState == ResState.ACCEPTED) this.run.subRuns[subRunIndex].availableSeats += passenger.nb;
                // change state to CANCELLED
                for(let i = 0; i < passenger.nb; i++) this.run.subRuns[subRunIndex].passengers[passenger.passengerIndex + i].reservationState = ResState.CANCELLED;
                // save the run
                this.runService.update(this.run).subscribe(() => this.getPassengers());
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
                this.runService.update(this.run).subscribe(() => this.getPassengers());
            }
        };
        let buttonRestoreReservation = {text: 'Reserver à nouveau', icon: !this.platform.is('ios') ? 'refresh-circle' : '', handler: () => {
                // change state to PENDING
                for(let i = 0; i < passenger.nb; i++) this.run.subRuns[subRunIndex].passengers[passenger.passengerIndex + i].reservationState = ResState.PENDING;
                // save the run
                this.runService.update(this.run).subscribe(() => this.getPassengers());
            }
        };
        buttons.push(buttonCancel);
        if(this.isDriver) {
            if(passenger.nb <= this.run.subRuns[subRunIndex].availableSeats && this.run.subRuns[subRunIndex].startDate >= LocalDate.now() && this.run.subRuns[subRunIndex].startTime > LocalTime.now().plusMinutes(30)){
                //driver and available seats ok to reserve and run not passed
                buttons.push(buttonAccept);
                buttons.push(buttonRefuse);
                buttons.push(buttonProfile);
            } else {
                if(this.run.subRuns[subRunIndex].startDate >= LocalDate.now() && this.run.subRuns[subRunIndex].startTime > LocalTime.now().plusMinutes(30)) {
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
            if( this.run.subRuns[subRunIndex].startDate >= LocalDate.now() && this.run.subRuns[subRunIndex].startTime > LocalTime.now().plusMinutes(30)) {
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
    }

    rate(user: User) {
        console.log("noter : " + user);
    }

    setState() {
        let subRuns: SubRun[] = this.run.subRuns;
        for(let i = 0, k = subRuns.length; i < k; i++) {
            for(let j = 0, l = subRuns[i].passengers.length; j < l; j++) {
                (subRuns[i].passengers[j].reservationState.toString() == 'PENDING' || subRuns[i].passengers[j].reservationState.toString() == ResState.PENDING.toString()) ? subRuns[i].passengers[j].reservationState = ResState.PENDING :
                (subRuns[i].passengers[j].reservationState.toString() == 'ACCEPTED' || subRuns[i].passengers[j].reservationState.toString() == ResState.ACCEPTED.toString()) ? subRuns[i].passengers[j].reservationState = ResState.ACCEPTED :
                (subRuns[i].passengers[j].reservationState.toString() == 'REFUSED' || subRuns[i].passengers[j].reservationState.toString() == ResState.REFUSED.toString()) ? subRuns[i].passengers[j].reservationState = ResState.REFUSED :
                (subRuns[i].passengers[j].reservationState.toString() == 'CANCELLED'  || subRuns[i].passengers[j].reservationState.toString() == ResState.CANCELLED.toString()) ? subRuns[i].passengers[j].reservationState = ResState.CANCELLED :
                subRuns[i].passengers[j].reservationState = ResState.RUN_CANCELLED;
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
        this.nav.push(ViewRatingsPage, {
            user: this.run.driver
        });
    }

}
