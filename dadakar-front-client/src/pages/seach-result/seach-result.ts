import { Component } from '@angular/core';
import { MenuController } from 'ionic-angular';

import { Run } from '../../models/run.model';

import { AuthProvider } from '../../providers/auth';

import { RunService } from '../../services/run.service';

@Component({
  selector: 'page-seach-result',
  templateUrl: 'seach-result.html',
})
export class SeachResultPage {

    activeMenu: string;
    runs: Run[];

    constructor(private authProvider: AuthProvider, private menu: MenuController, private RunService: RunService) {
        //this.runs = this.RunService.findRuns();
        this.authProvider.authUser.subscribe(jwt => {
            if(jwt) {
                this.menuConnectedActive();
            } else {
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

}
