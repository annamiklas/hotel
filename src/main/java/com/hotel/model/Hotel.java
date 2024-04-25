package com.hotel.model;

import org.javatuples.Pair;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

import static com.hotel.model.Type.DELUXE;
import static com.hotel.model.Type.STANDARD;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Hotel {
    private final List<Room> rooms;
    private final ArrayList<Customer> customers;

    public Hotel() {
        this.rooms = getRooms();
        this.customers = new ArrayList<>();
    }

    private static List<Room> getRooms() {
        final var customer = new Customer(0L, "Ania", "M", "aaaa@aaa.pl", 123456);
        final var room1 = new Room(1, 1, 1, new BigDecimal(75), STANDARD, new ArrayList<>());
        final var booking = new Booking(0L, customer, room1, Instant.parse("2024-07-01T00:00:00.0000Z"),
                Instant.parse("2024-07-03T00:00:00.0000Z"), new BigDecimal(150), false);
        room1.bookings().add(booking);
        return List.of(room1,
                new Room(2, 1, 1, new BigDecimal(75), STANDARD, new ArrayList<>()),
                new Room(3, 1, 1, new BigDecimal(120), DELUXE, new ArrayList<>()),
                new Room(4, 1, 2, new BigDecimal(110), STANDARD, new ArrayList<>()),
                new Room(5, 1, 2, new BigDecimal(110), STANDARD, new ArrayList<>()),
                new Room(6, 1, 2, new BigDecimal(110), STANDARD, new ArrayList<>()),
                new Room(7, 2, 2, new BigDecimal(110), STANDARD, new ArrayList<>()),
                new Room(8, 2, 2, new BigDecimal(110), STANDARD, new ArrayList<>()),
                new Room(9, 2, 2, new BigDecimal(170), DELUXE, new ArrayList<>()),
                new Room(10, 2, 2, new BigDecimal(170), DELUXE, new ArrayList<>()),
                new Room(11, 2, 3, new BigDecimal(150), STANDARD, new ArrayList<>()),
                new Room(12, 2, 3, new BigDecimal(150), STANDARD, new ArrayList<>()),
                new Room(13, 2, 3, new BigDecimal(210), DELUXE, new ArrayList<>()),
                new Room(14, 2, 4, new BigDecimal(180), STANDARD, new ArrayList<>()),
                new Room(15, 2, 4, new BigDecimal(250), DELUXE, new ArrayList<>()));
    }

    public List<Room> findFreeRoomInDates(final Instant from, final Instant to) {
        return this.rooms.stream().filter(r -> r.hasNotAnyBookingInDates(from, to)).toList();
    }

    public Optional<Room> getRoom(final Integer roomNumber) {
        return this.rooms.stream().filter(r -> r.isNumber(roomNumber)).findAny();
    }

    public Customer getExistingOrSaveNew(final Customer customer) {
        final var existing = this.customers.stream().filter(c -> c.email().equals(customer.email())).findAny();
        if (existing.isPresent()) {
            return existing.get();
        }
        customers.add(customer);
        return customer;
    }

    public Optional<Booking> findBooking(long bookingNumber) {
        final var room = this.rooms.stream().filter(r -> r.hasBooking(bookingNumber)).findFirst();
        return room.flatMap(value -> value.bookings().stream()
                .filter(b -> b.getBookingId().equals(bookingNumber))
                .findAny());
    }

    public void cancelBooking(long bookingNumber) {
        final var room = this.rooms.stream().filter(r -> r.hasBooking(bookingNumber)).findFirst().orElseThrow();
        final var booking = room.bookings().stream()
                .filter(b -> b.getBookingId().equals(bookingNumber))
                .findAny().orElseThrow();
        room.bookings().remove(booking);
    }

    public Map<Room, List<Booking>> getReport(Pair<Instant, Instant> dates) {
        final var from = dates.getValue0();
        final var to = dates.getValue1();
        return this.rooms.stream().map(Room::bookings)
                .flatMap(Collection::stream)
                .filter(b -> b.isDateBetweenFromTo(from, to))
                .collect(groupingBy(Booking::getRoom, toList()));
    }
}
