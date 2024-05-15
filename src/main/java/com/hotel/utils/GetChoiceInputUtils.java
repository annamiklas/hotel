package com.hotel.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GetChoiceInputUtils {

    final private static Integer MAX_CHOICE_NUMBER = 6;
    final private static Integer MIN_CHOICE_NUMBER = 1;
    public static Integer getNumber(final Scanner scanner) {
        Integer number = null;
        while (number == null || number < MIN_CHOICE_NUMBER || number > MAX_CHOICE_NUMBER) {
            System.out.println("Podaj liczbę z przedziału od 1 do 6");
            number = tryGetNumber(scanner);
        }
        return number;
    }

    public static Boolean getBooleanChoice(final Scanner scanner, final String text) {
        Boolean choice = null;
        while (choice == null) {
            System.out.println(text);
            choice = tryGetBoolean(scanner);
        }
        return choice;
    }


    private static Integer tryGetNumber(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException ex) {
            scanner.nextLine();
            return null;
        }
    }

    private static Boolean tryGetBoolean(Scanner scanner) {
        final var pattern = "Tak|Nie|tak|nie|TAK|NIE";
        try {
            final var choice = scanner.next(pattern);
            return choice.equalsIgnoreCase("tak");
        } catch (InputMismatchException ex) {
            scanner.nextLine();
            return null;
        }
    }
}
