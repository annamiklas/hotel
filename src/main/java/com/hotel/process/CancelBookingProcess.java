package com.hotel.process;

import com.hotel.utils.GetLongInputUtils;
import com.hotel.model.Booking;
import com.hotel.model.Hotel;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

@RequiredArgsConstructor
public class CancelBookingProcess implements Process {

    private final Hotel hotel;
    private final Scanner scanner;

    public void run() {
        this.printInfo();
        final var booking = getBooking();
        if (this.isCancellationDeadlinePast(booking)) {
            return;
        }
        this.printInfoForPaidBooking(booking);
        hotel.cancelBooking(booking.getBookingId());
        System.out.println("Rezerwacja została anulowana!");
    }

    private Booking getBooking() {
        final var bookingNumber = GetLongInputUtils.getNumber(scanner, "Podaj numer rezerwacji: ");
        final var booking = hotel.findBooking(bookingNumber);
        return booking.orElseGet(() -> {
            System.out.println("Podana rezerwacja nie istanieje, wpisz inne id!");
            return this.getBooking();
        });
    }

    private boolean isCancellationDeadlinePast(Booking booking) {
        final var daysBetween = ChronoUnit.DAYS.between(Instant.now(), booking.getFromDate());
        if (daysBetween < 3) {
            System.out.println("Rezerwacja zaczyna się za mniej niż 3 dni, odwołanie jest niemożliwe!");
            return true;
        }
        return false;
    }

    private void printInfoForPaidBooking(Booking booking) {
        if (booking.isPaid()) {
            System.out.println("Rezerwacja została już opłacona, skontaktuj się z biorem obsługi kielnta w celu zwortu pieniędzy. Nr tel: +48 123 456 789");
        }
    }

    private void printInfo() {
        System.out.println("Odawołanie rezerwacji jest możwliwe do 3 dni przed poczatkiem pobytu.");
    }
}
