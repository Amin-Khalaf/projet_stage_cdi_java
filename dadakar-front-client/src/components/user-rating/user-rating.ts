import { Component } from '@angular/core';

import { Rating } from '../../models/rating.model';
import { User } from '../../models/user.model';

@Component({
    selector: 'user-rating',
    templateUrl: 'user-rating.html'
})

export class UserRatingComponent {

    rating: Rating;
    user: User;
    icons: string[] = [];

    constructor() {
        for(let i = 0; i< 5; i++) this.icons[i] = 'star-outline';
    }

    dismiss() {

    }

    iconClick(index: number) {

    }

    rate() {

    }

}
