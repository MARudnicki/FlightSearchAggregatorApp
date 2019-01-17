package com.games.toki.pot.orchestrator.helper;

import com.games.toki.pot.controller.api.SearchCriteria;
import com.games.toki.pot.domain.model.Flight;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class ResponseSortHelperTest {

	@Test
	public void testSortByDate() {

		List<Flight> flightList = new ArrayList<>();

		flightList.add(new Flight("id1", "a1", "b1", "dt1", "at1", "cheap"));
		flightList.add(new Flight("id2", "a2", "b2", "dt2", "at1", "cheap"));
		flightList.add(new Flight("id3", "a3", "b3", "dt3", "at1", "cheap"));
		flightList.add(new Flight("id4", "a4", "b4", "dt4", "at1", "cheap"));
		flightList.add(new Flight("id5", "a5", "b5", "dt5", "at1", "cheap"));
		flightList.add(new Flight("id6", "a6", "b6", "dt6", "at1", "business"));
		flightList.add(new Flight("id7", "a7", "b7", "dt7", "at1", "cheap"));
		flightList.add(new Flight("id8", "a8", "b8", "dt8", "at1", "cheap"));
		flightList.add(new Flight("id9", "a9", "b9", "dt9", "at1", "business"));
		flightList.add(new Flight("id10", "a10", "b10", "dt10", "at1", "cheap"));


		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setPageNumber("40");
		searchCriteria.setPageSize("15");
		searchCriteria.setSortBy("DepartureTime");

		Flux<Flight> sortedFlightsFlux = new ResponseSortHelper().sort(Flux.fromIterable(flightList), searchCriteria);

		assertNotNull(sortedFlightsFlux);
		assertEquals(sortedFlightsFlux.collectList().block().get(0).getId(), "id1");

	}

	@Test
	public void testSortByCategory() {
		List<Flight> flightList = new ArrayList<>();

		flightList.add(new Flight("id1", "a1", "b1", "dt1", "at1", "cheap"));
		flightList.add(new Flight("id2", "a2", "b2", "dt2", "at1", "cheap"));
		flightList.add(new Flight("id3", "a3", "b3", "dt3", "at1", "cheap"));
		flightList.add(new Flight("id4", "a4", "b4", "dt4", "at1", "cheap"));
		flightList.add(new Flight("id5", "a5", "b5", "dt5", "at1", "cheap"));
		flightList.add(new Flight("id6", "a6", "b6", "dt6", "at1", "business"));
		flightList.add(new Flight("id7", "a7", "b7", "dt7", "at1", "cheap"));
		flightList.add(new Flight("id8", "a8", "b8", "dt8", "at1", "cheap"));
		flightList.add(new Flight("id9", "a9", "b9", "dt9", "at1", "business"));
		flightList.add(new Flight("id10", "a10", "b10", "dt10", "at1", "cheap"));


		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setPageNumber("40");
		searchCriteria.setPageSize("15");
		searchCriteria.setSortBy("Category");

		Flux<Flight> sortedFlightsFlux = new ResponseSortHelper().sort(Flux.fromIterable(flightList), searchCriteria);

		assertNotNull(sortedFlightsFlux);
		assertEquals(sortedFlightsFlux.collectList().block().get(0).getId(), "id6");

	}
}
