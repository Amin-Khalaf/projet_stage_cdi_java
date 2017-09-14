import { Injectable } from "@angular/core";
import {Headers, Http, Response } from "@angular/http";

import { AuthProvider } from '../providers/auth'

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class ImgService {
    url: string = config.backImgServerAddress + "/dadakar/img/";

    constructor(private authProvider: AuthProvider, private http:Http) {}

    private buildHeaders() {
        return new Headers({
            'Authorization': this.authProvider.jwt
        })
    }

    add(image) {
        this.http.post(this.url + "upload", image, {headers : this.buildHeaders()});
    }

    findById(imageId: string) {
        return this.http.get(this.url + imageId, {headers : this.buildHeaders()});
    }

    findByFileName(fileName: string) {
        return this.http.get(this.url + "/name:" + fileName, {headers : this.buildHeaders()});
    }

}
