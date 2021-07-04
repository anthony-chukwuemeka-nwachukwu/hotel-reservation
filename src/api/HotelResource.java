package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    CustomerService customerService = CustomerService.getCustomerService();
    ReservationService reservationService = ReservationService.getReservationService();

    //Singleton declarations starts
    private static final HotelResource hotelResource = new HotelResource();
    private HotelResource() { }
    public static HotelResource getHotelResource() {
        return hotelResource;
    }
    //Singleton declarations ends

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email,firstName,lastName);
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        return reservationService.reserveARoom(customerService.getCustomer(customerEmail),room,checkInDate,checkOutDate);
    }

    public Collection<Reservation> getCustomerReservation(String customerEmail){
        return reservationService.getCustomersReservation(customerService.getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut, int additionalDays, Integer roomPriceType){
        return reservationService.findRooms(checkIn, checkOut, additionalDays, roomPriceType);
    }

}
