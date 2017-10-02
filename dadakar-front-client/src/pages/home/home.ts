import { Component, OnInit } from '@angular/core';
import { LocalDate } from 'js-joda';
import { MenuController, NavController , AlertController } from 'ionic-angular';
import { NgForm } from '@angular/forms';

import { AuthProvider } from '../../providers/auth';

import { Search } from '../../models/search.model';

import { SearchResultPage } from '../../pages/search-result/search-result';

import { RunService } from '../../services/run.service';

import { AddressService } from '../../services/address.service';

import { Address } from '../../models/address.model';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage implements OnInit {

    activeMenu: string;
    formIsValid: boolean = false;
    today: string;
    maxSearch: string;
    searchValues: Search = null;
    // runCreate1 = RunCreate1Page;
    towns: string[] = [];
    listDistricts: Address[][] = [[]];

        // tester l'ensemble des vues pour tout effacer //
    constructor(private authProvider: AuthProvider, private menu: MenuController, private navCtrl: NavController, private runService: RunService,
                private addressService: AddressService, private alertCtrl: AlertController) {
        this.menu.close();
        this.today = LocalDate.now().toString();
        this.maxSearch = LocalDate.now().plusDays(60).toString();
    }

    ngOnInit(){
      this.findTowns();
    }

    menuBannedOrDeletedActive() {
        this.activeMenu = 'menu-banned';
        this.menu.enable(true, 'menu-banned');
        this.menu.enable(false, 'menu-not-connected');
        this.menu.enable(false, 'menu-connected');
    }

    menuConnectedActive() {
        this.activeMenu = 'menu-connected';
        this.menu.enable(false, 'menu-banned');
        this.menu.enable(false, 'menu-not-connected');
        this.menu.enable(true, 'menu-connected');
    }

    menuNotConnectedActive() {
        this.activeMenu = 'menu-not-connected';
        this.menu.enable(false, 'menu-banned');
        this.menu.enable(true, 'menu-not-connected');
        this.menu.enable(false, 'menu-connected');
    }

    search(values: any, form: NgForm) {
        this.searchValues = {
            startTown: 'Paris',//form.value.startTown,
            startDistrict: '10 ème',//form.value.startDistrict,
            endTown: 'Lille',//form.value.endTown,
            endDistrict: 'Centre', //form.value.endDistrict,
            startDate: form.value.startDate
        }
        this.runService.setSearch(this.searchValues);
        this.navCtrl.push(SearchResultPage);
    }

    validate(values: any) {
        this.formIsValid = values.startTown && values.startDistrict && values.endTown && values.endDistrict && values.startDate;
    }

    findTowns(){
      let addresses : Address[] = [];
      this.addressService.findAll().subscribe((data) => {
        addresses = data;
        for (let address of addresses) {
          if (this.towns.indexOf(address.town) < 0)
            this.towns.push(address.town);
        }
      });
    }

    findDistricts(town: string, index : number){
      this.addressService.findByTown(town).subscribe(
        data =>{
          if (data.length == 0) {
            this.handleError('Aucun quartier trouvé');
          }
          if (this.listDistricts.length >= index + 1) {
            this.listDistricts[index] = data;
          } else {
            this.listDistricts.push(data);
          }
        },
        error => {
          this.handleError(error.json().error);
        }
      );
    }

    ionViewWillEnter() {
        this.authProvider.authUser.subscribe(jwt => {
            if(jwt) {
                if(jwt.accountDTO.banned || jwt.accountDTO.deleted) this.menuBannedOrDeletedActive();
                else this.menuConnectedActive();
            } else {
                this.menuNotConnectedActive();
            }
        });
    }

    onSelectTown(town: string, index: number, values: any) {
      this.validate(values);
      this.findDistricts(town, index);
    }

    private handleError(errorMessage: string){
      const alert = this.alertCtrl.create({
        title: 'An error occured',
        message: errorMessage,
        buttons: ['Ok']
      });
      alert.present();
    }
}
