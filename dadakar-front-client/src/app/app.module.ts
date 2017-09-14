import { AuthConfig, AuthHttp, JwtHelper } from 'angular2-jwt';
import { BrowserModule } from '@angular/platform-browser';
import { CustomFormsModule } from 'ng2-validation';
import { ErrorHandler, NgModule } from '@angular/core';
import { Http, HttpModule, RequestOptions } from "@angular/http";
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { IonicStorageModule, Storage } from '@ionic/storage';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { AuthProvider } from "../providers/auth";
import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { LoginPage} from '../pages/login/login';
import { SignupPage } from '../pages/signup/signup';
import { UserSignupPage } from '../pages/user-signup/user-signup';

import { UserService } from '../services/user.service';

export function authHttpServiceFactory(http: Http, options: RequestOptions, storage: Storage) {
    const authConfig = new AuthConfig({ tokenGetter: (() => storage.get('jwt')),});
    return new AuthHttp(authConfig, http, options);
}

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    LoginPage,
    SignupPage,
    UserSignupPage
  ],
  imports: [
    BrowserModule,
    CustomFormsModule,
    HttpModule,
    IonicModule.forRoot(MyApp),
    IonicStorageModule.forRoot({
        name: "dadakar",
        driverOrder: ['sqlite', 'indexeddb', 'websql']
    })
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    LoginPage,
    SignupPage,
    UserSignupPage
  ],
  providers: [
    AuthProvider,
    JwtHelper,
    {provide: AuthHttp, useFactory: authHttpServiceFactory, deps: [Http, RequestOptions, Storage]},
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    UserService
  ]
})
export class AppModule {}
