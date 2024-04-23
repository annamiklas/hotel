package com.hotel.model.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class BankAccount {

    private BigDecimal amount;

    public void pay(final BigDecimal amount) {
        final var paid = this.amount.subtract(amount);
        if (paid.compareTo(BigDecimal.ZERO) >= 0) {
            this.amount = paid;
        }
    }

    public boolean hasEnoughAmount(final BigDecimal price) {
        return price.compareTo(this.amount) > 0;
    }

}
