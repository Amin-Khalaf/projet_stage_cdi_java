import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { BookRunPage } from './book-run';

@NgModule({
  declarations: [
    BookRunPage,
  ],
  imports: [
    IonicPageModule.forChild(BookRunPage),
  ],
})
export class BookRunPageModule {}
