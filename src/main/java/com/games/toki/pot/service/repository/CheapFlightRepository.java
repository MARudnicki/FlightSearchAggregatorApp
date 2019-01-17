package com.games.toki.pot.service.repository;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.toki.pot.service.response.CheapFlight;
import com.games.toki.pot.service.response.parser.CheapFlightsResponseParser;
import com.games.toki.pot.util.WebClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Repository
public class CheapFlightRepository {

	private final WebClient webClient;

	public CheapFlightRepository(@Value("${cheapTypeUrl}") String baseURL,
									@Value("${timeOutConnect}") int connectionTimeout,
									@Value("${timeOutRead}") int readTimeout) {

		webClient = WebClientUtil.build(baseURL, connectionTimeout, readTimeout);

	}

	public Flux<CheapFlight> searchAvailableFlights() {

		JsonNode jsonNode = null;
		try {
			jsonNode = new ObjectMapper().readTree(webClient.get().uri("/cheap")
					.exchange().block()
					.bodyToFlux(String.class).blockLast());
		} catch (IOException e) {
			System.out.print(e.getStackTrace());
		}

		return CheapFlightsResponseParser.parse(jsonNode);
	}
}
