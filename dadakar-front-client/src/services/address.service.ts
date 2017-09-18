import { Injectable } from "@angular/core";
import { Headers, Http, Response } from "@angular/http";

import { AuthProvider } from '../providers/auth';
import { Address } from "../models/address.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class AddressService {

    url: string = config.backRunServerAddress + '/dadakar/addresses/';
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

    add(address: Address) {
        return this.http.post(this.url + 'save', address, {headers : this.header}).map((res:Response) => res.json());
    }

    update(address: Address) {
        return this.http.put(this.url + 'update', address, {headers : this.header}).map((res:Response) => res.json());
    }

    del(addressId: string) {
        this.http.delete(this.url + 'del/' + addressId, {headers : this.header});
    }

    findAll() {
        return this.http.get(this.url, {headers : this.header}).map((res: Response) => res.json());
    }

    findById(addressId: string) {
        return this.http.get(this.url + addressId, {headers : this.header}).map((res: Response) => res.json());
    }

    findByTown(town: string) {
        return this.http.get(this.url + "town:" + town, {headers : this.header}).map((res: Response) => res.json());
    }

    findByTownAndDistrict(town: string, district: string) {
        return this.http.get(this.url + "town:" + town + "/district:" + district, {headers : this.header}).map((res: Response) => res.json());
    }

}
