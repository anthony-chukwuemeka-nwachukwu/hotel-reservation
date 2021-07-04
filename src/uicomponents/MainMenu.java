package uicomponents;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class MainMenu {

    static Scanner mainScanner = new Scanner(System.in);
    private final HotelResource hotelResource = HotelResource.getHotelResource();

    //Singleton declarations starts
    private static final MainMenu mainMenu = new MainMenu();
    public MainMenu(){
        System.out.println("Welcome to the Hotel Reservation Application");
        System.out.println(" ");
        mainMenu();
    }
    public static void getMainMenu() {
    }
    //Singleton declarations ends

    public void mainMenu(){
        System.out.println("\nMain Menu");
        System.out.println("______________________________________________________");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("______________________________________________________");
        Integer[] possibleInputs = {1,2,3,4,5};

        try {
            System.out.print("Enter your choice: ");
            int mainMenuChoice = Integer.parseInt(mainScanner.nextLine());
            if (!Arrays.stream(possibleInputs).toList().contains(mainMenuChoice)){
                throw new IllegalArgumentException("For input: "+ mainMenuChoice);
            }
            switch (mainMenuChoice) {
                case 4 -> {AdminMenu.getAdminMenu().adminMenu();mainMenu();}
                case 1 -> findAndReserveARoom();
                case 2 -> seeMyReservations();
                case 3 -> createAccount();
                case 5 -> mainScanner.close();
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage()+", Entry must be a number between 1 and 5");
            mainMenu();
        }
    }

    private void createAccount(){
        try {
            System.out.println("\nEnter Details to to Create Account");
            System.out.println("----------------------------------");

            System.out.print("First Name: ");
            String firstName = mainScanner.nextLine();
            if(firstName.isBlank()){firstName = null;}

            System.out.print("Last Name: ");
            String lastName = mainScanner.nextLine();
            if(lastName.isBlank()){lastName = null;}

            System.out.print("Email: ");
            String email = mainScanner.nextLine();
            if(email.isBlank()){email = null;}

            System.out.println();
            hotelResource.createACustomer(email,firstName,lastName);
            System.out.println("Account created");
            mainMenu();

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
            createAccount();
        }
    }

    private void seeMyReservations(){
        try {
            System.out.println("\nMy Reservations");
            System.out.println("----------------------------------");

            System.out.print("Enter email: ");
            String email = mainScanner.nextLine();
            if(email.isBlank()){email = null;}

            System.out.println();
            for (Reservation reservation:hotelResource.getCustomerReservation(email)){
                System.out.println(reservation);
            }
            mainMenu();

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage()+"\nSearch again, yes or no?: ");
            String response = mainScanner.nextLine().toLowerCase();
            if (response.equals("yes")){
                seeMyReservations();
            }else if (response.equals("no")){
                mainMenu();
            }else{
                System.out.println("Invalid Entry, try again next time");
                mainMenu();
            }
        }
    }

    private void findAndReserveARoom(){
        System.out.println("\nFind and Reserve a Room");
        System.out.println("----------------------------------");

        System.out.print("Enter checkin data dd/MM/yyyy: ");
        String checkin = mainScanner.nextLine();

        System.out.print("Enter checkout data dd/MM/yyyy: ");
        String checkout = mainScanner.nextLine();

        int additionalDays = 0;

        try {
            System.out.print("Options: \n1.Free Rooms\n2.Paid Rooms\n3.All Rooms\nEnter your choice: ");
            Integer roomPriceType = Integer.parseInt(mainScanner.nextLine());

            if (checkin.isBlank()) { checkin = null; }
            Date checkinDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkin);


            if (checkout.isBlank()) { checkout = null; }
            Date checkoutDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkout);

            if (roomPriceType!=1 && roomPriceType!=2 && roomPriceType!=3) { roomPriceType = null; }

            System.out.print("If no available room, how many days out should be researched for?: ");
            additionalDays = Integer.parseInt(mainScanner.nextLine());

            Collection<IRoom> availableRooms= hotelResource.findARoom(checkinDate,checkoutDate,additionalDays,roomPriceType);

            if (availableRooms.isEmpty()){
                throw new EmptyStackException();
            }

            System.out.println("\nAvailable rooms are:\n");
            int availableRoomsCount = 1;
            for (IRoom room:availableRooms){
                System.out.println(availableRoomsCount+". "+room);
                availableRoomsCount++;
            }

            System.out.print("\nWhich of the above do you wish to reserve?(Enter the RoomId): ");
            String roomId = mainScanner.nextLine();
            System.out.print("Enter your email address: ");
            String email = mainScanner.nextLine();

            for (IRoom room:availableRooms){
                if (room.getRoomNumber().equals(roomId)){
                    hotelResource.bookARoom(email,room,checkinDate,checkoutDate);
                }
            }

            mainMenu();

        }catch (IllegalArgumentException | ParseException | NullPointerException ex) {

            System.out.println(ex.getLocalizedMessage()+"\nTry again, yes or no?: ");
            String response = mainScanner.nextLine().toLowerCase();
            if (response.equals("yes")){
                findAndReserveARoom();
            }else if (response.equals("no")){
                mainMenu();
            }else{
                System.out.println("Invalid Entry, try again next time");
                mainMenu();
            }
        }catch (EmptyStackException ex){
            System.out.println(ex.getLocalizedMessage()+
                    "\nNo available room date between "+checkin+" and "+checkout+", not even for the next "+
                    additionalDays+" days time"+"\nTry again, yes or no?: ");
            String response = mainScanner.nextLine().toLowerCase();
            if (response.equals("yes")){
                findAndReserveARoom();
            }else if (response.equals("no")){
                mainMenu();
            }else{
                System.out.println("Invalid Entry, try again next time");
                mainMenu();
            }
        }
    }


}
