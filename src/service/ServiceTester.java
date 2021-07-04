package service;

import model.*;

import java.util.Date;

public class ServiceTester {
    public static void main(String[] args) {
        Customer customer = new Customer("Anthony", "Nwachukwu", "nwachukwu.anthony2017@gmail.com");
        //System.out.println(customer);

        ReservationService reservationService = ReservationService.getReservationService();
        CustomerService customerService = CustomerService.getCustomerService();
        Room room1 = new Room("23",234.0, RoomType.DOUBLE);
        Room room2 = new Room("13",2674.0,RoomType.SINGLE);
        Room room3 = new FreeRoom("14", RoomType.SINGLE);
        Customer customer1 = new Customer("caleb", "nduka", "caleb@yahoo.com");
        customerService.addCustomer("caleb@yahoo.com", "nduka", "caleb");
        reservationService.addRoom(room1);
        //reservationService.addRoom(room3);
        reservationService.addRoom(room2);
        System.out.println(reservationService.getARoom("13").isFree());
        System.out.println(customerService.getCustomer("calebyahoo.com"));
        Reservation reservedRoom1 = reservationService.reserveARoom(customer,room1, new Date(2014, 02, 11),new Date(2014, 02, 20));
        reservationService.reserveARoom(customer1,room2, new Date(2014, 02, 11),new Date(2014, 02, 20));
        //reservationService.reserveARoom(customer1,room3, Date.from(Instant.now()),Date.from(Instant.now()));
        //reservationService.printAllReservation();
        System.out.println(reservationService.findRooms(new Date(2014, 02, 13),new Date(2014, 02, 20),7,1));

        //System.out.println(reservationService.getCustomersReservation(customer1));

    }
}
