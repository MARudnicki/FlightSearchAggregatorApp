package com.games.toki.pot.service.repository;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CheapFlightsRepositoryTest {

	@Test
	public void testSearchBusinessFlightsUsingBusinessEndpoint() {

		CheapFlightRepository cheapFlightsRepository = new CheapFlightRepository("https://obscure-caverns-79008.herokuapp.com", 30_000, 30_000);
		assertNotNull(cheapFlightsRepository.searchAvailableFlights());

	}

}
