package com.games.toki.pot.service.response.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.toki.pot.service.response.CheapFlight;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class CheapFlightsResponseParserTest {

	@Test
	public void testParseHappyScenario() throws IOException {

		JsonNode jsonNode = new ObjectMapper().readTree("[{\"id\":\"1234\",\"departure\":\"Almagro\",\"arrival\":\"Brinkmann\",\"departureTime\":\"000000000000000000\",\"arrivalTime\":\"000000000000000000\"}]");

		Flux<CheapFlight> cheapFlightFlux = CheapFlightsResponseParser.parse(jsonNode);

		assertNotNull(cheapFlightFlux);
		assertTrue(cheapFlightFlux.collectList().block().size() == 1);
		assertEquals(cheapFlightFlux.collectList().block().get(0).getDepartureFrom(), "Almagro");

	}

}
