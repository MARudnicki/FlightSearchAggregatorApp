package com.games.toki.pot.orchestrator.cache;

import com.games.toki.pot.domain.model.Flight;
import org.easymock.EasyMock;
import org.junit.Test;
import reactor.core.publisher.Flux;

import static org.junit.Assert.*;

public class FlightsResponseLocalStoreTest {

	@Test
	public void testSaveAndGetWithIsCachedDataAvailaiblityCheck() {
		FlightsResponseLocalStore flightsResponseLocalStore =
				new FlightsResponseLocalStore();

		assertFalse(flightsResponseLocalStore.isCacheNotEmpty());

		Flux<Flight> flightFlux = Flux.just(EasyMock.createMock(Flight.class));
		flightsResponseLocalStore.save(flightFlux);

		assertTrue(flightsResponseLocalStore.isCacheNotEmpty());

		assertNotNull(flightsResponseLocalStore.get());
		assertTrue(flightsResponseLocalStore.get().collectList().block().size() == 1);

	}
}
