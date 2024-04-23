package com.hotel.model.payment;


import java.util.List;

public class BlikPayment implements PaymentMethod {

    private final Integer code;

    public BlikPayment(Integer code) {
        this.code = code;
    }

    @Override
    public boolean isAuthorized() {
        final var existingCodes = List.of(123456, 5678900, 334455);
        return existingCodes.contains(this.code);
    }
}
