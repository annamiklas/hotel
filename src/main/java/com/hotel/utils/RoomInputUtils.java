package com.hotel.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RoomInputUtils {

    final private static Integer MAX_ROOM_NUMBER = 15;
    final private static Integer MIN_ROOM_NUMBER = 1;

    public static Integer getRoom(final Scanner scanner) {
        Integer number = getNumber(scanner);
        if (number < MIN_ROOM_NUMBER || number > MAX_ROOM_NUMBER) {
            System.out.println("Podany pokoj nie istnieje");
            System.out.println("Numer pokoju nie może być mniejszy niż " + MIN_ROOM_NUMBER  + " oraz więszky niż " + MAX_ROOM_NUMBER);
            getNumber(scanner);
        }
        return number;
    }

    private static Integer getNumber(final Scanner scanner) {
        Integer number = null;
        while (number == null) {
            System.out.println("Podaj numer pokoju: ");
            number = tryGetNumber(scanner);
        }
        return number;
    }

    private static Integer tryGetNumber(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Podaj liczbę!");
            scanner.nextLine();
            return null;
        }
    }
}
