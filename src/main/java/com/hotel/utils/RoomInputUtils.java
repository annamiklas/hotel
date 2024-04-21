package com.hotel.utils;

import org.javatuples.Pair;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RoomInputUtils {

    final private static Integer MAX_ROOM_NUMBER = 15;
    final private static Integer MIN_ROOM_NUMBER = 1;
    final private static Integer MAX_FLOOR_NUMBER = 2;
    final private static Integer MIN_FLOOR_NUMBER = 1;

    public static Pair<Integer, Integer> getRoom(final Scanner scanner) {
        Integer number = getNumber(scanner, "Podaj numer pokoju: ");
        Integer floor = getNumber(scanner, "Podaj pietro: ");
        if (number < MIN_ROOM_NUMBER || number > MAX_ROOM_NUMBER ||
                floor < MIN_FLOOR_NUMBER || floor > MAX_FLOOR_NUMBER) {
            System.out.println("Podany pokoj nie istnieje");
            System.out.println("Numer pokoju nie może być więszky niż " + MAX_ROOM_NUMBER + " a piętra " + MAX_FLOOR_NUMBER);
            getNumber(scanner);
        }
        return new Pair<>(number, floor);
    }

    private static Integer getNumber(final Scanner scanner, final String text) {
        Integer number = null;
        while (number == null) {
            System.out.println(text);
            number = getNumber(scanner);
        }
        return number;
    }

    private static Integer getNumber(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Podaj liczbę!");
            scanner.nextLine();
            return null;
        }
    }
}
