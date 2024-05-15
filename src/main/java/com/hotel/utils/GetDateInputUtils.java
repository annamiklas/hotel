package com.hotel.utils;

import org.javatuples.Pair;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GetDateInputUtils {

    public static Pair<Instant, Instant> getDates(final Scanner scanner) {
        Instant from = getDate(scanner, "Podaj datę od:  (format YYYY-MM-DD)");
        Instant to = getDate(scanner, "Podaj datę do:  (format YYYY-MM-DD)");
        if (from.isAfter(to)) {
            System.out.println("Data od nie może być wcześniejsza od daty do!");
            return getDates(scanner);
        }
        if (from.isBefore(Instant.now())) {
            System.out.println("Data od nie może być wcześniejsza od daty aktualnej!");
            return getDates(scanner);
        }
        return new Pair<>(from, to);
    }

    private static Instant getDate(final Scanner scanner, final String text) {
        Instant date = null;
        while (date == null) {
            System.out.println(text);
            date = getDate(scanner);
        }
        return date;
    }

    private static Instant getDate(Scanner scanner) {
        var datePattern = "\\d{4}-\\d{2}-\\d{2}";
        try {
            final var localDate = LocalDate.parse(scanner.next(datePattern));
            final var localDateTime = localDate.atStartOfDay();
            return localDateTime.toInstant(ZoneOffset.UTC);
        } catch (InputMismatchException | DateTimeParseException ex) {
            System.out.println("Błędny format daty, spróbuj jeszcze raz");
            scanner.nextLine();
            return null;
        }
    }
}
