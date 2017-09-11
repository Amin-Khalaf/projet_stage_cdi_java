import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class ImgService {
    url: string = config.backImgServerAddress + "/dadakar/img/";

    constructor(private http: Http) {}

    add(image) {
        this.http.post(this.url + "upload", image);
    }

    findById(imageId: string) {
        return this.http.get(this.url + imageId);
    }

    findByFileName(fileName: string) {
        return this.http.get(this.url + "/name:" + fileName);
    }

}
