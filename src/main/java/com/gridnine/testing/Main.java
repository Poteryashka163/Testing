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

        // Фильтр 3: исключаем перелёты, где время на земле превышает 2 часа
        Filter groundTimeExceedsTwoHoursFilter = new GroundTimeExceedsTwoHoursFilter();
        List<Flight> flightsFilteredByGroundTime = groundTimeExceedsTwoHoursFilter.apply(flights);
        System.out.println("\nПерелёты, где общее время на земле не превышает 2 часов:");
        flightsFilteredByGroundTime.forEach(System.out::println);
    }
}
