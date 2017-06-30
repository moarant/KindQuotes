import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { EditAQuotePage } from './edit-a-quote';

@NgModule({
  declarations: [
    EditAQuotePage,
  ],
  imports: [
    IonicPageModule.forChild(EditAQuotePage),
  ],
  exports: [
    EditAQuotePage
  ]
})
export class EditAQuotePageModule {}
