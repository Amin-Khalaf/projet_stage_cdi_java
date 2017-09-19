import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { HomeConnectedPage } from './home-connected';

@NgModule({
  declarations: [
    HomeConnectedPage,
  ],
  imports: [
    IonicPageModule.forChild(HomeConnectedPage),
  ],
})
export class HomeConnectedPageModule {}
