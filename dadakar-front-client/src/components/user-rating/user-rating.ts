import { Component } from '@angular/core';
import { NavParams, ViewController } from 'ionic-angular';

import { Rating } from '../../models/rating.model';
import { Run } from '../../models/run.model';
import { User } from '../../models/user.model';

import { ImgService } from '../../services/image.service';
import { RunService } from '../../services/run.service';
import { UserService } from '../../services/user.service';

@Component({
    selector: 'user-rating',
    templateUrl: 'user-rating.html'
})

export class UserRatingComponent {

    comment: string;
    photo: string;
    rated: boolean;
    raterAccountId: string;
    rating: Rating;
    user: User;
    icons: string[] = [];
    iconsPrevious: string[] = [];

    constructor(private imgService: ImgService, private params: NavParams, private runService: RunService, private userService: UserService, private view: ViewController) {
        this.user = this.params.get('user');
        this.raterAccountId = this.params.get('raterAccountId');
        for(let i = 0, j = this.user.ratings.length; i < j; i++) {
            if(this.user.ratings[i].rater.accountId == this.raterAccountId) {
                this.comment = this.user.ratings[i].comment;
                let step = 0.5;
                let score = Math.ceil(this.user.ratings[i].value / step) * step;
                for (let i = 1; i <= 5; i++) {
                    if (i <= score) {
                        this.icons.push('star');
                    } else if (i - step <= score) {
                        this.icons.push('star-half');
                    } else {
                        this.icons.push('star-outline');
                    }
                }
                this.rated = true;
                break;
            }
        }
        if(!this.rated) for(let i = 0; i< 5; i++) this.icons[i] = 'star-outline';
        for(let i = 0; i< 5; i++) this.iconsPrevious[i] = this.icons[i];
        this.imgService.findByFileName(this.user.photo).subscribe(data => {
            if(data) this.photo = 'data:image/jpeg;base64,' + data;
            else this.photo = '/assets/img/avatar.png';
        });
    }

    dismiss() {
        this.view.dismiss();
    }

    iconClick(index: number) {
        for(let i = 0; i < index; i++) this.icons[i] = 'star';
        for(let i = index + 1; i < 5; i++) this.icons[i] = 'star-outline';
        switch(this.icons[index]) {
            case 'star-outline':
            case 'star':
                for(let i = 0; i< 5; i++) this.iconsPrevious[i] = this.icons[i];
                this.icons[index] = 'star-half';
                break;
            case 'star-half':
                if(this.iconsPrevious[index] == 'star-outline') this.icons[index] = 'star';
                else this.icons[index] = 'star-outline';
                break;
        }
    }

    rate() {
        this.userService.findByAccountId(this.raterAccountId).subscribe(data => {
            let rate: Rating;
            let score: number = 0;
            for(let i = 0; i < 5; i++) {
                if(this.icons[i] == 'star') score++;
                if(this.icons[i] == 'star-half') score += 0.5;
            }
            rate = {
                comment: this.comment,
                rater: data,
                ratingId: '' + this.user.ratings.length,
                value: score
            }
            this.user.ratings.push(rate);
            //update user
            this.userService.update(this.user).subscribe();
            // update runs containing user
                // has driver
            this.runService.findByDriver(this.user.accountId).subscribe(data => {
                if(data) {
                    let runs: Run[] = data;
                    for(let i = 0, j = runs.length; i < j; i++) {
                        runs[i].driver = this.user;
                        this.runService.update(runs[i]).subscribe();
                    }
                }
            });
                // has passenger
            this.runService.findByPassenger(this.user.accountId).subscribe(data => {
                if(data) {
                    let runs: Run[] = data;
                    for(let i = 0, j = runs.length; i < j; i++) {
                        for(let k = 0, l = runs[i].subRuns.length; k < l; k++) {
                            for(let m = 0, n = runs[i].subRuns[k].passengers.length; m < n; m++) {
                                if(runs[i].subRuns[k].passengers[m].user.accountId == this.user.accountId) runs[i].subRuns[k].passengers[m].user = this.user;
                            }
                        }
                        this.runService.update(runs[i]).subscribe();
                    }
                }
            });
            this.view.dismiss();
        });
    }

}
