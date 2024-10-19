package com.gridnine.testing;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class GroundTimeExceedsTwoHoursFilter implements Filter {
    private static final Duration MAX_GROUND_TIME = Duration.ofHours(2);

    @Override
    public List<Flight> apply(List<Flight> flights) {
        return flights.stream()
                .filter(this::isGroundTimeWithinLimit)
                .collect(Collectors.toList());
    }

    // Метод для проверки, не превышает ли время на земле между сегментами допустимый лимит
    private boolean isGroundTimeWithinLimit(Flight flight) {
        List<Segment> segments = flight.getSegments();

        // Если всего один сегмент, то на земле времени нет, и его исключать не надо
        if (segments.size() < 2) {
            return true;
        }

        // Суммируем все промежутки времени на земле между прилетом одного сегмента и вылетом следующего
        Duration totalGroundTime = Duration.ZERO;
        for (int i = 1; i < segments.size(); i++) {
            Segment previousSegment = segments.get(i - 1);
            Segment currentSegment = segments.get(i);

            // Время на земле - это разница между прилетом предыдущего сегмента и вылетом следующего
            Duration groundTime = Duration.between(previousSegment.getArrivalDate(), currentSegment.getDepartureDate());

            // Суммируем общее время на земле
            totalGroundTime = totalGroundTime.plus(groundTime);
        }

        // Проверяем, не превышает ли суммарное время на земле лимит в 2 часа
        return totalGroundTime.compareTo(MAX_GROUND_TIME) <= 0;
    }
}
