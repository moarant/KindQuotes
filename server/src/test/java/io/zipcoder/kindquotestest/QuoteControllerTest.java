package io.zipcoder.kindquotestest;

import io.zipcoder.kindquotes.config.QuoteConfig;
import io.zipcoder.kindquotes.controller.QuoteController;
import io.zipcoder.kindquotes.model.Quote;
import io.zipcoder.kindquotes.repository.QuoteRepository;
import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

/**
 * Created by mollyarant on 6/28/17.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={QuoteConfig.class})

public class QuoteControllerTest {
    private MockMvc mockMvc;

    @Mock
    private QuoteRepository quoteRepository;
    @InjectMocks
    private QuoteController quoteController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(quoteController)
                .addFilters(new CorsFilter())
                .build();
    }

   @Test
    public void testGetAll() throws Exception{
        List<Quote> quotes= Arrays.asList(
                new Quote(1, "You are cool"),
                new Quote(2, "This is a kind quote"));
        when(quoteController.getAllQuotes()).thenReturn((ResponseEntity<ArrayList<Quote>>) quotes);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is (1)))
                .andExpect(jsonPath("$[0].message", is("You are cool")));



   }





}
