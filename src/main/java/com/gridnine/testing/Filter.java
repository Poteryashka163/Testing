package com.gridnine.testing;

import java.util.List;

public interface Filter {
    List<Flight> apply(List<Flight> flights);
}
