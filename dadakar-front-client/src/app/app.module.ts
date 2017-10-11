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
import { AgmCoreModule } from '@agm/core';

import { MyApp } from './app.component';

import { BookRunPage } from '../pages/book-run/book-run';
import { HomePage } from '../pages/home/home';
import { ManagedRunDetailsComponent } from '../components/managed-run-details/managed-run-details';
import { ManageRunsPage } from '../pages/manage-runs/manage-runs';
import { MenuBannedComponent } from '../components/menu-banned/menu-banned';
import { MenuConnectedComponent } from '../components/menu-connected/menu-connected';
import { MenuNotConnectedComponent } from '../components/menu-not-connected/menu-not-connected';
import { MessageComponent } from '../components/message/message';
import { MessagesComponent } from '../components/messages/messages';
import { AddressForm } from '../components/address-form/address-form';
import { SearchResultPage } from '../pages/search-result/search-result';
import { SignupPage } from '../pages/signup/signup';
import { UserProfileComponent } from '../components/user-profile/user-profile';
import { UserProfilePage } from '../pages/user-profile/user-profile';
import { UserRatingComponent } from '../components/user-rating/user-rating';
import { UserSignupPage } from '../pages/user-signup/user-signup';
import { RateComponent } from '../components/rate/rate';
import { RatingComponent } from '../components/rating/rating';
import { RunCreate1Page } from '../pages/run-create1/run-create1';
import { RunCreate2Page } from '../pages/run-create2/run-create2';
import { RunCreate3Page } from '../pages/run-create3/run-create3';
import { RunCreate4Page } from '../pages/run-create4/run-create4';
import { RunDetailsComponent } from '../components/run-details/run-details';
import { VehicleDetailsComponent } from '../components/vehicle-details/vehicle-details';
import { ViewRatingsPage } from '../pages/view-ratings/view-ratings';

import { AuthProvider } from "../providers/auth";

import { AccountService } from '../services/account.service';
import { AddressService } from '../services/address.service';
import { ImgService } from '../services/image.service';
import { MessageService } from '../services/message.service';
import { RunService } from '../services/run.service';
import { RunPriceService } from '../services/runprice.service';
import { TransactionService } from '../services/transaction.service';
import { UserService } from '../services/user.service';

export function authHttpServiceFactory(http: Http, options: RequestOptions, storage: Storage) {
  const authConfig = new AuthConfig({ tokenGetter: (() => storage.get('jwt')), });
  return new AuthHttp(authConfig, http, options);
}

@NgModule({
  declarations: [
    BookRunPage,
    MyApp,
    HomePage,
    ManagedRunDetailsComponent,
    ManageRunsPage,
    MenuBannedComponent,
    MenuConnectedComponent,
    MenuNotConnectedComponent,
    MessageComponent,
    MessagesComponent,
    SearchResultPage,
    SignupPage,
    UserProfileComponent,
    UserProfilePage,
    UserRatingComponent,
    UserSignupPage,
    RateComponent,
    RatingComponent,
    RunCreate1Page,
    RunCreate2Page,
    RunCreate3Page,
    RunCreate4Page,
    AddressForm,
    RunDetailsComponent,
    VehicleDetailsComponent,
    ViewRatingsPage
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
    }),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCKbbwApwgV7meJUzaRLKWIRrgjqDONEDw'
    })
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    BookRunPage,
    MyApp,
    HomePage,
    ManagedRunDetailsComponent,
    ManageRunsPage,
    MessageComponent,
    MessagesComponent,
    SearchResultPage,
    SignupPage,
    UserProfileComponent,
    UserProfilePage,
    UserRatingComponent,
    UserSignupPage,
    RateComponent,
    RatingComponent,
    RunCreate1Page,
    RunCreate2Page,
    RunCreate3Page,
    RunCreate4Page,
    RunDetailsComponent,
    VehicleDetailsComponent,
    ViewRatingsPage
  ],
  providers: [
    AuthProvider,
    Camera,
    FileTransfer,
    JwtHelper,
    { provide: AuthHttp, useFactory: authHttpServiceFactory, deps: [Http, RequestOptions, Storage] },
    StatusBar,
    SplashScreen,
    { provide: ErrorHandler, useClass: IonicErrorHandler },
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
export class AppModule { }
