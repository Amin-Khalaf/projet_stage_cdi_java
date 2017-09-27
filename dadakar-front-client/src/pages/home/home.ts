import { Component } from '@angular/core';
import { LocalDate } from 'js-joda';
import { MenuController, NavController } from 'ionic-angular';
import { NgForm } from '@angular/forms';

import { AuthProvider } from '../../providers/auth';

import { Search } from '../../models/search.model';

import { SearchResultPage } from '../../pages/search-result/search-result';

import { RunService } from '../../services/run.service';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

    activeMenu: string;
    formIsValid: boolean = false;
    today: string;
    maxSearch: string;
    searchValues: Search = null;

    constructor(private authProvider: AuthProvider, private menu: MenuController, private navCtrl: NavController, private runService: RunService) {
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
        this.searchValues = {
            startTown: 'Paris',
            startDistrict: '10 ème',
            endTown: 'Lille',
            endDistrict: 'Centre',
            startDate: LocalDate.of(2017, 9, 22)
        }
        this.runService.setSearch(this.searchValues);
        this.navCtrl.push(SearchResultPage);
    }

    validate(values: any) {
        this.formIsValid = values.startTown && values.startDistrict && values.endTown && values.endDistrict && values.startDate;
    }

}
