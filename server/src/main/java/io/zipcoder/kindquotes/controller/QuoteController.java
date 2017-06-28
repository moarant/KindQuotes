package io.zipcoder.kindquotes.controller;

import io.zipcoder.kindquotes.model.Quote;
import io.zipcoder.kindquotes.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;

@RequestMapping("/quotes/")
@RestController
@CrossOrigin("http://localhost:8100")
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Quote>> getAllQuotes(){
        ArrayList<Quote> quotes = new ArrayList<>();
        quoteRepository.findAll().forEach(quotes::add);
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<Quote> get(@PathVariable("id") long id){
        Quote targetQuote= quoteRepository.findOne(id);
        if (targetQuote == null){

            return new ResponseEntity<Quote>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Quote>(targetQuote, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote){
        quoteRepository.save(quote);
        return new ResponseEntity<>(quote, HttpStatus.CREATED);

    }

    @RequestMapping(value= "/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Void> deleteQuote(@PathVariable("id") long id){
       quoteRepository.delete(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value= "/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Quote>editQuote(@PathVariable long id, @RequestBody Quote quote) {
        Quote targetQuote = quoteRepository.findOne(id);

        if (targetQuote == null) {
            return new ResponseEntity<Quote>(HttpStatus.NOT_FOUND);
        }

        targetQuote.setMessage(quote.getMessage());
        quoteRepository.save(quote);

        return new ResponseEntity<Quote>(targetQuote, HttpStatus.OK);
    }



}
