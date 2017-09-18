import { Injectable } from "@angular/core";
import { Headers, Http, Response } from "@angular/http";

import { AuthProvider } from '../providers/auth';
import { Message } from "../models/message.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class MessageService {

    url: string = config.backMsgServerAddress + '/dadakar/msg/';
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

    add(message: Message) {
        return this.http.post(this.url + 'save', message, {headers : this.header}).map((res:Response) => res.json());
    }

    update(message: Message) {
        return this.http.put(this.url + 'update', message, {headers : this.header}).map((res:Response) => res.json());
    }

    del(messageId: string) {
        this.http.delete(this.url + 'del/' + messageId, {headers : this.header});
    }

    findAll() {
        return this.http.get(this.url, {headers : this.header}).map((res: Response) => res.json());
    }

    findById(messageId: string) {
        return this.http.get(this.url + messageId, {headers : this.header}).map((res: Response) => res.json());
    }

    findByHoroLess(end: string) {
        return this.http.get(this.url + "lte:" + end, {headers : this.header}).map((res: Response) => res.json());
    }

    findByHoroGreater(start: string) {
        return this.http.get(this.url + "gte:" + start, {headers : this.header}).map((res: Response) => res.json());
    }

    findByHoroBetween(start: string, end: string) {
        return this.http.get(this.url + "gte:" + start + "/lte:" + end, {headers : this.header}).map((res: Response) => res.json());
    }

    findBySenderId(sid: string) {
        return this.http.get(this.url + "sid:" + sid, {headers : this.header}).map((res: Response) => res.json());
    }

    findBySenderIdAndHoroLess(sid: string, end: string) {
        return this.http.get(this.url + "sid:" + sid + "/lte:" + end, {headers : this.header}).map((res: Response) => res.json());
    }

    findBySenderIdAndHoroGreater(sid: string, start: string) {
        return this.http.get(this.url + "sid:" + sid + "/gte:" + start, {headers : this.header}).map((res: Response) => res.json());
    }

    findBySenderIdAndHoroBetween(sid: string, start: string, end: string) {
        return this.http.get(this.url + "sid:" + sid + "/gte:" + start + "/lte:" + end, {headers : this.header}).map((res: Response) => res.json());
    }

    findByReceiverId(rid: string) {
        return this.http.get(this.url + "rid:" + rid, {headers : this.header}).map((res: Response) => res.json());
    }

    findByReceiverIdAndHoroLess(rid: string, end: string) {
        return this.http.get(this.url + "rid:" + rid + "/lte:" + end, {headers : this.header}).map((res: Response) => res.json());
    }

    findByReceiverIdAndHoroGreater(rid: string, start: string) {
        return this.http.get(this.url + "rid:" + rid + "/gte:" + start, {headers : this.header}).map((res: Response) => res.json());
    }

    findByReceiverIdAndHoroBetween(rid: string, start: string, end: string) {
        return this.http.get(this.url + "rid:" + rid + "/gte:" + start + "/lte:" + end, {headers : this.header}).map((res: Response) => res.json());
    }

    findBySenderIdAndReceiverId(sid: string, rid: string) {
        return this.http.get(this.url + "sid:" + sid + "/rid:" + rid, {headers : this.header}).map((res: Response) => res.json());
    }

    findBySenderIdAndReceiverIdAndHoroLess(sid: string, rid: string, end: string) {
        return this.http.get(this.url + "sid:" + sid + "/rid:" + rid + "/lte:" + end, {headers : this.header}).map((res: Response) => res.json());
    }

    findBySenderIdAndReceiverIdAndHoroGreater(sid: string, rid: string, start: string) {
        return this.http.get(this.url + "sid:" + sid + "/rid:" + rid + "/gte:" + start, {headers : this.header}).map((res: Response) => res.json());
    }

    findBySenderIdAndReceiverIdAndHoroBetween(sid: string, rid: string, start: string, end: string) {
        return this.http.get(this.url + "sid:" + sid + "/rid:" + rid + "/gte:" + start + "/lte:" + end, {headers : this.header}).map((res: Response) => res.json());
    }

}
