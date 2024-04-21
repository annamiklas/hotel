package com.hotel;

import com.hotel.model.Hotel;
import com.hotel.utils.GetChoiceInputUtils;
import com.hotel.process.BookRoomProcess;
import com.hotel.process.CancelBookingProcess;
import com.hotel.process.GetFreeRoomsProcess;
import com.hotel.process.GetReportProcess;

import java.util.Scanner;

public class InputManager {

    private final Hotel hotel;
    private final Scanner scanner;

    public InputManager() {
        this.hotel = new Hotel();
        this.scanner = new Scanner(System.in);
        greeting();
    }

    public void run() {
        while (true) {
            actions();
            final var choice = GetChoiceInputUtils.getNumber(scanner);
            handleChoice(choice, hotel, scanner);
        }
    }

    private static void handleChoice(Integer choice, Hotel hotel, Scanner scanner) {
        switch (choice) {
            case 1: {
                final var getRooms = new GetFreeRoomsProcess(hotel, scanner);
                getRooms.run();
                break;
            }
            case 2: {
                final var book = new BookRoomProcess(hotel, scanner);
                book.run();
                break;
            }
            case 3: {
                final var cancelProcess = new CancelBookingProcess(hotel, scanner);
                cancelProcess.run();
                break;
            }
            case 4: {
                // wyszukanie booking
                // podaj nr kary, data, cv
                //
            }
            case 5: {
                final var report = new GetReportProcess(hotel, scanner);
                report.run();
                break;
            }
            case 6: {
                System.out.println("Dziękujemy za odwiedziny, zapraszamy ponowanie :)");
                System.exit(0);
            }
        }
    }

    private static void greeting() {
        final var greeting = """
                    Witamy w naszym hotelu NiceNight!
                    Działamy na runku już od 12 lat i cieszymy się świetną opinią naszych kilentów.
                    Dysponujemy aż 15 pokojami, z czego 10 jest typu Standart a 5 Deluxe.
                """;
        System.out.println(greeting);
    }

    private static void actions() {
        final var actions = """
                   Wybierz jakie działanie chcesz wykonać:
                   1. Sprawdż dostępność pokoji w przedziale dat
                   2. Zarazerwuj pokój
                   3. Odwołaj rezerwaję
                   4. Opłać rezerwację
                   5. Pokaż raport
                   6. Zakoncz program
                """;
        System.out.println(actions);
    }
}
