package com.gridnine.testing;

import java.util.List;

public class FlightFilterManager {

    private final List<Filter> filters;

    public FlightFilterManager(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Flight> applyFilters(List<Flight> flights) {
        for (Filter filter : filters) {
            flights = filter.apply(flights);
        }
        return flights;
    }
}
