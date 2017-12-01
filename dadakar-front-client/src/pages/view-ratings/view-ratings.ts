import { Component } from '@angular/core';
import { MenuController, ModalController, NavParams } from 'ionic-angular';

import { RateComponent } from '../../components/rate/rate';

import { Rating } from '../../models/rating.model';
import { User } from '../../models/user.model';

import { AuthProvider } from '../../providers/auth';

import { ImgService } from '../../services/image.service';

@Component({
    selector: 'page-view-ratings',
    templateUrl: 'view-ratings.html',
})
export class ViewRatingsPage {

    activeMenu: string;
    banned: boolean;
    connected: boolean;
    deleted: boolean;
    ratersPhoto: string[] = []
    user: User;

    constructor(private auth: AuthProvider, private imgService: ImgService, private menu: MenuController, private modal: ModalController, private params: NavParams) {
        this.user = this.params.get('user');
        this.auth.authUser.subscribe(jwt => {
            if(jwt) {
                this.banned = jwt.accountDTO.banned;
                this.connected = true;
                this.deleted = jwt.accountDTO.deleted;
                if(jwt.accountDTO.banned || jwt.accountDTO.deleted) this.menuBannedOrDeletedActive();
                else this.menuConnectedActive();
            } else {
                this.connected = false;
                this.menuNotConnectedActive();
            }
            this.getRatersPhoto();
        });
    }

    private getRatersPhoto() {
        let ratings: Rating[] = this.user.ratings;
        for(let i = 0, j = ratings.length; i < j; i++) {
            let filename: string = ratings[i].rater.photo;
            if(this.connected && filename) {
                this.imgService.findByFileName(filename).subscribe(data => {
                    if(data) this.ratersPhoto[i] = 'data:image/jpeg;base64,' + data;
                    else this.ratersPhoto[i] = '/assets/img/avatar.png';
                })
            } else {
                this.ratersPhoto[i] = '/assets/img/avatar.png';
            }
        }
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

    viewFullRate(rating: Rating) {
        let rate = this.modal.create(RateComponent, {
            connected: this.connected,
            rating: rating
        });
        rate.present();
    }

}
