import { Injectable } from "@angular/core";
import { Headers, Http, Response } from "@angular/http";

import { AuthProvider } from '../providers/auth';
import { User } from "../models/user.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
    url: string = config.backRunServerAddress + "/dadakar/users/";
    private header: Headers;

    constructor(private authProvider: AuthProvider, private http:Http) {
        this.authProvider.authUser.subscribe(jwt => {
            if(jwt) {
                this.header = new Headers({
                    'Authorization': jwt.token
                });
            }
        });
    }

    add(user: User) {
        return this.http.post(this.url + 'save', user, {headers : this.header}).map((res: Response) => res.json());
    }
    update(user: User) {
        return this.http.put(this.url + 'update', user, {headers : this.header}).map((res:Response) => res.json());
    }

    del(userId: string) {
        this.http.delete(this.url + 'del/' + userId, {headers : this.header});
    }

    findAll() {
        return this.http.get(this.url, {headers : this.header}).map((res: Response) => res.json());
    }

    findById(userId: string) {
        return this.http.get(this.url + userId, {headers : this.header}).map((res: Response) => res.json());
    }

    findByLastName(lastName: string) {
        return this.http.get(this.url + "name:" + lastName, {headers : this.header}).map((res: Response) => res.json());
    }

    findByAccountId(accountId: string) {
        return this.http.get(this.url + "accountid:" + accountId, {headers : this.header}).map((res: Response) => res.json());
    }

}
