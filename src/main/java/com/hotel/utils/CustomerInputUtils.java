package com.hotel.utils;

import com.hotel.model.Customer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerInputUtils {

    public static Customer getCustomer(final Scanner scanner) {
        System.out.println("Podaj imię: ");
        final var name = scanner.next();
        System.out.println("Podaj nazwisko: ");
        final var surname = scanner.next();
        final var email = getEmail(scanner);
        final var phoneNumber = getPhoneNumber(scanner);
        return Customer.create(name, surname, email, phoneNumber);
    }

    private static String getEmail(final Scanner scanner) {
        String email = null;
        while (email == null) {
            System.out.println("Podaj email: (xxx@xxx.xxx)");
            email = tryGetEmail(scanner);
        }
        return email;
    }

    private static Integer getPhoneNumber(final Scanner scanner) {
        Integer number = null;
        while (number == null) {
            System.out.println("Podaj numer telefonu: (bez numeru kierunkowego)");
            number = tryGetPhoneNumber(scanner);
        }
        return number;
    }

    private static Integer tryGetPhoneNumber(Scanner scanner) {
        var phoneNumberPattern = "\\d{9,}";
        try {
            final var input = scanner.next(phoneNumberPattern);
            return Integer.parseInt(input);
        } catch (NumberFormatException | InputMismatchException ex) {
            System.out.println("Błędny format numeru telefonu!!");
            scanner.nextLine();
            return null;
        }
    }

    private static String tryGetEmail(Scanner scanner) {
        var emailPattern = ".*@.*";
        try {
            return scanner.next(emailPattern);
        } catch (InputMismatchException ex) {
            System.out.println("Błędny format adresu email!!");
            scanner.nextLine();
            return null;
        }
    }
}
