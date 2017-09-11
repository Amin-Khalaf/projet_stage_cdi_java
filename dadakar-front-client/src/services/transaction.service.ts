import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";

import { Transaction } from "../models/transaction.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class TransactionService {
    url: string = config.backTransactionServerAddress + "/dadakar/transactions/";

    constructor(private http:Http) {}

    add(transaction: Transaction) {
        return this.http.post(this.url + 'save', transaction).map((res: Response) => res.json());
    }

    findByTransactionNumber(txNumber: string) {
        return this.http.get(this.url + "txnumber:" + txNumber).map((res:Response) => res.json());
    }

    findBySenderId(sid: string) {
        return this.http.get(this.url + "sid:" + sid).map((res:Response) => res.json());
    }

    findByReceiverId(rid: string) {
        return this.http.get(this.url + "rid:" + rid).map((res:Response) => res.json());
    }

    findByDateAndSenderId(txDate: string, sid: string) {
        return this.http.get(this.url + "txdate:" + txDate + "/sid:" + sid).map((res: Response) => res.json());
    }

    findByDateAndReceiverId(txDate: string, rid: string) {
        return this.http.get(this.url + "txdate:" + txDate + "/rid:" + rid).map((res: Response) => res.json());
    }

    findBySenderIdAndReceiverId(sid: string, rid: string) {
        return this.http.get(this.url + "sid:" + sid + "/rid:" + rid).map((res: Response) => res.json());
    }

}
