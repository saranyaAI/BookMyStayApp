import java.io.*;
import java.util.*;

// Reservation (Serializable)
class Reservation implements Serializable {
    String reservationId;
    String customerName;
    String roomType;

    public Reservation(String reservationId, String customerName, String roomType) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println(reservationId + " | " + customerName + " | " + roomType);
    }
}

// Booking System (Serializable)
class BookingSystem implements Serializable {

    Map<String, Integer> inventory = new HashMap<>();
    Map<String, Reservation> bookings = new HashMap<>();

    public BookingSystem() {
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);
    }

    public void bookRoom(String id, String name, String roomType) {
        if (!inventory.containsKey(roomType)) {
            System.out.println("Invalid room type");
            return;
        }

        if (inventory.get(roomType) <= 0) {
            System.out.println("No rooms available");
            return;
        }

        Reservation res = new Reservation(id, name, roomType);
        bookings.put(id, res);
        inventory.put(roomType, inventory.get(roomType) - 1);

        System.out.println("Booked: " + id);
    }

    public void showData() {
        System.out.println("\nBookings:");
        for (Reservation r : bookings.values()) {
            r.display();
        }

        System.out.println("\nInventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "booking_data.ser";

    // Save data
    public static void save(BookingSystem system) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(system);
            System.out.println("Data saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load data
    public static BookingSystem load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            BookingSystem system = (BookingSystem) ois.readObject();
            System.out.println("Data loaded successfully!");
            return system;

        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data. Starting fresh.");
        }

        return new BookingSystem(); // fallback
    }
}

// Main class
public class BookMyStayApp {

    public static void main(String[] args) {

        // Load previous state
        BookingSystem system = PersistenceService.load();

        // Perform operations
        system.bookRoom("R1", "Saranya", "Deluxe");
        system.bookRoom("R2", "Rahul", "Suite");

        system.showData();

        // Save state before exit
        PersistenceService.save(system);
    }
}