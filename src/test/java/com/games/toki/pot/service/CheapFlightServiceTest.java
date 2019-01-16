package com.games.toki.pot.service;

import com.games.toki.pot.service.repository.CheapFlightRepository;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CheapFlightServiceTest {
	@Test
	public void testSearch() {
		CheapFlightRepository repository = new CheapFlightRepository();

		CheapFlightService CheapFlightService = new CheapFlightService(repository);

		assertNotNull(CheapFlightService.search());
	}	
}
