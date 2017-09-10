import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";

import { Observable } from "rxjs/Observable";

import { User } from "../models/user.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
    url: string = config.backServerAddress + "/dadakar/users/";

    constructor(private http:Http) {}

    add(user: User) {
        return this.http.post(this.url + 'save', user).map((res: Response) => res.json());
    }
    update(user: User) {
        return this.http.put(this.url + 'update', user).map((res:Response) => res.json());
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

    findByLastName(lastName: string) {
        return this.http.get(this.url + "name:" + lastName).map((res: Response) => res.json());
    }

    findByAccountUsername(username: string) {
        return this.http.get(this.url + "username:" + username).map((res: Response) => res.json());
    }

}
