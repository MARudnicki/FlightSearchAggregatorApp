package com.games.toki.pot.service;


import com.games.toki.pot.service.repository.BusinessFlightRepository;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class BusinessFlightServiceTest {

	@Test
	public void testSearch() {
		BusinessFlightRepository repository = new BusinessFlightRepository();

		BusinessFlightService businessFlightService = new BusinessFlightService(repository);

		assertNotNull(businessFlightService.search());
	}
}
