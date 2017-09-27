import { Component, OnInit } from '@angular/core';
import { LocalDate } from 'js-joda';
import { MenuController, AlertController } from 'ionic-angular';
import { NgForm } from '@angular/forms';

import { AuthProvider } from '../../providers/auth';

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
    towns: string[] = [];
    listDistricts: Address[][] = [[]];


    constructor(private authProvider: AuthProvider, private menu: MenuController,
                private addressService: AddressService, private alertCtrl: AlertController) {
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

    ngOnInit(){
      this.findTowns();
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
