package com.games.toki.pot.service.response.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.games.toki.pot.service.response.BusinessFlight;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class BusinessFlightsResponseParserTest {

	@Test
	public void testParseHappyScenario() throws IOException {

		JsonNode jsonNode = new ObjectMapper().readTree("[{\"uuid\":\"b00068b2-aed9-4526-aff2-f1668af219df\",\"flight\":\"Almagro -> Brinkmann\",\"departure\":\"2019-01-16T18:20:12.688Z\",\"arrival\":\"2019-01-16T20:37:54.625Z\"}]");

		Flux<BusinessFlight> businessFlightFlux = BusinessFlightsResponseParser.parse(jsonNode);

		assertNotNull(businessFlightFlux);
		assertTrue(businessFlightFlux.collectList().block().size() == 1);
		assertEquals(businessFlightFlux.collectList().block().get(0).getDepartureFromArrivalTo(), "Almagro -> Brinkmann");

	}

	@Test
	public void testParseWhenResponseBlank() throws  IOException {
		JsonNode jsonNode = new ObjectMapper().readTree("[]");

		Flux<BusinessFlight> businessFlightFlux = BusinessFlightsResponseParser.parse(jsonNode);

		assertNotNull(businessFlightFlux);
		assertTrue(businessFlightFlux.collectList().block().size() == 0);
	}
}
