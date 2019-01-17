package com.games.toki.pot.orchestrator.helper;

import com.games.toki.pot.controller.api.SearchCriteria;
import com.games.toki.pot.domain.model.Flight;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ResponseFilterHelperTest {

	@Test
	public void testFilterSearchResponseByGivenDepartureFromInCriteria() {
		List<Flight> flightList = new ArrayList<>();

		flightList.add(new Flight("id1", "a1", "b1", "dt1", "at1", "cheap"));

		flightList.add(new Flight("id2", "a2", "b2", "dt2", "at1", "cheap"));
		flightList.add(new Flight("id3", "a2", "b3", "dt3", "at1", "cheap"));
		flightList.add(new Flight("id4", "a2", "b4", "dt4", "at1", "cheap"));

		flightList.add(new Flight("id5", "a5", "b5", "dt5", "at1", "cheap"));
		flightList.add(new Flight("id6", "a6", "b6", "dt6", "at1", "cheap"));
		flightList.add(new Flight("id7", "a7", "b7", "dt7", "at1", "cheap"));
		flightList.add(new Flight("id8", "a8", "b8", "dt8", "at1", "cheap"));
		flightList.add(new Flight("id9", "a9", "b9", "dt9", "at1", "cheap"));
		flightList.add(new Flight("id10", "a10", "b10", "dt10", "at1", "cheap"));


		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setDepartureFrom("a2");

		Flux<Flight> fliteredFlightsFlux = new ResponseFilterHelper().filter(Flux.fromIterable(flightList), searchCriteria);

		assertNotNull(fliteredFlightsFlux);
		//Response only contains the flights for which departure from is "a2"
		assertTrue(fliteredFlightsFlux.collectList().block().size() == 3);

	}
}
