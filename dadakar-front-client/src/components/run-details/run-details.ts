import { Component } from '@angular/core';
import { NavParams, ViewController } from 'ionic-angular';

import { Rating } from '../../models/rating.model';
import { Run } from '../../models/run.model';
import { Search } from '../../models/search.model';
import { SubRun } from '../../models/subrun.model';
import { WayPoint } from '../../models/waypoint.model';

import { ImgService } from '../../services/image.service';

@Component({
    selector: 'run-details',
    templateUrl: 'run-details.html'
})

export class RunDetailsComponent {

    connected: boolean;
    nbRatings: number;
    photo: string;
    run: Run;
    search: Search;
    wantedSubRun: SubRun;

    constructor(private imgService: ImgService, private params: NavParams, private view: ViewController) {
        this.connected = this.params.get('connected');
        this.run = this.params.get('run');
        this.search = this.params.get('search');
        this.nbRatings = this.run.driver.ratings.length;
        this.getAvatar();
        this.findWantedSubRun();
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

    getAvatar() {
        if(this.connected) {
            this.imgService.findByFileName(this.run.driver.photo).subscribe(data => {
                this.photo = 'data:image/jpeg;base64,' + data;
            })
        } else {
            this.photo = '/assets/img/avatar.png';
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

    dismiss(data: any) {
        this.view.dismiss(data);
    }


}
