package com.hotel.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GetLongInputUtils {
    public static Long getNumber(final Scanner scanner, final String text) {
        Long number = null;
        while (number == null) {
            System.out.println(text);
            number = getNumber(scanner);
        }
        return number;
    }

    private static Long getNumber(Scanner scanner) {
        try {
            return scanner.nextLong();
        } catch (InputMismatchException ex) {
            System.out.println("Podaj liczbę!");
            scanner.nextLine();
            return null;
        }
    }
}
