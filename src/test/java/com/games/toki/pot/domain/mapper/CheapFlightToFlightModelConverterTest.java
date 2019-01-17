package com.games.toki.pot.domain.mapper;


import com.games.toki.pot.domain.model.Flight;
import com.games.toki.pot.service.response.CheapFlight;
import org.junit.Test;
import reactor.core.publisher.Flux;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CheapFlightToFlightModelConverterTest {

	@Test
	public void testConvertHappyScenario() {


		CheapFlightToFlightModelConverter cheapFlightToFlightModelConverter
				= new CheapFlightToFlightModelConverter();
		Flux<CheapFlight> dummyCheapFlightsFlux = Flux.just(new CheapFlight("id", "dummyDepartureFrom", "dummyArritvalTo", "00000000000000000", "00000000000000000"));

		Flux<Flight> flightFlux = cheapFlightToFlightModelConverter.convert(dummyCheapFlightsFlux);
		assertNotNull(flightFlux);
		assertTrue(flightFlux.collectList().block().size() == 1);

	}
}
