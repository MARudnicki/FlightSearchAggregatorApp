package com.games.toki.pot.orchestrator;

import com.games.toki.pot.controller.api.SearchCriteria;
import com.games.toki.pot.domain.mapper.BusinessFlightToFlightModelConverter;
import com.games.toki.pot.domain.mapper.CheapFlightToFlightModelConverter;
import com.games.toki.pot.orchestrator.cache.FlightsResponseLocalStore;
import com.games.toki.pot.orchestrator.helper.ResponseFilterHelper;
import com.games.toki.pot.orchestrator.helper.ResponsePaginationHelper;
import com.games.toki.pot.orchestrator.helper.ResponseSortHelper;
import com.games.toki.pot.service.BusinessFlightService;
import com.games.toki.pot.service.CheapFlightService;
import com.games.toki.pot.service.aggregator.FlightResponseConverterAndAggregator;
import com.games.toki.pot.service.response.BusinessFlight;
import com.games.toki.pot.service.response.CheapFlight;
import org.easymock.EasyMock;
import org.junit.Test;
import reactor.core.publisher.Flux;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FlightSearchOrchestratorTest {

	@Test
	public void testOrchestrationHappyScenario() {

		CheapFlightService cheapFlightServiceMock = EasyMock.createMock(CheapFlightService.class);
		BusinessFlightService businessFlightServiceMock = EasyMock.createMock(BusinessFlightService.class);

		Flux<CheapFlight> dummyCheapFlightsFlux = Flux.just(new CheapFlight("id", "dummyDepartureFrom", "dummyArritvalTo", "00000000000000000", "00000000000000000"));
		EasyMock.expect(cheapFlightServiceMock.search()).andReturn(dummyCheapFlightsFlux).anyTimes();

		Flux<BusinessFlight> dummyBusinessFlightsFlux = Flux.just(new BusinessFlight("id", "dummyDepartureFrom -> To", "dummyDepartureTime", "dummyArrivalTime"));
		EasyMock.expect(businessFlightServiceMock.search()).andReturn(dummyBusinessFlightsFlux).anyTimes();

		EasyMock.replay(cheapFlightServiceMock, businessFlightServiceMock);

		FlightSearchOrchestrator flightSearchOrchestrator = new FlightSearchOrchestrator(
				cheapFlightServiceMock, businessFlightServiceMock,
				new FlightResponseConverterAndAggregator(new CheapFlightToFlightModelConverter(), new BusinessFlightToFlightModelConverter()),
				new FlightsResponseLocalStore(),
				new ResponseFilterHelper(),
				new ResponseSortHelper(),
				new ResponsePaginationHelper());

		assertNotNull(flightSearchOrchestrator.searchFlights(new SearchCriteria()));
		assertTrue(flightSearchOrchestrator.searchFlights(new SearchCriteria()).collectList().block().size() == 2);
	}
}
