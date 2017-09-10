import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";

import { Observable } from "rxjs/Observable";

import { Account } from "../models/account.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class AccountService {

    url: string = config.backServerAddress + '/dadakar/accounts/';

    constructor(private http: Http) {}

    add(account: Account) {
        return this.http.post(this.url + 'save', account).map((res:Response) => res.json());
    }

    update(account: Account) {
        return this.http.put(this.url + 'update', account).map((res:Response) => res.json());
    }

    del(accountId: string) {
        this.http.delete(this.url + 'del/' + accountId);
    }

    findAll() {
        return this.http.get(this.url).map((res: Response) => res.json());
    }

    findById(accountId: string) {
        return this.http.get(this.url + accountId).map((res: Response) => res.json());
    }

    findByUsername(username: string) {
        return this.http.get(this.url + "/username:" + username).map((res: Response) => res.json());
    }

    findUsers() {
        return this.http.get(this.url + "/users").map((res: Response) => res.json());
    }

}
