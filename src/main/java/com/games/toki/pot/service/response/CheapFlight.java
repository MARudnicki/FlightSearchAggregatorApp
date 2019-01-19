package com.games.toki.pot.service.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheapFlight {
	private String id;
	private String departureFrom;
	private String arrivalTo;

	private String departureTime;
	private String arrivalTime;

	public CheapFlight(String id, String departureFrom, String arrivalTo, String departureTime, String arrivalTime) {
		this.id = id;
		this.departureFrom = departureFrom;
		this.arrivalTo = arrivalTo;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}
}