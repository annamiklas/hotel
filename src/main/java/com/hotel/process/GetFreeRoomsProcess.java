package com.hotel.process;

import com.hotel.utils.DateInputUtils;
import com.hotel.model.Hotel;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class GetFreeRoomsProcess implements Process {

    private final Hotel hotel;
    private final Scanner scanner;

    public void run() {
        final var dates = DateInputUtils.getDates(this.scanner);
        final var rooms = this.hotel.findFreeRoomInDates(dates.getValue0(), dates.getValue1());
        rooms.forEach(System.out::println);
        System.out.println();
        System.out.println();
    }
}
