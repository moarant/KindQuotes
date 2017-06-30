import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import {HomePage} from './../home/home';
import {HomeService} from './../home/homeService';

/**
 * Generated class for the EditAQuotePage page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@IonicPage()
@Component({
  selector: 'page-edit-a-quote',
  templateUrl: 'edit-a-quote.html',
  providers: [HomeService]
})
export class EditAQuotePage {

  quote={
    id: 0, 
    message: ""
  }

  constructor(public navCtrl: NavController, public navParams: NavParams, public homeService: HomeService) {
  this.quote.id = navParams.get('quoteId');
  homeService.getQuote(this.quote.id).subscribe(data => {
    this.quote.message= data.message;
  })
}

  ionViewDidLoad() {
    console.log('ionViewDidLoad EditAQuotePage');
  }

  editQuote(){
    this.homeService.updateQuote(this.quote)
    .map(res=> res.toString())
    .subscribe(data =>{
      console.log(data);
      this.navCtrl.pop();
    });
  }

}
