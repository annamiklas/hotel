package com.hotel.utils;

import com.hotel.model.payment.BlikPayment;
import com.hotel.model.payment.CardPayment;
import com.hotel.model.payment.Payment;
import com.hotel.model.payment.PaymentMethodEnum;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GetPaymentInputUtils {

    public static Payment getPayment(final Scanner scanner) {
        PaymentMethodEnum paymentMethod = null;
        while (paymentMethod == null) {
            System.out.println("Wybierz metode płatności: BLIK lub CARD");
            paymentMethod = getPaymentMethod(scanner);
        }
        return getPayment(paymentMethod, scanner);
    }

    private static Integer getBlikCode(final Scanner scanner) {
        Long blik = null;
        while (blik == null) {
            System.out.println("Wpisz 6-cyfrowy kod BLIK");
            blik = tryGetNumber(scanner, "\\d{6}", "Wpisz 6-cyfrowy kod BLIK");
        }
        return blik.intValue();
    }

    private static Long getCardNumber(final Scanner scanner) {
        Long cardNumber = null;
        while (cardNumber == null) {
            System.out.println("Wpisz 16-cyfrowy numer karty");
            cardNumber = tryGetNumber(scanner, "\\d{16}", "Wpisz 16-cyfrowy numer karty");
        }
        return cardNumber;
    }

    private static Integer getCardPin(final Scanner scanner) {
        Long cardNumber = null;
        while (cardNumber == null) {
            System.out.println("Wpisz 4-cyfrowy pin karty");
            cardNumber = tryGetNumber(scanner, "\\d{4}", "Wpisz 4-cyfrowy pin karty");
        }
        return cardNumber.intValue();
    }

    private static Payment getPayment(PaymentMethodEnum paymentMethod, Scanner scanner) {
        return switch (paymentMethod) {
            case BLIK -> getBlik(scanner);
            case CARD -> getCard(scanner);
        };
    }

    private static Payment getCard(Scanner scanner) {
        final var cardNumber = getCardNumber(scanner);
        final var pin = getCardPin(scanner);
        final var cardPayment = new CardPayment(cardNumber, pin);
        return new Payment(cardPayment);
    }

    private static Payment getBlik(Scanner scanner) {
        final var code = getBlikCode(scanner);
        final var blikPayment = new BlikPayment(code);
        return new Payment(blikPayment);
    }

    private static PaymentMethodEnum getPaymentMethod(Scanner scanner) {
        final var pattern = "CARD|BLIK|card|blik|Card|Blik";
        try {
            final var paymentMethod = scanner.next(pattern);
            return PaymentMethodEnum.valueOf(paymentMethod);
        } catch (InputMismatchException | IllegalArgumentException ex) {
            System.out.println("Wpisz BLIK lub CARD!");
            scanner.nextLine();
            return null;
        }
    }

    private static Long tryGetNumber(Scanner scanner, String pattern, String text) {
        try {
            final var code = scanner.next(pattern);
            return Long.parseLong(code);
        } catch (InputMismatchException | IllegalArgumentException ex) {
            System.out.println(text);
            scanner.nextLine();
            return null;
        }
    }
}