import { Component } from '@angular/core';
import { LoadingController, MenuController, ModalController } from 'ionic-angular';
import { LocalDate } from 'js-joda';

import { ManagedRunDetailsComponent } from '../../components/managed-run-details/managed-run-details';

import { Run } from '../../models/run.model';
import { SubRun } from '../../models/subrun.model';
import { User } from '../../models/user.model';

import { AuthProvider } from '../../providers/auth';

import { RunService } from '../../services/run.service';
import { UserService } from '../../services/user.service';

@Component({
    selector: 'page-manage-runs',
    templateUrl: 'manage-runs.html',
})
export class ManageRunsPage {

    activeMenu: string;
    connected: boolean;
    runsAllAll: Run[] = [];
    runsAllDriver: Run[] = [];
    runsAllPassenger: Run[] = [];
    runsUpcommingAll: Run[] = [];
    runsUpcommingDriver: Run[] = [];
    runsUpcommingPassenger: Run[] = [];
    runsPassedAll: Run[] = [];
    runsPassedDriver: Run[] = [];
    runsPassedPassenger: Run[] = [];
    runType: string = "all";
    userAccountId: string;
    userType: string = "all";

    constructor(private auth: AuthProvider, private loading: LoadingController, private menu: MenuController, private modal: ModalController, private runService: RunService, private userService: UserService) {
        this.menuConnectedActive();
        this.auth.authUser.subscribe(jwt => {
            if(jwt) this.userAccountId = jwt.accountDTO.accountId;
            this.connected = true;
            this.getRuns();
        });
    }

    private getRuns(): void {
        let loading = this.loading.create({
            spinner: 'bubbles',
            content: 'Chargement en cours. Veuillez patienter...'
        });
        loading.present();
        this.runService.findByUser(this.userAccountId).subscribe(data => {
            if(data) this.runsAllAll = data;
            this.runService.findCurrentByUser(this.userAccountId).subscribe(data => {
                if(data) this.runsUpcommingAll = data;
                this.runService.findPassedByUser(this.userAccountId).subscribe(data => {
                    if(data) this.runsPassedAll = data;
                    this.runService.findByDriver(this.userAccountId).subscribe(data => {
                        if(data) this.runsAllDriver = data;
                        for(let i = 0, k = this.runsAllDriver.length; i < k; i++) {
                            let subRuns: SubRun[] = this.runsAllDriver[i].subRuns;
                            let passed: boolean = false;
                            for(let j = 0, l = subRuns.length; j < l; j++) {
                                if(subRuns[j].estimatedEndDate >= LocalDate.now()) {
                                    passed = false;
                                } else {
                                    passed = true;
                                }
                            }
                            if(passed) this.runsPassedDriver.push(this.runsAllDriver[i]);
                            else this.runsUpcommingDriver.push(this.runsAllDriver[i]);
                        }
                        this.runService.findByPassenger(this.userAccountId).subscribe(data => {
                            if(data) this.runsAllPassenger = data;
                            for(let i = 0, k = this.runsAllPassenger.length; i < k; i++) {
                                let subRuns: SubRun[] = this.runsAllPassenger[i].subRuns;
                                let passed: boolean = false;
                                for(let j = 0, l = subRuns.length; j < l; j++) {
                                    if(subRuns[j].estimatedEndDate >= LocalDate.now()) {
                                        passed = false;
                                    } else {
                                        passed = true;
                                    }
                                }
                                if(passed) this.runsPassedPassenger.push(this.runsAllPassenger[i]);
                                else this.runsUpcommingPassenger.push(this.runsAllPassenger[i]);
                            }
                            loading.dismiss();
                        });
                    });
                });
            });
        });
    }

    private menuConnectedActive(): void {
        this.activeMenu = 'menu-connected';
        this.menu.enable(false, 'menu-banned');
        this.menu.enable(false, 'menu-not-connected');
        this.menu.enable(true, 'menu-connected');
    }

    getItems(runType: string, userType: string): Run[] {
        switch(runType) {
            case 'all':
                switch(userType) {
                    case 'all':
                    return this.runsAllAll;
                    case 'driver':
                    return this.runsAllDriver;
                    case 'passenger':
                    return this.runsAllPassenger;
                }
            case 'upcomming':
                switch(userType) {
                    case 'all':
                    return this.runsUpcommingAll;
                    case 'driver':
                    return this.runsUpcommingDriver;
                    case 'passenger':
                    return this.runsUpcommingPassenger;
                }
            case 'passed':
                switch(userType) {
                    case 'all':
                    return this.runsPassedAll;
                    case 'driver':
                    return this.runsPassedDriver;
                    case 'passenger':
                    return this.runsPassedPassenger;
                }
        }
    }

    viewDetails(run: Run) {
        let profile = this.modal.create(ManagedRunDetailsComponent ,{
            run: run,
            connected: this.connected,
            id: this.userAccountId
        });
        profile.present();
    }

}
