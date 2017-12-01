import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ManageRunsPage } from './manage-runs';

@NgModule({
  declarations: [
    ManageRunsPage,
  ],
  imports: [
    IonicPageModule.forChild(ManageRunsPage),
  ],
})
export class ManageRunsPageModule {}
