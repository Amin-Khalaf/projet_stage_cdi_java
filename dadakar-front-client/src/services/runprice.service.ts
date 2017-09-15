import { Injectable } from "@angular/core";
import { Headers, Http, Response } from "@angular/http";

import { AuthProvider } from '../providers/auth';

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class RunPriceService {

    url: string = config.backRunServerAddress + '/dadakar/runprices/';
    private header: Headers;

    constructor(private authProvider: AuthProvider, private http:Http) {
        this.authProvider.authUser.subscribe(jwt => {
            this.header = new Headers({
                'Authorization': jwt
            });
        });
    }

    findByPower(power: number) {
        return this.http.get(this.url + "power:" + power, {headers : this.header}).map((res: Response) => res.json());
    }

}
