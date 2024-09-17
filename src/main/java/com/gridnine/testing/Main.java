package com.gridnine.testing;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // Получаем тестовые данные
        List<Flight> flights = FlightBuilder.createFlights();

        // 1. Фильтр вылетов в прошлом
        Filter departureBeforeNowFilter = new DepartureBeforeNowFilter();
        List<Flight> flightsFilteredByDeparture = departureBeforeNowFilter.apply(flights);
        System.out.println("Перелёты без вылетов в прошлом:");
        flightsFilteredByDeparture.forEach(System.out::println);

        // 2. Фильтр сегментов с прилётом раньше вылета
        Filter arrivalBeforeDepartureFilter = new ArrivalBeforeDepartureFilter();
        List<Flight> flightsFilteredByArrival = arrivalBeforeDepartureFilter.apply(flights);
        System.out.println("\nПерелёты без сегментов с прилётом раньше вылета:");
        flightsFilteredByArrival.forEach(System.out::println);

        // 3. Фильтр перелётов с временем на земле более двух часов
        Filter groundTimeExceedsFilter = new GroundTimeExceedsFilter();
        List<Flight> flightsFilteredByGroundTime = groundTimeExceedsFilter.apply(flights);
        System.out.println("\nПерелёты без времени на земле более двух часов:");
        flightsFilteredByGroundTime.forEach(System.out::println);
    }
}
