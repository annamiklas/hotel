package com.hotel.model.payment;

import java.util.Map;

public class CardPayment implements PaymentMethod {

    private final Long cardNumber;
    private final Integer pin;

    public CardPayment(Long cardNumber, Integer pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    @Override
    public boolean isAuthorized() {
        final var existingCards = Map.of(1111222233334444L, 1234, 5555666677778888L, 5678, 9999333344445555L, 9345);
        final var pin = existingCards.get(this.cardNumber);
        return pin != null && pin.equals(this.pin);
    }
}
