import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";

import { Observable } from "rxjs/Observable";

import { Rating } from "../models/rating.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class AccountService {

    url: string = config.backServerAddress + '/dadakar/ratings/';

    constructor(private http: Http) {}

    add(rating: Rating) {
        return this.http.post(this.url + 'save', rating).map((res:Response) => res.json());
    }

    update(rating: Rating) {
        return this.http.put(this.url + 'update', rating).map((res:Response) => res.json());
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

}
