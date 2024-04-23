package com.hotel.process;

import com.hotel.model.Booking;
import com.hotel.model.Hotel;
import com.hotel.utils.GetLongInputUtils;
import com.hotel.utils.GetPaymentInputUtils;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class PaymentProcess implements Process {

    private final Hotel hotel;
    private final Scanner scanner;

    public void run() {
        final var bookingNumber = GetLongInputUtils.getNumber(scanner, "Podaj id rezerwacji: ");
        final var booking = hotel.findBooking(bookingNumber);
        final var payment = GetPaymentInputUtils.getPayment(scanner);
        final var paymentResult = payment.payIfRequired(booking);
        this.handlePaymentResult(paymentResult);
    }

    public void run(final Booking booking) {
        final var payment = GetPaymentInputUtils.getPayment(scanner);
        final var paymentResult = payment.payIfRequired(booking);
        this.handlePaymentResult(paymentResult);
    }

    private void handlePaymentResult(boolean paymentResult) {
        if (!paymentResult) {
            System.out.println("Płatność nieudana, sprobuj jeszcze raz");
            return;
        }
        System.out.println("Płatność powiodła się, dziękujemy!");
    }
}