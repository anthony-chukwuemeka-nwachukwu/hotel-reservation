package model;

import java.util.Objects;

public class Room implements IRoom{
    private final String roomNumber;
    private final Double price;
    private final RoomType enumeration;

    public Room (String roomNumber, Double price, RoomType enumeration){
        super();
        if (roomNumber == null || price == null || enumeration == null) {
            throw new IllegalArgumentException("All fields are required");
        }
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    public void setRoomNumber(){}

    public void setPrice(){}

    public void setEnumeration(){}

    @Override
    public boolean isFree() {
        return price==0.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Room other = (Room) obj;
        if (!Objects.equals(this.roomNumber, other.roomNumber)) {
            return false;
        }

        if (!Objects.equals(this.enumeration, other.enumeration)) {
            return false;
        }

        return Math.round(this.price * Math.pow(10, 3)) / Math.pow(10, 3) == Math.round(other.price * Math.pow(10, 3)) / Math.pow(10, 3);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode (this.roomNumber);
        hash = 31 * hash + Objects.hashCode (this.enumeration);
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.price));
        return hash;
    }

    @Override
    public String toString() {
        return "Room Number:" + roomNumber + ", Price:" + price + ", Enumeration:" + enumeration;
    }
}
