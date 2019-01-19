package com.games.toki.pot.domain.mapper;

import com.games.toki.pot.domain.model.Flight;
import com.games.toki.pot.service.response.BusinessFlight;
import org.junit.Test;
import reactor.core.publisher.Flux;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mmundra on 1/17/19.
 */
public class BusinessFlightToFlightModelConverterTest {

	@Test
	public void testConvertHappyScenario() {

		Flux<BusinessFlight> dummyBusinessFlightsFlux = Flux.just(new BusinessFlight("id", "dummyDepartureFrom -> To", "dummyDepartureTime", "dummyArrivalTime"));

		BusinessFlightToFlightModelConverter converter =
				new BusinessFlightToFlightModelConverter();

		Flux<Flight> flightFlux = converter.convert(dummyBusinessFlightsFlux);

		assertNotNull(flightFlux);
		assertTrue(flightFlux.collectList().block().size() == 1);
	}
}
