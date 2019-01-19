package com.games.toki.pot.controller.api;

import com.games.toki.pot.domain.model.Flight;
import com.games.toki.pot.orchestrator.FlightSearchOrchestrator;
import org.easymock.EasyMock;
import org.junit.Test;
import reactor.core.publisher.Flux;

import static org.junit.Assert.assertNotNull;

/**
 * Created by mmundra on 1/17/19.
 */
public class FlightSearchControllerTest {

	@Test
	public void testSearchHappyScenario() {

		Flux<Flight> flightFlux = Flux.just(EasyMock.createMock(Flight.class));

		FlightSearchOrchestrator flightSearchOrchestrator = EasyMock.createMock(FlightSearchOrchestrator.class);
		EasyMock.expect(flightSearchOrchestrator.searchFlights(EasyMock.anyObject(SearchCriteria.class))).andReturn(flightFlux).once();
		EasyMock.replay(flightSearchOrchestrator);

		FlightSearchController flightSearchController = new FlightSearchController(flightSearchOrchestrator);
		assertNotNull(flightSearchController.searchFlights(new SearchCriteria()));

	}

	@Test
	public void testSearchHappyScenarioWhenSearchCriteriaNULL() {

		Flux<Flight> flightFlux = Flux.just(EasyMock.createMock(Flight.class));

		FlightSearchOrchestrator flightSearchOrchestrator = EasyMock.createMock(FlightSearchOrchestrator.class);
		EasyMock.expect(flightSearchOrchestrator.searchFlights(EasyMock.anyObject(SearchCriteria.class))).andReturn(flightFlux).once();
		EasyMock.replay(flightSearchOrchestrator);

		FlightSearchController flightSearchController = new FlightSearchController(flightSearchOrchestrator);
		assertNotNull(flightSearchController.searchFlights(null));

	}
}
