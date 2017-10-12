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

    temp: number = 0;

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
    subRuns: SubRun[] = [];
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
                            this.runsAllAll.sort((a, b) => {
                                if(a.subRuns[0].startDate > b.subRuns[0].startDate) return -1;
                                else if(a.subRuns[0].startDate < b.subRuns[0].startDate) return 1;
                                else return 0;
                            });
                            this.runsAllDriver.sort((a, b) => {
                                if(a.subRuns[0].startDate > b.subRuns[0].startDate) return -1;
                                else if(a.subRuns[0].startDate < b.subRuns[0].startDate) return 1;
                                else return 0;
                            });
                            this.runsAllPassenger.sort((a, b) => {
                                if(a.subRuns[0].startDate > b.subRuns[0].startDate) return -1;
                                else if(a.subRuns[0].startDate < b.subRuns[0].startDate) return 1;
                                else return 0;
                            });
                            this.runsPassedAll.sort((a, b) => {
                                if(a.subRuns[0].startDate > b.subRuns[0].startDate) return -1;
                                else if(a.subRuns[0].startDate < b.subRuns[0].startDate) return 1;
                                else return 0;
                            });
                            this.runsPassedDriver.sort((a, b) => {
                                if(a.subRuns[0].startDate > b.subRuns[0].startDate) return -1;
                                else if(a.subRuns[0].startDate < b.subRuns[0].startDate) return 1;
                                else return 0;
                            });
                            this.runsPassedPassenger.sort((a, b) => {
                                if(a.subRuns[0].startDate > b.subRuns[0].startDate) return -1;
                                else if(a.subRuns[0].startDate < b.subRuns[0].startDate) return 1;
                                else return 0;
                            });
                            this.runsUpcommingAll.sort((a, b) => {
                                if(a.subRuns[0].startDate > b.subRuns[0].startDate) return -1;
                                else if(a.subRuns[0].startDate < b.subRuns[0].startDate) return 1;
                                else return 0;
                            });
                            this.runsUpcommingDriver.sort((a, b) => {
                                if(a.subRuns[0].startDate > b.subRuns[0].startDate) return -1;
                                else if(a.subRuns[0].startDate < b.subRuns[0].startDate) return 1;
                                else return 0;
                            });
                            this.runsUpcommingPassenger.sort((a, b) => {
                                if(a.subRuns[0].startDate > b.subRuns[0].startDate) return -1;
                                else if(a.subRuns[0].startDate < b.subRuns[0].startDate) return 1;
                                else return 0;
                            });
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

    getSubRuns(runIndex: number, runType: string, userType: string): SubRun[] {
        this.subRuns = [];
        switch(runType) {
            case 'all':
                switch(userType) {
                    case 'all':
                    this.getAsAll(this.runsAllAll[runIndex]);
                    break;
                    case 'driver':
                    this.subRuns = this.runsAllDriver[runIndex].subRuns;
                    break;
                    case 'passenger':
                    this.getAsPassenger(this.runsAllPassenger[runIndex]);
                    break;
                }
                break;
            case 'upcomming':
                switch(userType) {
                    case 'all':
                    this.getAsAll(this.runsUpcommingAll[runIndex]);
                    break;
                    case 'driver':
                    this.subRuns = this.runsUpcommingDriver[runIndex].subRuns;
                    break;
                    case 'passenger':
                    this.getAsPassenger(this.runsUpcommingPassenger[runIndex]);
                    break;
                }
                break;
            case 'passed':
                switch(userType) {
                    case 'all':
                    this.getAsAll(this.runsPassedAll[runIndex]);
                    break;
                    case 'driver':
                    this.subRuns = this.runsPassedDriver[runIndex].subRuns;
                    break;
                    case 'passenger':
                    this.getAsPassenger(this.runsPassedPassenger[runIndex]);
                    break;
                }
                break;
        }
        return this.subRuns;
    }

    private getAsAll(run: Run): void {
        if(run.driver.accountId == this.userAccountId) {
            this.subRuns = run.subRuns;
        } else {
            for(let i = 0, j = run.subRuns.length; i < j; i++) {
                for(let k = 0, l = run.subRuns[i].passengers.length; k < l; k++) {
                    if(run.subRuns[i].passengers[k].user.accountId == this.userAccountId) {
                        this.subRuns.push(run.subRuns[i]);
                        break;
                    }
                }
            }
        }
    }

    private getAsPassenger(run: Run): void {
        for(let i = 0, j = run.subRuns.length; i < j; i++) {
            for(let k = 0, l = run.subRuns[i].passengers.length; k < l; k++) {
                if(run.subRuns[i].passengers[k].user.accountId == this.userAccountId) {
                    this.subRuns.push(run.subRuns[i]);
                    break;
                }
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
