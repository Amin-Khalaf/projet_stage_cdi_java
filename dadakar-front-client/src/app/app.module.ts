import { AuthConfig, AuthHttp, JwtHelper } from 'angular2-jwt';
import { BrowserModule } from '@angular/platform-browser';
import { Camera } from '@ionic-native/camera';
import { CustomFormsModule } from 'ng2-validation';
import { ErrorHandler, NgModule } from '@angular/core';
import { FileTransfer } from '@ionic-native/file-transfer';
import { Http, HttpModule, RequestOptions } from "@angular/http";
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { IonicStorageModule, Storage } from '@ionic/storage';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { AuthProvider } from "../providers/auth";
import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { LoginComponent } from '../components/login/login';
import { SignupPage } from '../pages/signup/signup';
import { UserSignupPage } from '../pages/user-signup/user-signup';

import { AccountService } from '../services/account.service';
import { AddressService } from '../services/address.service';
import { ImgService } from '../services/image.service';
import { MessageService } from '../services/message.service';
import { RunService } from '../services/run.service';
import { RunPriceService } from '../services/runprice.service';
import { TransactionService } from '../services/transaction.service';
import { UserService } from '../services/user.service';

export function authHttpServiceFactory(http: Http, options: RequestOptions, storage: Storage) {
    const authConfig = new AuthConfig({ tokenGetter: (() => storage.get('jwt')),});
    return new AuthHttp(authConfig, http, options);
}

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    LoginComponent,
    SignupPage,
    UserSignupPage
  ],
  imports: [
    BrowserModule,
    CustomFormsModule,
    HttpModule,
    IonicModule.forRoot(MyApp, {
        monthNames: ['janvier', 'fevrier', 'mars', 'avril', 'mai', 'juin', 'juillet', 'aout', 'septembre', 'octobre', 'novembre', 'decembre'],
        monthShortNames: ['jan', 'fev', 'mar', 'avr', 'mai', 'jui', 'jul', 'aou', 'sep', 'oct', 'nov', 'dec'],
        dayNames: ['dimanche', 'lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi'],
        dayShortNames: ['dim', 'lun', 'mar', 'mer', 'jeu', 'ven', 'sam']
    }),
    IonicStorageModule.forRoot({
        name: "dadakar",
        driverOrder: ['sqlite', 'indexeddb', 'websql']
    })
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    SignupPage,
    UserSignupPage
  ],
  providers: [
    AuthProvider,
    Camera,
    FileTransfer,
    JwtHelper,
    {provide: AuthHttp, useFactory: authHttpServiceFactory, deps: [Http, RequestOptions, Storage]},
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    AccountService,
    AddressService,
    ImgService,
    MessageService,
    RunService,
    RunPriceService,
    TransactionService,
    UserService
  ]
})
export class AppModule {}
