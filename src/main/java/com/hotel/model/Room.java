package com.hotel.model;

import org.javatuples.Pair;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;


public record Room(Integer roomNumber, Integer floorNumber, Integer numberOfBeds, BigDecimal pricePerDay, Type type,
                   ArrayList<Booking> bookings) {

    public Boolean hasNotAnyBookingInDates(final Instant from, final Instant to) {
        return this.bookings.stream().allMatch(b -> isNotBookingInDates(b, from, to));
    }

    private Boolean isNotBookingInDates(final Booking booking, final Instant from, final Instant to) {
        return from.isAfter(booking.getFromDate()) && from.isAfter(booking.getToDate()) ||
                to.isBefore(booking.getFromDate()) && to.isBefore(booking.getToDate());
    }

    public Boolean isNumber(final Integer roomNumber) {
        return this.roomNumber.equals(roomNumber);
    }

    public Booking bookRoomInDates(Pair<Instant, Instant> dates, final Customer customer) {
        final var booking = Booking.create(dates.getValue0(), dates.getValue1(), customer, this);
        bookings.add(booking);
        return booking;
    }

    public boolean hasBooking(long bookingNumber) {
        return this.bookings.stream().anyMatch(b -> b.getBookingId().equals(bookingNumber));
    }

    @Override
    public String toString() {
        return "Pokój: nr " + roomNumber + " piętro: " + floorNumber + " Liczba osób: " + numberOfBeds +
                " cena za noc: " + pricePerDay + " typ: " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber) && Objects.equals(floorNumber, room.floorNumber) && Objects.equals(numberOfBeds, room.numberOfBeds) && Objects.equals(pricePerDay, room.pricePerDay) && type == room.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, floorNumber, numberOfBeds, pricePerDay, type);
    }
}
