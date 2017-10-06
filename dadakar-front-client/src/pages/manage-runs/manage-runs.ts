import { Component } from '@angular/core';
import {  } from 'ionic-angular';

import { Run } from '../../models/run.model';

@Component({
    selector: 'page-manage-runs',
    templateUrl: 'manage-runs.html',
})
export class ManageRunsPage {

    runsUpcommingAll: Run[] = [];
    runsUpcommingDriver: Run[] = [];
    runsUpcommingPassenger: Run[] = [];
    runsInprogressAll: Run[] = [];
    runsInprogressDriver: Run[] = [];
    runsInprogressPassenger: Run[] = [];
    runsPassedAll: Run[] = [];
    runsPassedDriver: Run[] = [];
    runsPassedPassenger: Run[] = [];
    runType: string = "upcomming";
    userType: string = "all";

    constructor() {
    }

}
