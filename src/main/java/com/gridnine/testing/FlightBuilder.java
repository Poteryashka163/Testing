package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FlightBuilder {
    public static List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
                // Обычный перелёт с длительностью 2 часа
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                // Обычный перелёт с двумя сегментами
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                // Перелёт, вылетающий в прошлом
                createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                // Перелёт с вылетом позже прилёта
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
                // Перелёт с более чем двумя часами на земле
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                // Другой перелёт с более чем двумя часами на земле
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7))
        );
    }

    private static Flight createFlight(LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException("Необходимо передать чётное количество дат");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < dates.length - 1; i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }
}
