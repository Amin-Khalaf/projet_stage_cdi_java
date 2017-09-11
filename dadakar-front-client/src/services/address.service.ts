import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";

import { Address } from "../models/address.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class AccountService {

    url: string = config.backRunServerAddress + '/dadakar/addresses/';

    constructor(private http: Http) {}

    add(address: Address) {
        return this.http.post(this.url + 'save', address).map((res:Response) => res.json());
    }

    update(address: Address) {
        return this.http.put(this.url + 'update', address).map((res:Response) => res.json());
    }

    del(addressId: string) {
        this.http.delete(this.url + 'del/' + addressId);
    }

    findAll() {
        return this.http.get(this.url).map((res: Response) => res.json());
    }

    findById(addressId: string) {
        return this.http.get(this.url + addressId).map((res: Response) => res.json());
    }

    findByTown(town: string) {
        return this.http.get(this.url + "town:" + town).map((res: Response) => res.json());
    }

    findByTownAndDistrict(town: string, district: string) {
        return this.http.get(this.url + "town:" + town + "/district:" + district).map((res: Response) => res.json());
    }

}
