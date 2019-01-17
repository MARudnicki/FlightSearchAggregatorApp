package com.games.toki.pot.service.repository;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class BusinessFlightsRepositoryTest {

	@Test
	public void testSearchBusinessFlightsUsingBusinessEndpoint() {

		BusinessFlightRepository businessFlightsRepository = new BusinessFlightRepository("https://obscure-caverns-79008.herokuapp.com", 30_000, 30_000);
		assertNotNull(businessFlightsRepository.searchAvailableFlights());

	}

}
