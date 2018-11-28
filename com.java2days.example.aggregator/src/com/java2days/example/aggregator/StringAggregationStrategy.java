package com.java2days.example.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class StringAggregationStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
			return newExchange;
		}

		String oldText = oldExchange.getIn().getBody(String.class);
		String newText = newExchange.getIn().getBody(String.class);

		// just avoid bad inputs by assuming its a 0 value
		String agregatedText = String.join(",", oldText, newText);
		oldExchange.getIn().setBody(agregatedText);

		return oldExchange;
	}

}
