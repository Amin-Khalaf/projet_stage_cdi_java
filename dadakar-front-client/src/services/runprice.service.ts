import { Injectable } from "@angular/core";
import { Headers, Http, Response } from "@angular/http";

import { AuthProvider } from '../providers/auth'
import { RunPrice } from "../models/runprice.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class RunPriceService {

    url: string = config.backRunServerAddress + '/dadakar/runprices/';

    constructor(private authProvider: AuthProvider, private http:Http) {}

    private buildHeaders() {
        return new Headers({
            'Authorization': this.authProvider.jwt
        })
    }

    findByPower(power: number) {
        return this.http.get(this.url + "power:" + power, {headers : this.buildHeaders()}).map((res: Response) => res.json());
    }

}
