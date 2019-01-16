package com.games.toki.pot.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Flight {
	private String id;
	private String departureFrom;
	private String arrivalTo;

	private String departureTime;
	private String arrivalTime;
	private String category;

	public Flight(String id, String departureFrom, String arrivalTo, String departureTime, String arrivalTime, String category) {
		this.id = id;
		this.departureFrom = departureFrom;
		this.arrivalTo = arrivalTo;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.category = category;
	}
}
