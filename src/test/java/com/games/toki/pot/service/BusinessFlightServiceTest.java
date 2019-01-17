package com.games.toki.pot.service;


import com.games.toki.pot.service.repository.BusinessFlightRepository;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class BusinessFlightServiceTest {

	@Test
	public void testSearch() {
		BusinessFlightRepository repository = new BusinessFlightRepository("https://obscure-caverns-79008.herokuapp.com", 30_000, 30_000);

		BusinessFlightService businessFlightService = new BusinessFlightService(repository);

		assertNotNull(businessFlightService.search());
	}
}
