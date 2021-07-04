package uicomponents;

import api.AdminResource;
import model.*;

import java.util.*;

import static uicomponents.MainMenu.mainScanner;

public class AdminMenu {
    //Scanner adminScanner = new Scanner(System.in);
    private final AdminResource adminResource = AdminResource.getAdminResource();

    //Singleton declarations starts
    private static final AdminMenu adminMenu = new AdminMenu();
    public AdminMenu(){
        System.out.println();
        //adminMenu();
    }
    public static AdminMenu getAdminMenu() {
        return adminMenu;
    }
    //Singleton declarations ends

    public void adminMenu() {

        System.out.println("\nAdmin Menu");
        System.out.println("_____________________________________________________");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("_____________________________________________________");
        Integer[] adminPossibleInputs = {1, 2, 3, 4, 5};

        try {
            System.out.print("Enter your choice: ");
            int adminMenuChoice = Integer.parseInt(mainScanner.nextLine());
            if (!Arrays.stream(adminPossibleInputs).toList().contains(adminMenuChoice)){
                throw new IllegalArgumentException("For input: "+ adminMenuChoice);
            }
            switch (adminMenuChoice) {
                //case 5 ->  System.out.println("Main Back");// MainMenu.getMainMenu();
                case 1 -> seeAllCustomers();
                case 2 -> seeAllRooms();
                case 3 -> seeAllReservations();
                case 4 -> addARoom();
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage()+", Entry must be a number between 1 and 5");
            adminMenu() ;
        }
    }

    public void seeAllCustomers(){
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()){
            System.out.println("No customer found");
        }else{
            for (Customer customer:customers){
                System.out.println(customer);
            }
        }
        adminMenu();
    }

    public void seeAllRooms(){
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms.isEmpty()){
            System.out.println("No room found");
        }else{
            for (IRoom room:rooms){
                System.out.println(room);
            }
        }
        adminMenu();
    }

    public void seeAllReservations(){
        adminResource.displayAllReservations();
        adminMenu();
    }

    public void addARoom(){
        System.out.println("Enter room details\n");
        List<IRoom> rooms = new LinkedList<>();
        while (true){
            try {
                System.out.print("Room ID (eg. 34): ");
                String roomId = mainScanner.nextLine();

                System.out.print("Room Price (eg. 234.56): ");
                Double roomPrice = Double.parseDouble(mainScanner.nextLine());

                System.out.print("Room ID, 1-> SINGLE Room, 2-> DOUBLE Room (eg. 1): ");
                int roomTypeId = Integer.parseInt(mainScanner.nextLine());
                RoomType roomType;
                switch (roomTypeId){
                    case 1 -> roomType = RoomType.SINGLE;
                    case 2 -> roomType = RoomType.DOUBLE;
                    default -> roomType = null;
                }

                Room room = new Room(roomId,roomPrice, roomType);
                rooms.add(room);

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getLocalizedMessage()+", Entry must be integer");
            }

            System.out.println("\nDo you want to add more rooms?, (yes or no): ");
            String response = mainScanner.nextLine().toLowerCase();
            if (response.equals("no")){
                break;
            }if (!response.equals("yes")){
                System.out.println("Invalid Entry, try again next time");
                break;
            }
        }
        //Room room1 = new Room("23",234.0, RoomType.DOUBLE);
        if (!rooms.isEmpty()){
            adminResource.addRoom(rooms);
        }
        adminMenu();
    }
}
