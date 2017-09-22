import { Component } from '@angular/core';
import { MenuController } from 'ionic-angular';

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
    connected: boolean;
    fullPrice: number = 0;
    private img: string;
    monnaie: string = config.monnaie;
    runs: Run[] = [];

    constructor(private authProvider: AuthProvider, private menu: MenuController,private imgService: ImgService, private RunService: RunService) {
        this.RunService.findRuns().subscribe(data => {
            this.runs = data;
        });
        this.authProvider.authUser.subscribe(jwt => {
            if(jwt) {
                this.connected = true;
                this.menuConnectedActive();
            } else {
                this.connected = false;
                this.menuNotConnectedActive();
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

    getAvatar(run: Run): string {
        this.img ='';
        this.imgService.findByFileName(run.driver.photo).subscribe(data => {
            this.img = 'data:image/jpeg;base64,' + data;
        });
        return this.img;
    }

    getFullPrice(run: Run) {
        run.subRuns.forEach(value => {this.fullPrice += value.price; });
    }

    reserve(run: Run) {
        console.log(run);
    }

}
