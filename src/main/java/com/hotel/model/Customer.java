package com.hotel.model;

import com.hotel.model.payment.Card;

import java.util.concurrent.atomic.AtomicLong;

public record Customer(Long idNumber, String name, String surname, String email, Integer phone) {

    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    public static Customer create(final String name, final String surname, final String email, final Integer phoneNumber) {
        return new Customer(ID_GENERATOR.getAndIncrement(), name, surname, email, phoneNumber);
    }
}
