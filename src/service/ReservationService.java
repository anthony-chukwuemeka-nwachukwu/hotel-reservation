package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {
    Map<String, Reservation> reservations = new HashMap<String, Reservation>();
    Map<String, Room> rooms = new HashMap<String, Room>();

    private static final ReservationService reservationService = new ReservationService();
    private ReservationService() { }

    public static ReservationService getReservationService() {
        return reservationService;
    }

    public void addRoom(Room room){
        if (!rooms.containsKey(room.getRoomNumber())){
            rooms.put(room.getRoomNumber(), room);
        }else {
            System.out.println("Room with id "+room.getRoomNumber()+" already exists");
        }

    }

    public IRoom getARoom(String roomId){
        if (rooms.containsKey(roomId)){
            return rooms.get(roomId);
        }
        System.out.println("Room with id "+roomId+" doesn't exist");
        return null;
    }

    public Reservation reserveARoom(Customer customer, Room room, Date checkInDate, Date checkOutDate){
        if (room.isFree()){
            Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
            room.setFree();
            reservations.put(room.getRoomNumber(), reservation);
            return reservation;
        }else {
            System.out.println("Room with id "+room.getRoomNumber()+" is occupied");
        }
        return null;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        List<IRoom> iRooms = new LinkedList<>();
        for (String reservation: reservations.keySet()){
            if (reservations.get(reservation).getCheckInDate() == checkInDate &&
                    reservations.get(reservation).getCheckOutDate() == checkOutDate){
                iRooms.add(reservations.get(reservation).getRoom());
            }
        }
        return iRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        List<Reservation> customerReservations = new LinkedList<>();
        for (Reservation reservation: reservations.values()){
            if(reservation.getCustomer().email.equals(customer.email)){
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    public void printAllReservation(){
        for (Reservation reservation: reservations.values()){
            System.out.println(reservation);
        }
    }

}
