package com.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@AllArgsConstructor
public final class Booking {

    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);
    private final Long bookingId;
    private final Customer customer;
    private final Room room;
    private final Instant fromDate;
    private final Instant toDate;
    private final BigDecimal totalPrice;
    private boolean paid;

    public static Booking create(final Instant from, final Instant to, final Customer customer, final Room room) {
        return new Booking(ID_GENERATOR.getAndIncrement(), customer, room, from, to,
                getTotalPrice(from, to, room), false);
    }

    private static BigDecimal getTotalPrice(final Instant from, final Instant to, final Room room) {
        final var nightsNumber = ChronoUnit.DAYS.between(from, to);
        return room.pricePerDay().multiply(new BigDecimal(nightsNumber));
    }

    public Long getDuration() {
        return ChronoUnit.DAYS.between(this.fromDate, this.toDate);
    }

    public BigDecimal getPricePerDay() {
        return this.room.pricePerDay();
    }

    public boolean isDateBetweenFromTo(final Instant date) {
        return this.fromDate.equals(date) || this.fromDate.isAfter(date) && this.toDate.isBefore(date) || this.toDate.equals(date);
    }

    public void pay() {
        this.paid = true;
    }

    @Override
    public String toString() {
        return "Rezerwacja numer: " + bookingId + " od: " + fromDate + " do: " + toDate + "   Cena calkowita: " + totalPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Booking) obj;
        return Objects.equals(this.bookingId, that.bookingId) &&
                Objects.equals(this.customer, that.customer) &&
                Objects.equals(this.room, that.room) &&
                Objects.equals(this.fromDate, that.fromDate) &&
                Objects.equals(this.toDate, that.toDate) &&
                Objects.equals(this.totalPrice, that.totalPrice) &&
                Objects.equals(this.paid, that.paid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, customer, room, fromDate, toDate, totalPrice, paid);
    }

}
