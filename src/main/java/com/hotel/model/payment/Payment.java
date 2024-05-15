package com.hotel.model.payment;

import com.hotel.model.Booking;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Random;


@AllArgsConstructor
public class Payment {

    private final BankAccount bankAccount;

    private final PaymentMethod paymentMethod;

    public Payment(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        this.bankAccount = new BankAccount(randomAmount());
    }

    public boolean payIfRequired(final Booking booking) {
        if (validPayment(booking)) {
            return false;
        }
        this.pay(booking);
        System.out.println("Płatność powiodła się, dziękujemy!");
        System.out.println("Pobrana kwota: " + booking.getTotalPrice());
        System.out.println("Pozostała kwota w banku: " + bankAccount.getAmount());
        return true;
    }

    private void pay(Booking booking) {
        this.bankAccount.pay(booking.getTotalPrice());
        booking.pay();
    }

    private boolean validPayment(Booking booking) {
        if (booking.isPaid()) {
            System.out.println("Rezerwacja już opłacona!");
            return true;
        }
        if (!this.paymentMethod.isAuthorized()) {
            System.out.println("Nie udało się zautoryzować płatności, spróbuj ponownie!");
            return true;
        }
        if (this.bankAccount.hasEnoughAmount(booking.getTotalPrice())) {
            System.out.println("Nie masz wystarczających środków na koncie");
            return true;
        }
        return false;
    }

    private BigDecimal randomAmount() {
        final var random = new Random();
        final var randomInt = random.nextInt(500, 2000);
        return new BigDecimal(randomInt);
    }
}
