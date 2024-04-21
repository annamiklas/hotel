package com.hotel.model.payment;

import com.hotel.model.Booking;

import java.math.BigDecimal;

abstract public class Payment {

    protected void pay(final BigDecimal amount, final Booking booking) {
        if (amount.equals(booking.getTotalPrice())) {
            booking.pay();
        }
    }

    abstract public void payBooking();
}
