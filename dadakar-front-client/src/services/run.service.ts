import { Injectable } from "@angular/core";
import { Headers, Http, Response } from "@angular/http";

import { AuthProvider } from '../providers/auth'
import { Run } from "../models/run.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class RunService {

    url: string = config.backRunServerAddress + '/dadakar/runs/';

    constructor(private authProvider: AuthProvider, private http:Http) {}

    private buildHeaders() {
        return new Headers({
            'Authorization': this.authProvider.jwt
        })
    }

    add(run: Run) {
        return this.http.post(this.url + 'save', run, {headers : this.buildHeaders()}).map((res:Response) => res.json());
    }

    update(run: Run) {
        return this.http.put(this.url + 'update', run, {headers : this.buildHeaders()}).map((res:Response) => res.json());
    }

    del(runId: string) {
        this.http.delete(this.url + 'del/' + runId, {headers : this.buildHeaders()});
    }

    findAll() {
        return this.http.get(this.url, {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findById(runId: string) {
        return this.http.get(this.url + runId, {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findByDriver(driverId: string) {
        return this.http.get(this.url + "driver:" + driverId, {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findByDriverNotCancelled(driverId: string) {
        return this.http.get(this.url + "driver:" + driverId + "/notcancelled", {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findByPassenger(passengerId: string) {
        return this.http.get(this.url + "passenger:" + passengerId, {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findByPassengerNotCancelled(passengerId: string) {
        return this.http.get(this.url + "passenger:" + passengerId + "/notcancelled", {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findByUser(userId: string) {
        return this.http.get(this.url + "user:" + userId, {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findByUserNotCancelled(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/notcancelled", {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findCurrentByUser(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/current", {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findCurrentByUserNotCancelled(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/current/notcancelled", {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findPassedByUser(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/passed", {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findPassedByUserNotCancelled(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/passed/notcancelled", {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findRuns(districtFrom: string, townFrom: string, dateFrom: string, districtTo: string, townTo: string) {
        return this.http.get(this.url + "searchrun?districtFrom=" + districtFrom + "&townFrom=" + townFrom + "&dateFrom=" + dateFrom + "&districtTo=" + districtTo + "&townTo=" + townTo, {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

}
