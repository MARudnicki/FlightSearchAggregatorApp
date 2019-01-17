package com.games.toki.pot.service.aggregator;

import com.games.toki.pot.domain.mapper.BusinessFlightToFlightModelConverter;
import com.games.toki.pot.domain.mapper.CheapFlightToFlightModelConverter;
import com.games.toki.pot.domain.model.Flight;
import com.games.toki.pot.service.response.BusinessFlight;
import com.games.toki.pot.service.response.CheapFlight;
import org.junit.Test;
import reactor.core.publisher.Flux;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mmundra on 1/17/19.
 */
public class FlightResponseConverterAndAggregatorTest {

	@Test
	public void testConvertAndAggregate() {
		FlightResponseConverterAndAggregator flightResponseConverterAndAggregator
				= new FlightResponseConverterAndAggregator(
						new CheapFlightToFlightModelConverter(),
						new BusinessFlightToFlightModelConverter());

		Flux<CheapFlight> dummyCheapFlightsFlux = Flux.just(new CheapFlight("id", "dummyDepartureFrom", "dummyArritvalTo", "00000000000000000", "00000000000000000"));
		Flux<BusinessFlight> dummyBusinessFlightsFlux = Flux.just(new BusinessFlight("id", "dummyDepartureFrom -> To", "dummyDepartureTime", "dummyArrivalTime"));


		Flux<Flight> flightsResponseFlux =
					flightResponseConverterAndAggregator.convertAndAggregate(dummyCheapFlightsFlux, dummyBusinessFlightsFlux);

		assertNotNull(flightsResponseFlux);
		assertTrue(flightsResponseFlux.collectList().block().size() == 2);

	}
}
