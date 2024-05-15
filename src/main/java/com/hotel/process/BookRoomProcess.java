package com.hotel.process;

import com.hotel.model.Booking;
import com.hotel.model.Customer;
import com.hotel.model.Hotel;
import com.hotel.model.Room;
import com.hotel.utils.CustomerInputUtils;
import com.hotel.utils.GetDateInputUtils;
import com.hotel.utils.GetChoiceInputUtils;
import com.hotel.utils.RoomInputUtils;
import lombok.RequiredArgsConstructor;
import org.javatuples.Pair;

import java.time.Instant;
import java.util.Scanner;

@RequiredArgsConstructor
public class BookRoomProcess implements Process {

    private final Hotel hotel;
    private final Scanner scanner;

    public void run() {
        final var room = getRoom();
        final var dates = GetDateInputUtils.getDates(scanner);
        if (this.isRoomIsFree(dates, room)) {
            System.out.println("Pokoj jest już zajęty w tym terminie");
            return;
        }
        final var booking = this.bookRoom(room, dates);
        this.printInfo(booking);
        this.tryPayment(booking);
    }

    private void tryPayment(Booking booking) {
        final var choice = GetChoiceInputUtils.getBooleanChoice(scanner, "Czy chcesz dokonać płatności teraz? Wpisz Tak lub Nie");
        if (choice) {
            final var paymentProcess = new PaymentProcess(hotel, scanner);
            paymentProcess.run(booking);
        }
    }

    private Room getRoom() {
        // tu nie działa, zapetla się -> 16 , 10
        var roomData = RoomInputUtils.getRoom(scanner);
        var room = hotel.getRoom(roomData);
        while (room.isEmpty()) {
            System.out.println("Pokoj nie istnieje, podaj inny numer");
            roomData = RoomInputUtils.getRoom(scanner);
            room = hotel.getRoom(roomData);
        }
        return room.get();
    }

    private void printInfo(Booking booking) {
        System.out.println("Liczba dni: " + booking.getDuration());
        System.out.println("Cena za noc: " + booking.getPricePerDay());
        System.out.println("Cena całkowita: " + booking.getTotalPrice());
        System.out.println("Nr rezerwacji: " + booking.getBookingId());
        System.out.println("UWAGA! Nr rezerwacji jest koniecy przy płatności oraz odwałaniu, zapisz go!");
        System.out.println();
        System.out.println();
    }

    private Booking bookRoom(Room room, Pair<Instant, Instant> dates) {
        final var customer = getCustomer();
        return room.bookRoomInDates(dates, customer);
    }

    private Customer getCustomer() {
        final var customerData = CustomerInputUtils.getCustomer(scanner);
        return hotel.getExistingOrSaveNewCustomer(customerData);
    }

    private boolean isRoomIsFree(Pair<Instant, Instant> dates, Room room) {
        final var freeRooms = hotel.findFreeRoomInDates(dates.getValue0(), dates.getValue1());
        return freeRooms.stream().noneMatch(r -> r.isNumber(room.roomNumber()));
    }
}
