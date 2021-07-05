package model;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

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
        }else if (checkInDate.before(Date.from(Instant.now()))){
            throw new IllegalArgumentException("Checkin and checkout dates must not be in the past");
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.checkInDate, other.checkInDate)) {
            return false;
        }

        if (!Objects.equals(this.checkOutDate, other.checkOutDate)) {
            return false;
        }

        if (!Objects.equals(this.room, other.room)) {
            return false;
        }

        return Objects.equals(this.customer, other.customer);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode (this.checkInDate);
        hash = 53 * hash + Objects.hashCode (this.checkOutDate);
        hash = 53 * hash + Objects.hashCode (this.room);
        hash = 53 * hash + Objects.hashCode (this.customer);
        return hash;
    }

    @Override
    public String toString() {
        return "Customer:" + customer + ", Room:" + room + ", CheckInDate:" + checkInDate+ ", CheckOutDate:" + checkOutDate;
    }
}
