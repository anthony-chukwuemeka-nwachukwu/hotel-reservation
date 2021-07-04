package model;

import java.util.Date;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        if (customer == null || room == null || checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException("All fields are required");
        }else if (checkInDate.after(checkOutDate) || checkInDate.equals(checkOutDate)){
            throw new IllegalArgumentException("Checkout date must be after checkin date");
        }else{
            this.customer = customer;
            this.room = room;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
        }
    }

    public Date getCheckInDate(){
        return checkInDate;
    }

    public Date getCheckOutDate(){
        return checkOutDate;
    }

    public IRoom getRoom(){
        return room;
    }

    public Customer getCustomer(){
        return customer;
    }

    @Override
    public String toString() {
        return "Customer:" + customer + ", Room:" + room + ", CheckInDate:" + checkInDate+ ", CheckOutDate:" + checkOutDate;
    }
}
