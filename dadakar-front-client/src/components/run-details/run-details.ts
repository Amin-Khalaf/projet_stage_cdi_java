import { Component } from '@angular/core';
import { ModalController, NavController, NavParams, ViewController } from 'ionic-angular';

import { UserProfileComponent } from '../../components/user-profile/user-profile';

import { Rating } from '../../models/rating.model';
import { Run } from '../../models/run.model';
import { Search } from '../../models/search.model';
import { SubRun } from '../../models/subrun.model';
import { User } from '../../models/user.model';
import { Vehicle } from '../../models/vehicle.model';
import { WayPoint } from '../../models/waypoint.model';

import { ViewRatingsPage } from '../../pages/view-ratings/view-ratings';

import { ImgService } from '../../services/image.service';

@Component({
    selector: 'run-details',
    templateUrl: 'run-details.html'
})

export class RunDetailsComponent {

    connected: boolean;
    nbRatings: number;
    note: number = 0;
    passengersPhotos: string[][] = [[]];
    photo: string;
    photos: string[] = [];
    photoVehicule: string;
    run: Run;
    search: Search;
    subRuns: SubRun[] = [];
    vehicle: Vehicle;
    wantedSubRuns: SubRun[] = [];

    constructor(private imgService: ImgService, private modal: ModalController, private nav: NavController, private params: NavParams, private view: ViewController) {
        this.connected = this.params.get('connected');
        this.run = this.params.get('run');
        this.search = this.params.get('search');
        this.nbRatings = this.run.driver.ratings.length;
        this.subRuns = this.run.subRuns;
        this.getAvatar(this.run.driver.photo, false);
        this.getRatings();
        this.findVehicle();
        this.findWantedSubRuns();
        this.getPassengersPhotos();
    }

    dismiss(data: any): void {
        this.view.dismiss(data);
    }

    findWantedSubRuns(): void {
        let tempStartingPoint: WayPoint;
        for(let i = 0, j = this.run.subRuns.length; i < j; i++) {
            // find first entry point
            if(this.run.subRuns[i].startPlace.address.town == this.search.startTown && this.run.subRuns[i].startPlace.address.district == this.search.startDistrict) {
                // add starting point
                this.wantedSubRuns.push(this.run.subRuns[i]);
                // if there is only one subrun => break otherwhise change startPlace
                if(this.run.subRuns[i].endPlace.address.town == this.search.endTown && this.run.subRuns[i].endPlace.address.district == this.search.endDistrict) {
                    break;
                } else {
                    tempStartingPoint = this.run.subRuns[i].endPlace;
                }
            } else if(tempStartingPoint != null && tempStartingPoint.address.town == this.run.subRuns[i].startPlace.address.town && tempStartingPoint.address.district == this.run.subRuns[i].startPlace.address.district) {
                //add step
                this.wantedSubRuns.push(this.run.subRuns[i]);
                // if it is the last subrun => break otherwhise change startPlace
                if(this.run.subRuns[i].endPlace.address.town == this.search.endTown && this.run.subRuns[i].endPlace.address.district == this.search.endDistrict) {
                    break;
                } else {
                    tempStartingPoint = this.run.subRuns[i].endPlace;
                }
            }
        }
    }

    findVehicle(): void {
        let vehicles: Vehicle[] = this.run.driver.vehicles;
        for(let i = 0, j = vehicles.length; i < j; i++) {
            if(vehicles[i].vehicleId == this.run.vehicleId) {
                this.vehicle = vehicles[i];
                break;
            }
        }
        this.getAvatar(this.vehicle.photo, true);
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
        console.log("pp");
        console.log(this.passengersPhotos);
        console.log("pp(i)");
        console.log(this.passengersPhotos[i]);
        if(typeof(this.passengersPhotos[i]) === 'undefined') this.passengersPhotos[i] = [];
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
        console.log("i: " + i + ", j : " + j);
        console.log(this.passengersPhotos);
    }

    getPassengersPhotos(): void {
        let subRuns = this.run.subRuns;
        for(let i = 0, k = subRuns.length; i < k; i++) {
            for(let j = 0, l = subRuns[i].passengers.length; j < l; j++) {
                console.log("i : " + i + ", j : " + j);
                console.log(subRuns[i].passengers[j].user.photo);
                this.getAvatars(subRuns[i].passengers[j].user.photo, i, j);
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
