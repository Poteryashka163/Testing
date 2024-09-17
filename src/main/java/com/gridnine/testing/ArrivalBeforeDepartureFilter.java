package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class ArrivalBeforeDepartureFilter implements Filter {
    @Override
    public List<Flight> apply(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}
