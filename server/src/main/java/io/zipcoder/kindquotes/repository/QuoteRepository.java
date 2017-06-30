package io.zipcoder.kindquotes.repository;

import io.zipcoder.kindquotes.model.Quote;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mollyarant on 6/24/17.
 */
public interface QuoteRepository extends CrudRepository<Quote, Long> {
//    void update(Quote quote);
}
