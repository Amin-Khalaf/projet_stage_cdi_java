import { Component } from '@angular/core';
import { LocalDate } from 'js-joda';
import { MenuController } from 'ionic-angular';
import { NgForm } from '@angular/forms';

import { AuthProvider } from '../../providers/auth';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

    activeMenu: string;
    formIsValid: boolean = false;
    today: string;
    maxSearch: string;

    constructor(private authProvider: AuthProvider, private menu: MenuController) {
        this.today = LocalDate.now().toString();
        this.maxSearch = LocalDate.now().plusDays(60).toString();
        this.authProvider.authUser.subscribe(jwt => {
            if(jwt) {
                this.menuConnectedActive();
            } else {
                this.menuNotConnectedActive();
            }
        });
    }

    menuNotConnectedActive() {
        this.activeMenu = 'menu-not-connected';
        this.menu.enable(true, 'menu-not-connected');
        this.menu.enable(false, 'menu-connected');
    }

    menuConnectedActive() {
        this.activeMenu = 'menu-connected';
        this.menu.enable(false, 'menu-not-connected');
        this.menu.enable(true, 'menu-connected');
    }

    search(values: any, form: NgForm) {
        console.log(values);
    }

    validate(values: any) {
        this.formIsValid = values.startTown && values.startDistrict && values.endTown && values.endDistrict && values.startDate;
    }

}
