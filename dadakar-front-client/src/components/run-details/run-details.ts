import { Component } from '@angular/core';
import { NavParams, ViewController } from 'ionic-angular';

import { Rating } from '../../models/rating.model';
import { Run } from '../../models/run.model';
import { Search } from '../../models/search.model';
import { SubRun } from '../../models/subrun.model';
import { Vehicle } from '../../models/vehicle.model';
import { WayPoint } from '../../models/waypoint.model';

import { ImgService } from '../../services/image.service';

@Component({
    selector: 'run-details',
    templateUrl: 'run-details.html'
})

export class RunDetailsComponent {

    connected: boolean;
    nbRatings: number;
    passengersPhotos: string[][] = [[]];
    photo: string;
    photos: string[] = [];
    photoVehicule: string;
    run: Run;
    search: Search;
    subRuns: SubRun[] = [];
    vehicle: Vehicle;
    wantedSubRun: SubRun;

    constructor(private imgService: ImgService, private params: NavParams, private view: ViewController) {
        this.connected = this.params.get('connected');
        this.run = this.params.get('run');
        this.search = this.params.get('search');
        this.nbRatings = this.run.driver.ratings.length;
        this.subRuns = this.run.subRuns;
        this.getAvatar(this.run.driver.photo, false);
        this.findVehicle();
        this.findWantedSubRun();
        this.getPassengersPhotos();
    }

    dismiss(data: any): void {
        this.view.dismiss(data);
    }

    findWantedSubRun(): void {
        for(let i = 0, j = this.run.subRuns.length; i < j; i++) {
            let startPlace: WayPoint = this.run.subRuns[i].startPlace;
            let endPlace: WayPoint = this.run.subRuns[i].endPlace;
            if(startPlace.address.town == this.search.startTown || startPlace.address.district == this.search.startDistrict || endPlace.address.town == this.search.endTown || endPlace.address.district == this.search.endDistrict) {
                this.wantedSubRun = this.run.subRuns[i];
                break;
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

    getPassengersPhotos(): void {
        let subRuns = this.run.subRuns;
        for(let i = 0, k = subRuns.length; i < k; i++) {
            for(let j = 0, l = subRuns[i].passengers.length; j < l; j++) {
                this.getAvatars(subRuns[i].passengers[j].user.photo, i, j);
            }
        }
    }

    getRatings(): number {
        if(this.nbRatings != 0) {
            let ratingValue: number = 0;
            let ratings: Rating[] = this.run.driver.ratings;
            for(let i = 0, j = ratings.length; i < j; i++) {
                ratingValue += ratings[i].value;
            }
            return ratingValue / ratings.length;
        }
        return 0;
    }

    viewProfile(userId: string): void {
        console.log(userId);
    }

}
