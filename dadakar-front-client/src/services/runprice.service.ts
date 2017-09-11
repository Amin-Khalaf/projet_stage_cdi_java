import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";

import { RunPrice } from "../models/runprice.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class RunPriceService {

    url: string = config.backRunServerAddress + '/dadakar/runprices/';

    constructor(private http: Http) {}

    findByPower(power: number) {
        return this.http.get(this.url + "power:" + power).map((res: Response) => res.json());
    }

}
