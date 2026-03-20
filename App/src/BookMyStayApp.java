import java.util.*;
class Room {
    String name;
    String city;
    int price;
    // Constructor
    Room(String name, String city, int price) {
        this.name = name;
        this.city = city;
        this.price = price;
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Sample room data
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Central Hotel", "New York", 150));
        rooms.add(new Room("Park Inn", "New York", 180));
        rooms.add(new Room("City Lodge", "New York", 120));
        rooms.add(new Room("Sea View", "Los Angeles", 250));
        rooms.add(new Room("Hill Stay", "Chicago", 100));

        System.out.println("Welcome to the Room Search Application");

        // User input
        System.out.print("Enter the city to search rooms: ");
        String city = sc.nextLine();

        System.out.print("Enter maximum price: ");
        int maxPrice = sc.nextInt();

        System.out.println("\nSearching for rooms in " + city + " under $" + maxPrice + "...");
        System.out.println("Available Rooms:");

        boolean found = false;

        // Search logic
        for (Room r : rooms) {
            if (r.city.equalsIgnoreCase(city) && r.price <= maxPrice) {
                System.out.println("Room: " + r.name + ", Price: $" + r.price);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms found.");
        }

        System.out.println("\nSearch complete.");

        sc.close();
    }
}
