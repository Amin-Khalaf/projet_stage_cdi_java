import { Injectable } from "@angular/core";
import { Headers, Http, Response } from "@angular/http";

import { AuthProvider } from '../providers/auth';
import { Transaction } from "../models/transaction.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class TransactionService {
    url: string = config.backTransactionServerAddress + "/dadakar/transactions/";
    private header: Headers;

    constructor(private authProvider: AuthProvider, private http:Http) {
        this.authProvider.authUser.subscribe(jwt => {
            this.header = new Headers({
                'Authorization': jwt
            });
        });
    }

    add(transaction: Transaction) {
        return this.http.post(this.url + 'save', transaction, {headers : this.header}).map((res: Response) => res.json());
    }

    findByTransactionNumber(txNumber: string) {
        return this.http.get(this.url + "txnumber:" + txNumber, {headers : this.header}).map((res:Response) => res.json());
    }

    findBySenderId(sid: string) {
        return this.http.get(this.url + "sid:" + sid, {headers : this.header}).map((res:Response) => res.json());
    }

    findByReceiverId(rid: string) {
        return this.http.get(this.url + "rid:" + rid, {headers : this.header}).map((res:Response) => res.json());
    }

    findByDateAndSenderId(txDate: string, sid: string) {
        return this.http.get(this.url + "txdate:" + txDate + "/sid:" + sid, {headers : this.header}).map((res: Response) => res.json());
    }

    findByDateAndReceiverId(txDate: string, rid: string) {
        return this.http.get(this.url + "txdate:" + txDate + "/rid:" + rid, {headers : this.header}).map((res: Response) => res.json());
    }

    findBySenderIdAndReceiverId(sid: string, rid: string) {
        return this.http.get(this.url + "sid:" + sid + "/rid:" + rid, {headers : this.header}).map((res: Response) => res.json());
    }

}
