import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";

import { Run } from "../models/run.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class RunService {

    url: string = config.backRunServerAddress + '/dadakar/runs/';

    constructor(private http: Http) {}

    add(run: Run) {
        return this.http.post(this.url + 'save', run).map((res:Response) => res.json());
    }

    update(run: Run) {
        return this.http.put(this.url + 'update', run).map((res:Response) => res.json());
    }

    del(runId: string) {
        this.http.delete(this.url + 'del/' + runId);
    }

    findAll() {
        return this.http.get(this.url).map((res: Response) => res.json());
    }

    findById(runId: string) {
        return this.http.get(this.url + runId).map((res: Response) => res.json());
    }

    findByDriver(driverId: string) {
        return this.http.get(this.url + "driver:" + driverId).map((res: Response) => res.json());
    }

    findByDriverNotCancelled(driverId: string) {
        return this.http.get(this.url + "driver:" + driverId + "/notcancelled").map((res: Response) => res.json());
    }

    findByPassenger(passengerId: string) {
        return this.http.get(this.url + "passenger:" + passengerId).map((res: Response) => res.json());
    }

    findByPassengerNotCancelled(passengerId: string) {
        return this.http.get(this.url + "passenger:" + passengerId + "/notcancelled").map((res: Response) => res.json());
    }

    findByUser(userId: string) {
        return this.http.get(this.url + "user:" + userId).map((res: Response) => res.json());
    }

    findByUserNotCancelled(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/notcancelled").map((res: Response) => res.json());
    }

    findCurrentByUser(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/current").map((res: Response) => res.json());
    }

    findCurrentByUserNotCancelled(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/current/notcancelled").map((res: Response) => res.json());
    }

    findPassedByUser(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/passed").map((res: Response) => res.json());
    }

    findPassedByUserNotCancelled(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/passed/notcancelled").map((res: Response) => res.json());
    }

    findRuns(districtFrom: string, townFrom: string, dateFrom: string, districtTo: string, townTo: string) {
        return this.http.get(this.url + "searchrun?districtFrom=" + districtFrom + "&townFrom=" + townFrom + "&dateFrom=" + dateFrom + "&districtTo=" + districtTo + "&townTo=" + townTo).map((res: Response) => res.json());
    }

}
