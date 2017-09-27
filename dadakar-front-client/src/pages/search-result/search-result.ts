import { Component } from '@angular/core';
import { LoadingController, MenuController } from 'ionic-angular';

import { Run } from '../../models/run.model';

import { AuthProvider } from '../../providers/auth';

import { ImgService } from '../../services/image.service';
import { RunService } from '../../services/run.service';

import config from "../../assets/config/config";

@Component({
  selector: 'page-search-result',
  templateUrl: 'search-result.html',
})
export class SearchResultPage {

    activeMenu: string;
    connected: boolean = false;
    private fullPrice: number = 0;
    minPrice: number = 0;
    maxPrice:number = 0;
    monnaie: string = config.monnaie;
    nbRuns: number;
    runs: Run[] = [];
    startTown: string = this.runService.getSearch().startTown;
    endTown: string = this.runService.getSearch().endTown;
    private tempPrice: number = 0;


    constructor(private authProvider: AuthProvider, private imgService: ImgService, private loader: LoadingController, private menu: MenuController, private runService: RunService) {
        this.authProvider.authUser.subscribe(jwt => {
            if(jwt) {
                this.connected = true;
                this.menuConnectedActive();
                this.getItems();
            } else {
                this.connected = false;
                this.menuNotConnectedActive();
                this.getItems();
            }
        })
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

    getAvatar(run: Run): void {
        if(this.connected) {
            this.imgService.findByFileName(run.driver.photo).subscribe(data => {
                run.driver.photo = 'data:image/jpeg;base64,' + data;
            });
        } else {
            run.driver.photo = '/assets/img/avatar.png';
        }
    }

    getFullPrice(run: Run): number {
        this.fullPrice = 0;
        for(let i = 0, j = run.subRuns.length; i < j; i++) {
            this.fullPrice += run.subRuns[i].price;
        }
        return this.fullPrice;
    }

    private getItems() {

        let loading = this.loader.create({
            spinner: 'bubbles',
            content: 'Veuillez patientez nous recherchons les disponibilitées...'
        });

        loading.present();

        this.runService.findRuns().finally(() => loading.dismiss()).subscribe(data => {
            this.runs = data;
            this.nbRuns = this.runs.length;
            for(let i = 0, j = this.nbRuns; i < j; i++) {
                this.getAvatar(this.runs[i]);
                this.tempPrice = this.getFullPrice(this.runs[i]);
                if(this.minPrice != 0) {
                    if(this.minPrice > this.tempPrice) {
                        this.minPrice = this.tempPrice;
                    }
                    if(this.maxPrice < this.tempPrice) {
                        this.maxPrice = this.tempPrice;
                    }
                } else {
                    this.minPrice = this.tempPrice;
                    this.maxPrice = this.tempPrice;
                }
            }
        });
    }

    reserve(run: Run) {
        if(this.connected) {
            console.log(run);
        } else {
            console.log('not connected')
        }
    }

}
