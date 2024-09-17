package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

class Flight {
    private final List<Segment> segments;

    Flight(List<Segment> segments) {
        this.segments = segments;
    }

    List<Segment> getSegments() {
        return segments;
    }

    @Override
    public String toString() {
        return segments.stream()
                .map(Segment::toString)
                .collect(Collectors.joining(" "));
    }
}