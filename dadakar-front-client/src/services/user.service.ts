import { Injectable } from "@angular/core";
import { Headers, Http, Response } from "@angular/http";

import { AuthProvider } from '../providers/auth'
import { User } from "../models/user.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
    url: string = config.backRunServerAddress + "/dadakar/users/";

    constructor(private authProvider: AuthProvider, private http:Http) {}

    private buildHeaders() {
        return new Headers({
            'Authorization': this.authProvider.jwt
        })
    }

    add(user: User) {
        return this.http.post(this.url + 'save', user, {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }
    update(user: User) {
        return this.http.put(this.url + 'update', user, {headers : this.buildHeaders()}).map((res:Response) => res.json());
    }

    del(userId: string) {
        this.http.delete(this.url + 'del/' + userId, {headers : this.buildHeaders()});
    }

    findAll() {
        return this.http.get(this.url, {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findById(userId: string) {
        return this.http.get(this.url + userId, {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findByLastName(lastName: string) {
        return this.http.get(this.url + "name:" + lastName, {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

    findByAccountId(accountId: string) {
        return this.http.get(this.url + "accountid:" + accountId, {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

}
