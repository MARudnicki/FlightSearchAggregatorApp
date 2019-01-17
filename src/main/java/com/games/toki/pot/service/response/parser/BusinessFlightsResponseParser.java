package com.games.toki.pot.service.response.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.games.toki.pot.service.response.BusinessFlight;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;


public class BusinessFlightsResponseParser {

	public static Flux<BusinessFlight> parse(JsonNode jsonNode) {

		List<BusinessFlight> businessFlightList = new ArrayList<>();
		if(jsonNode != null) {
			jsonNode.forEach(node -> {
				String id = node.get("uuid").asText();
				String departureFromArrivalTo = node.get("flight").asText();
				String departureTime = node.get("departure").asText();
				String arrivalTime = node.get("arrival").asText();

				businessFlightList.add(new BusinessFlight(id, departureFromArrivalTo, departureTime, arrivalTime));
			});
		}

		return Flux.fromIterable(businessFlightList);
	}
}
