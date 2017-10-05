import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { User } from '../../models/user.model';

@Component({
    selector: 'page-view-ratings',
    templateUrl: 'view-ratings.html',
})
export class ViewRatingsPage {

    connected: boolean;
    user: User;

    constructor(private nav: NavController, private params: NavParams) {
        this.connected = this.params.get('connected');
        this.user = this.params.get('user');
    }

}
