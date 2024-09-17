package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Segment {
    private final LocalDateTime departureDate;
    private final LocalDateTime arrivalDate;

    Segment(LocalDateTime departureDate, LocalDateTime arrivalDate) {
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    LocalDateTime getDepartureDate() {
        return departureDate;
    }

    LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return "[" + departureDate.format(fmt) + "|" + arrivalDate.format(fmt) + "]";
    }
}
