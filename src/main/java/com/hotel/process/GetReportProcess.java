package com.hotel.process;

import com.hotel.utils.GetDateInputUtils;
import com.hotel.model.Booking;
import com.hotel.model.Hotel;
import com.hotel.model.Room;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public class GetReportProcess implements Process {

    private final Hotel hotel;
    private final Scanner scanner;

    public void run() {
        final var dates = GetDateInputUtils.getDates(scanner);
        final var report = hotel.getReport(dates);
        this.printReport(report);
    }

    private void printReport(Map<Room, List<Booking>> bookingReport) {
        final var totalNumber = bookingReport.values().stream().mapToInt(List::size).sum();
        final var totalPrice = bookingReport.values().stream().flatMap(Collection::stream).map(Booking::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println();
        bookingReport.keySet().forEach(r -> {
            System.out.println(r);
            r.bookings().forEach(b -> System.out.println("\t -> \t" + b));
        });
        System.out.println();
        System.out.println("Podsumowanie");
        System.out.println("Liczba wszytskich rezwerwacij: " + totalNumber);
        System.out.println("Kwota wszystkich rezwerwacij: " +  totalPrice);
        System.out.println();
        System.out.println();
    }
}
