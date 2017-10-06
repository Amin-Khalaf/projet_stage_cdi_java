import { Component } from '@angular/core';
import { ModalController, NavController, NavParams, ViewController } from 'ionic-angular';

import { RateComponent } from '../../components/rate/rate';

import { Rating } from '../../models/rating.model';
import { User } from '../../models/user.model';

import { ImgService } from '../../services/image.service';

@Component({
    selector: 'user-profile-comp',
    templateUrl: 'user-profile.html'
})

export class UserProfileComponent {

    connected: boolean;
    nbRatings: number;
    note: number = 0;
    photo: string;
    ratersPhoto: string[] = [];
    ratings: Rating[];
    user: User;

    constructor(private imgService: ImgService, private modal: ModalController, private nav: NavController, private params: NavParams, private view: ViewController) {
        this.connected = this.params.get('connected');
        this.user = this.params.get('user');
        this.ratings = this.user.ratings;
        this.nbRatings = this.ratings.length;
        this.getRatings();
        this.getPhoto();
        this.getRatersPhoto();
    }

    private getPhoto() {
        if(this.connected && this.user.photo) {
            this.imgService.findByFileName(this.user.photo).subscribe(data => {
                if(data) this.photo = 'data:image/jpeg;base64,' + data;
                else this.photo = '/assets/img/avatar.png';
            });
        } else {
            this.photo = '/assets/img/avatar.png';
        }
    }

    private getRatersPhoto() {
        for(let i = 0, j = this.ratings.length; i < j; i++) {
            if(this.connected && this.ratings[i].rater.photo) {
                this.imgService.findByFileName(this.ratings[i].rater.photo).subscribe(data => {
                    if(data) this.ratersPhoto[i] = 'data:image/jpeg;base64,' + data;
                    else this.ratersPhoto[i] = '/assets/img/avatar.png';
                });
            } else {
                this.ratersPhoto[i] = '/assets/img/avatar.png';
            }
        }
    }

    private getRatings(): void {
        this.note = 0;
        let ratingValue: number = 0;
        this.nbRatings = this.ratings.length;
        for(let i = 0, j = this.ratings.length; i < j; i++) {
            ratingValue += this.ratings[i].value;
        }
        this.note = ratingValue / this.ratings.length;
    }

    dismiss() {
        this.view.dismiss();
    }

    viewFullRate(rating: Rating) {
        let rate = this.modal.create(RateComponent, {
            connected: this.connected,
            rating: rating
        });
        rate.present();
    }

}
