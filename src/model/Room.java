package model;

public class Room implements IRoom{
    private final String roomNumber;
    private final Double price;
    private final RoomType enumeration;
    private boolean free;

    public Room (String roomNumber, Double price, RoomType enumeration){
        super();
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
        this.free = true;
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

    @Override
    public boolean isFree() {
        return free;
    }

    public void setFree(){
        free = !isFree();
    }

    @Override
    public String toString() {
        return "Room Number:" + roomNumber + ", Price:" + price + ", Enumeration:" + enumeration;
    }
}
