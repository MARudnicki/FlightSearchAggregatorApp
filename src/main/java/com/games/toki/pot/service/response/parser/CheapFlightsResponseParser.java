package com.games.toki.pot.service.response.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.games.toki.pot.service.response.CheapFlight;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmundra on 1/16/19.
 */
public class CheapFlightsResponseParser {

	public static Flux<CheapFlight> parse(JsonNode jsonNode) {

		List<CheapFlight> cheapFlightList = new ArrayList<>();
		if(jsonNode != null) {
			jsonNode.forEach(node -> {
				String id = node.get("id").asText();
				String departure = node.get("departure").asText();
				String arrival = node.get("arrival").asText();
				String departureTime = node.get("departureTime").asText();
				String arrivalTime = node.get("arrivalTime").asText();

				cheapFlightList.add(new CheapFlight(id, departure, arrival, departureTime, arrivalTime));
			});
		}

		return Flux.fromIterable(cheapFlightList);
	}
}
