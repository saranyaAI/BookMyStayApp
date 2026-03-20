import java.util.*;

// Room class
class Room {
    int roomId;
    String type;
    boolean isAvailable;

    Room(int roomId, String type, boolean isAvailable) {
        this.roomId = roomId;
        this.type = type;
        this.isAvailable = isAvailable;
    }
}

// Booking class
class Booking {
    String customerName;
    String requiredType;

    Booking(String customerName, String requiredType) {
        this.customerName = customerName;
        this.requiredType = requiredType;
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Sample rooms
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, "Standard", true));
        rooms.add(new Room(102, "Deluxe", true));
        rooms.add(new Room(103, "Suite", true));
        rooms.add(new Room(104, "Standard", true));
        rooms.add(new Room(105, "Deluxe", true));

        // Queue for booking requests
        Queue<Booking> bookings = new LinkedList<>();

        System.out.println("Welcome to Room Allocation Service");

        // Input number of bookings
        System.out.print("Enter number of booking requests: ");
        int n = sc.nextInt();
        sc.nextLine();

        // Input booking details
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter booking " + (i + 1));

            System.out.print("Customer Name: ");
            String name = sc.nextLine();

            System.out.print("Required Room Type (Standard/Deluxe/Suite): ");
            String type = sc.nextLine();

            bookings.add(new Booking(name, type));
        }

        // Allocation process
        System.out.println("\nAllocating Rooms...");

        while (!bookings.isEmpty()) {
            Booking b = bookings.poll();
            boolean allocated = false;

            for (Room r : rooms) {
                if (r.isAvailable && r.type.equalsIgnoreCase(b.requiredType)) {
                    r.isAvailable = false;

                    System.out.println("\nBooking Confirmed!");
                    System.out.println("Customer: " + b.customerName);
                    System.out.println("Room ID: " + r.roomId);
                    System.out.println("Room Type: " + r.type);

                    allocated = true;
                    break;
                }
            }

            if (!allocated) {
                System.out.println("\nSorry " + b.customerName +
                        ", No " + b.requiredType + " rooms available.");
            }
        }

        System.out.println("\nAll bookings processed.");

        sc.close();
    }
}