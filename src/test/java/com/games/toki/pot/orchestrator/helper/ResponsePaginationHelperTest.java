package com.games.toki.pot.orchestrator.helper;


import com.games.toki.pot.controller.api.SearchCriteria;
import com.games.toki.pot.domain.model.Flight;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ResponsePaginationHelperTest {

	@Test
	public void testPagination() {

		List<Flight> flightList = new ArrayList<>();

		flightList.add(new Flight("id1", "a1", "b1", "dt1", "at1", "cheap"));
		flightList.add(new Flight("id2", "a2", "b2", "dt2", "at1", "cheap"));
		flightList.add(new Flight("id3", "a3", "b3", "dt3", "at1", "cheap"));
		flightList.add(new Flight("id4", "a4", "b4", "dt4", "at1", "cheap"));
		flightList.add(new Flight("id5", "a5", "b5", "dt5", "at1", "cheap"));
		flightList.add(new Flight("id6", "a6", "b6", "dt6", "at1", "cheap"));
		flightList.add(new Flight("id7", "a7", "b7", "dt7", "at1", "cheap"));
		flightList.add(new Flight("id8", "a8", "b8", "dt8", "at1", "cheap"));
		flightList.add(new Flight("id9", "a9", "b9", "dt9", "at1", "cheap"));
		flightList.add(new Flight("id10", "a10", "b10", "dt10", "at1", "cheap"));


		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setPageNumber("4");
		searchCriteria.setPageSize("2");

		Flux<Flight> page4 = new ResponsePaginationHelper().paginate(Flux.fromIterable(flightList), searchCriteria);
		assertNotNull(page4);
		assertTrue(page4.collectList().block().size() == 2);

	}

	@Test
	public void testPaginationWhenNegativeNumbersAreGiven() {

		List<Flight> flightList = new ArrayList<>();

		flightList.add(new Flight("id1", "a1", "b1", "dt1", "at1", "cheap"));
		flightList.add(new Flight("id2", "a2", "b2", "dt2", "at1", "cheap"));
		flightList.add(new Flight("id3", "a3", "b3", "dt3", "at1", "cheap"));
		flightList.add(new Flight("id4", "a4", "b4", "dt4", "at1", "cheap"));
		flightList.add(new Flight("id5", "a5", "b5", "dt5", "at1", "cheap"));
		flightList.add(new Flight("id6", "a6", "b6", "dt6", "at1", "cheap"));
		flightList.add(new Flight("id7", "a7", "b7", "dt7", "at1", "cheap"));
		flightList.add(new Flight("id8", "a8", "b8", "dt8", "at1", "cheap"));
		flightList.add(new Flight("id9", "a9", "b9", "dt9", "at1", "cheap"));
		flightList.add(new Flight("id10", "a10", "b10", "dt10", "at1", "cheap"));


		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setPageNumber("-4");
		searchCriteria.setPageSize("-2");

		Flux<Flight> page4 = new ResponsePaginationHelper().paginate(Flux.fromIterable(flightList), searchCriteria);
		assertNotNull(page4);
		assertTrue(page4.collectList().block().size() == 2);

	}

	@Test
	public void testPaginationWhenPageNumberAndPageSizeHigherThenAvailableResult() {

		List<Flight> flightList = new ArrayList<>();

		flightList.add(new Flight("id1", "a1", "b1", "dt1", "at1", "cheap"));
		flightList.add(new Flight("id2", "a2", "b2", "dt2", "at1", "cheap"));
		flightList.add(new Flight("id3", "a3", "b3", "dt3", "at1", "cheap"));
		flightList.add(new Flight("id4", "a4", "b4", "dt4", "at1", "cheap"));
		flightList.add(new Flight("id5", "a5", "b5", "dt5", "at1", "cheap"));
		flightList.add(new Flight("id6", "a6", "b6", "dt6", "at1", "cheap"));
		flightList.add(new Flight("id7", "a7", "b7", "dt7", "at1", "cheap"));
		flightList.add(new Flight("id8", "a8", "b8", "dt8", "at1", "cheap"));
		flightList.add(new Flight("id9", "a9", "b9", "dt9", "at1", "cheap"));
		flightList.add(new Flight("id10", "a10", "b10", "dt10", "at1", "cheap"));


		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setPageNumber("40");
		searchCriteria.setPageSize("15");

		Flux<Flight> page4 = new ResponsePaginationHelper().paginate(Flux.fromIterable(flightList), searchCriteria);
		assertNotNull(page4);
		assertTrue(page4.collectList().block().size() == 10);

	}

}
