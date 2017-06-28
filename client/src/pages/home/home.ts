import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import {HomeService} from './homeService';
import {Http} from '@angular/http';
import { EditAQuotePage } from "../edit-a-quote/edit-a-quote";

@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
  providers:[HomeService]
})
export class HomePage {

  quotes: any;
   
  constructor(public navCtrl: NavController, public homeService:HomeService) {
    homeService.getAllQuotes().subscribe(data =>{
      this.quotes = data;
    })
  }

   quote = {
    id: Number,
    message: ""
  }

  postQuote(){
    this.homeService.postQuote(this.quote).map(res => res.json())
        .subscribe(data => {
            console.log(data);
            this.getQuotes();
        });
        
  }

  getQuotes(){
      this.homeService.getAllQuotes().subscribe(data =>{
      this.quotes = data;
  })
  }
  
  
  deleteQuote(id: Number){
    this.homeService.deleteQuote(id).subscribe(data => {
     console.log(data);
     this.getQuotes();
   });
   
    
  }

  updateQuote(id: Number){
    this.navCtrl.push(EditAQuotePage, {quoteId: id})
    console.log(id+ " pushed");
  }

  ionViewWillEnter(){
    this.getQuotes();
  }

 

  

  

  
 

}
