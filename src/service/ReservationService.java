package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private final Map<String, List<Reservation>> reservations = new HashMap<>();
    private final Map<String, IRoom> rooms = new HashMap<>();

    //Singleton declarations starts
    private static final ReservationService reservationService = new ReservationService();
    private ReservationService() { }
    public static ReservationService getReservationService() {
        return reservationService;
    }
    //Singleton declarations ends

    public void addRoom(IRoom room){
        if (room == null){
            throw new IllegalArgumentException("Invalid Entry");
        }else if (rooms.containsKey(room.getRoomNumber())){
            throw new IllegalArgumentException("Room with id "+room.getRoomNumber()+" already exists");
        }else {
            rooms.put(room.getRoomNumber(), room);;
        }
    }

    public IRoom getARoom(String roomId){
        if (roomId == null){
            throw new IllegalArgumentException("Invalid Entry");
        }if (!rooms.containsKey(roomId)){
            throw new IllegalArgumentException("Room with id "+roomId+" doesn't exist");
        }
        return rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        for (String reservationId:reservations.keySet()){
            if (reservationId.equals(newReservation.getRoom().getRoomNumber())){
                for (Reservation reservation:reservations.get(reservationId)){
                    if ( (newReservation.getCheckInDate().before(reservation.getCheckOutDate())) &&
                            ((newReservation.getCheckOutDate().after(reservation.getCheckInDate()) ||
                                    newReservation.getCheckOutDate().equals(reservation.getCheckInDate()))) ){
                        throw new IllegalArgumentException("Room with id "+reservationId+" is occupied between "+
                                checkInDate+" and "+checkOutDate);
                    }
                }
            }
        }
        if (reservations.containsKey(room.getRoomNumber())){
            reservations.get(room.getRoomNumber()).add(newReservation);
        }else{
            List<Reservation> newReservationList = new LinkedList<>();
            newReservationList.add(newReservation);
            reservations.put(room.getRoomNumber(), newReservationList);
        }
        return newReservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate, int additionalDays, Integer roomPriceType){
        Collection<IRoom> getRoomsHelper = findRoomsHelper(checkInDate, checkOutDate, roomPriceType);
        if (getRoomsHelper.isEmpty()){

            //Add additionalDays days to checkInDate
            Calendar calIn = Calendar.getInstance();
            calIn.setTime(checkInDate);
            calIn.add(Calendar.DATE, additionalDays);
            checkInDate = calIn.getTime();

            //Add additionalDays days to checkOutDate
            Calendar calOut = Calendar.getInstance();
            calOut.setTime(checkOutDate);
            calOut.add(Calendar.DATE, additionalDays);
            checkOutDate = calOut.getTime();

            getRoomsHelper = findRoomsHelper(checkInDate, checkOutDate, roomPriceType);
        }
        return getRoomsHelper;
    }

    private Collection<IRoom> findRoomsHelper(Date checkInDate, Date checkOutDate, Integer roomPriceType){
        if (checkInDate == null || checkOutDate == null || roomPriceType == null) {
            throw new IllegalArgumentException("All fields are required");
        }if (checkInDate.after(checkOutDate) || checkInDate.equals(checkOutDate)){
            throw new IllegalArgumentException("Checkout date must be after checkin date");
        }
        List<IRoom> allIRooms = new LinkedList<>();
        List<IRoom> freeIRooms = new LinkedList<>();
        List<IRoom> paidIRooms = new LinkedList<>();

        for (String roomId: rooms.keySet()){
            boolean conflict = false;
            if (reservations.containsKey(roomId)){
                for (Reservation reservation:reservations.get(roomId)){
                    if ( (checkInDate.before(reservation.getCheckOutDate())) &&
                            (checkOutDate.after(reservation.getCheckInDate()) ||
                                    checkOutDate.equals(reservation.getCheckInDate())) ){
                        conflict = true;
                        break;
                    }
                }
            }
            if (!conflict){
                switch (roomPriceType){
                    case 1 -> {if (rooms.get(roomId).isFree()) freeIRooms.add(rooms.get(roomId));}
                    case 2 -> {if (!rooms.get(roomId).isFree()) paidIRooms.add(rooms.get(roomId));}
                    case 3 -> allIRooms.add(rooms.get(roomId));
                }
            }
        }
        switch (roomPriceType){
            case 1 -> {return freeIRooms;}
            case 2 -> {return paidIRooms;}
            case 3 -> {return allIRooms;}
        }
        return Collections.emptyList();
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        if (customer == null) {
            throw new IllegalArgumentException("Customer can't be null");
        }
        List<Reservation> customerReservations = new LinkedList<>();
        for (List<Reservation> reservationList: reservations.values()){
            for (Reservation reservation:reservationList){
                if(reservation.getCustomer().getEmail().equals(customer.getEmail())){
                    customerReservations.add(reservation);
                }
            }
        }
        return customerReservations;
    }

    public void printAllReservation() {
        for (List<Reservation> reservationList: reservations.values()){
            for (Reservation reservation:reservationList){
                System.out.println(reservation);
            }
        }
    }

    public Collection<IRoom> getAllRooms(){
        return rooms.values();
    }

}
