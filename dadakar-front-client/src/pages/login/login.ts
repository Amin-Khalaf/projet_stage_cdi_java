import { Component } from '@angular/core';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {

    login: string = "";
    password: string = "";

    connect() {
        console.log("login : " + this.login + ", password : " + this.password);
    }

}
