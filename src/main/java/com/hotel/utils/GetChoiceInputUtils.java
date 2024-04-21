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

    private static Integer tryGetNumber(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException ex) {
            scanner.nextLine();
            return null;
        }
    }
}
