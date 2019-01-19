package com.games.toki.pot.service;

import com.games.toki.pot.service.repository.CheapFlightRepository;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CheapFlightServiceTest {
	@Test
	public void testSearch() {
		CheapFlightRepository repository = new CheapFlightRepository("https://obscure-caverns-79008.herokuapp.com", 30_000, 30_000);

		CheapFlightService CheapFlightService = new CheapFlightService(repository);

		assertNotNull(CheapFlightService.search());
	}	
}
