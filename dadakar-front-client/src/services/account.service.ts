import { Injectable } from "@angular/core";
import { Headers, Http, Response } from "@angular/http";

import { AuthProvider } from '../providers/auth';
import { Account } from "../models/account.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class AccountService {

    url: string = config.backLoginServerAddress + '/dadakar/accounts/';
    private header: Headers;

    constructor(private authProvider: AuthProvider, private http:Http) {
        this.authProvider.authUser.subscribe(jwt => {
            this.header = new Headers({
                'Authorization': jwt
            });
        });
    }

    add(account: Account) {
        return this.http.post(this.url + 'save', account, {headers : this.header}).map((res:Response) => res.json());
    }

    update(account: Account) {
        return this.http.put(this.url + 'update', account, {headers : this.header}).map((res:Response) => res.json());
    }

    del(accountId: string) {
        this.http.delete(this.url + 'del/' + accountId, {headers : this.header});
    }

    findAll() {
        return this.http.get(this.url, {headers : this.header}).map((res: Response) => res.json());
    }

    findById(accountId: string) {
        return this.http.get(this.url + accountId, {headers : this.header}).map((res: Response) => res.json());
    }

    findByUsername(username: string) {
        return this.http.get(this.url + "/username:" + username, {headers : this.header}).map((res: Response) => res.json());
    }

    findUsers() {
        return this.http.get(this.url + "/users", {headers : this.header}).map((res: Response) => res.json());
    }

}
