package com.hotel.model.payment;

import java.util.Map;

public class CardPayment implements PaymentMethod {

    private final Integer cardNumber;
    private final Integer pin;

    public CardPayment(Integer cardNumber, Integer pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    @Override
    public boolean isAuthorized() {
        final var existingCards = Map.of(11223344, 1234, 55667788, 5678, 99334455, 9345);
        final var pin = existingCards.get(this.cardNumber);
        return pin != null && pin.equals(this.pin);
    }
}
