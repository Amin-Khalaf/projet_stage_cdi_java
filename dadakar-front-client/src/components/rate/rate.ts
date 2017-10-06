import { Component } from '@angular/core';
import { NavParams, ViewController } from 'ionic-angular';

import { Rating } from '../../models/rating.model';

import { ImgService } from '../../services/image.service';

@Component({
    selector: 'rate',
    templateUrl: 'rate.html'
})

export class RateComponent {

    connected: boolean;
    photo: string;
    rating: Rating;

    constructor(private imgService: ImgService, private params: NavParams, private view: ViewController) {
        this.connected = this.params.get('connected');
        this.rating = this.params.get('rating');
        this.getPhoto();
    }

    private getPhoto() {
        let filename = this.rating.rater.photo;
        if(this.connected && filename) {
            this.imgService.findByFileName(filename).subscribe(data => {
                if(data) this.photo = 'data:image/jpeg;base64,' + data;
                else this.photo = '/assets/img/avatar.png';
            });
        } else {
            this.photo = '/assets/img/avatar.png';
        }

    }

    dismiss() {
        this.view.dismiss();
    }

}
