import java.util.HashMap;
import java.util.Map;

// Room class to store room details
class Room {
    int beds;
    int size;
    double price;
    int available;

    public Room(int beds, int size, double price, int available) {
        this.beds = beds;
        this.size = size;
        this.price = price;
        this.available = available;
    }
}

// Inventory class
class RoomInventory {

    private HashMap<String, Room> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Register room with details
    public void registerRoomType(String type, Room room) {
        inventory.put(type, room);
    }

    // Display formatted inventory
    public void displayInventory() {
        System.out.println("Hotel Room Inventory Status\n");

        for (Map.Entry<String, Room> entry : inventory.entrySet()) {

            String type = entry.getKey();
            Room room = entry.getValue();

            System.out.println(type + " Room:");
            System.out.println("Beds: " + room.beds);
            System.out.println("Size: " + room.size + " sqft");
            System.out.println("Price per night: " + room.price);
            System.out.println("Available Rooms: " + room.available);
            System.out.println();
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // Add room types with details
        inventory.registerRoomType("Single", new Room(1, 250, 1500.0, 5));
        inventory.registerRoomType("Double", new Room(2, 400, 2500.0, 3));
        inventory.registerRoomType("Suite", new Room(3, 750, 5000.0, 2));

        // Display inventory
        inventory.displayInventory();
    }
}