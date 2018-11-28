package com.java2days.example.splitter;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class SplitAggregateStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if(oldExchange==null) {
			return newExchange;
		}
		String oldWord = oldExchange.getIn().getBody(String.class);
		String newWord = newExchange.getIn().getBody(String.class);
		oldWord = oldWord.concat(newWord);
		oldExchange.getIn().setBody(oldWord);		
		return oldExchange;
	}

}
