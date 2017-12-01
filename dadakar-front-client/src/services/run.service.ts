import { Injectable } from "@angular/core";
import { Headers, Http, Response } from "@angular/http";

import { AuthProvider } from '../providers/auth';
import { Run } from "../models/run.model";
import { Search } from '../models/search.model';

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class RunService {

    url: string = config.backRunServerAddress + '/dadakar/runs/';
    private header: Headers;
    private search: Search;

    constructor(private authProvider: AuthProvider, private http:Http) {
        this.authProvider.authUser.subscribe(jwt => {
            if(jwt) {
                this.header = new Headers({
                    'Authorization': jwt.token
                });
            }
        });
    }

    getSearch(): Search {
    return this.search;
    }

    setSearch(search: Search):void {
        this.search = search;
    }

    add(run: Run) {
        return this.http.post(this.url + 'save', run, {headers : this.header}).map((res:Response) => res.json());
    }

    update(run: Run) {
        return this.http.put(this.url + 'update', run, {headers : this.header}).map((res:Response) => res.json());
    }

    del(runId: string) {
        this.http.delete(this.url + 'del/' + runId, {headers : this.header});
    }

    findAll() {
        return this.http.get(this.url, {headers : this.header}).map((res: Response) => res.json());
    }

    findById(runId: string) {
        return this.http.get(this.url + runId, {headers : this.header}).map((res: Response) => res.json());
    }

    findByDriver(driverId: string) {
        return this.http.get(this.url + "driver:" + driverId, {headers : this.header}).map((res: Response) => res.json());
    }

    findByDriverNotCancelled(driverId: string) {
        return this.http.get(this.url + "driver:" + driverId + "/notcancelled", {headers : this.header}).map((res: Response) => res.json());
    }

    findByPassenger(passengerId: string) {
        return this.http.get(this.url + "passenger:" + passengerId, {headers : this.header}).map((res: Response) => res.json());
    }

    findByPassengerNotCancelled(passengerId: string) {
        return this.http.get(this.url + "passenger:" + passengerId + "/notcancelled", {headers : this.header}).map((res: Response) => res.json());
    }

    findByUser(userId: string) {
        return this.http.get(this.url + "user:" + userId, {headers : this.header}).map((res: Response) => res.json());
    }

    findByUserNotCancelled(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/notcancelled", {headers : this.header}).map((res: Response) => res.json());
    }

    findCurrentByUser(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/current", {headers : this.header}).map((res: Response) => res.json());
    }

    findCurrentByUserNotCancelled(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/current/notcancelled", {headers : this.header}).map((res: Response) => res.json());
    }

    findPassedByUser(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/passed", {headers : this.header}).map((res: Response) => res.json());
    }

    findPassedByUserNotCancelled(userId: string) {
        return this.http.get(this.url + "user:" + userId + "/passed/notcancelled", {headers : this.header}).map((res: Response) => res.json());
    }

    findRuns() {
        return this.http.get(this.url + "searchrun?districtFrom=" + this.search.startDistrict + "&townFrom=" + this.search.startTown + "&dateFrom=" + this.search.startDate + "&districtTo=" + this.search.endDistrict + "&townTo=" + this.search.endTown, {headers : this.header}).map((res: Response) => res.json());
    }

}
