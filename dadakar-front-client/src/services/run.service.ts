import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";

import { Run } from "../models/run.model";

import config from "../assets/config/config";

import 'rxjs/add/operator/map';

@Injectable()
export class RunService {

    url: string = config.backRunServerAddress + '/dadakar/runs/';

    constructor(private http: Http) {}

    add(run: Run) {
        return this.http.post(this.url + 'save', run).map((res:Response) => res.json());
    }

    update(run: Run) {
        return this.http.put(this.url + 'update', run).map((res:Response) => res.json());
    }

    del(runId: string) {
        this.http.delete(this.url + 'del/' + runId);
    }

    findAll() {
        return this.http.get(this.url).map((res: Response) => res.json());
    }

    findById(runId: string) {
        return this.http.get(this.url + runId).map((res: Response) => res.json());
    }

}
