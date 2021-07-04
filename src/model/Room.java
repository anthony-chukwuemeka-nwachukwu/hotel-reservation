package model;

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
    public String toString() {
        return "Room Number:" + roomNumber + ", Price:" + price + ", Enumeration:" + enumeration;
    }
}
