package com.hotel;

import com.hotel.model.Hotel;
import com.hotel.process.*;
import com.hotel.utils.GetChoiceInputUtils;

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
                final var paymentProcess = new PaymentProcess(hotel, scanner);
                paymentProcess.run();
                break;
            }
            case 5: {
                final var report = new GetReportProcess(hotel, scanner);
                report.run();
                break;
            }
            case 6: {
                System.out.println("Dziękujemy za odwiedziny, zapraszamy ponownie :)");
                System.exit(0);
            }
        }
    }

    private static void greeting() {
        final var greeting = """
                    Witamy w naszym hotelu NiceNight!
                    Działamy na rynku już od 12 lat i cieszymy się świetną opinią naszych klientów.
                    Dysponujemy aż 15 pokojami, z czego 10 jest typu Standard a 5 Deluxe.
                """;
        System.out.println(greeting);
    }

    private static void actions() {
        final var actions = """
                   Wybierz jakie działanie chcesz wykonać:
                   1. Sprawdź dostępność pokoi w przedziale dat
                   2. Zarezerwuj pokój
                   3. Odwołaj rezerwację
                   4. Opłać rezerwację
                   5. Pokaż raport
                   6. Zakończ program
                """;
        System.out.println(actions);
    }
}
