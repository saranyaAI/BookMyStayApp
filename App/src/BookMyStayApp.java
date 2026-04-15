import java.util.*;

// Custom Exception
class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }
}

// Reservation class
class Reservation {
    String reservationId;
    String customerName;
    String roomType;

    public Reservation(String reservationId, String customerName, String roomType) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId +
                ", Name: " + customerName +
                ", Room: " + roomType);
    }
}

// Booking System
class BookingSystem {

    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Reservation> bookings = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();

    public BookingSystem() {
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);
    }

    // Booking
    public void bookRoom(String id, String name, String roomType) throws BookingException {

        if (!inventory.containsKey(roomType)) {
            throw new BookingException("Invalid room type");
        }

        if (inventory.get(roomType) <= 0) {
            throw new BookingException("No rooms available");
        }

        Reservation res = new Reservation(id, name, roomType);
        bookings.put(id, res);

        inventory.put(roomType, inventory.get(roomType) - 1);

        System.out.println("Booking successful:");
        res.display();
    }

    // Cancellation with rollback
    public void cancelBooking(String reservationId) throws BookingException {

        if (!bookings.containsKey(reservationId)) {
            throw new BookingException("Reservation not found or already cancelled");
        }

        Reservation res = bookings.get(reservationId);

        // Push to stack (LIFO rollback)
        rollbackStack.push(reservationId);

        // Restore inventory
        String roomType = res.roomType;
        inventory.put(roomType, inventory.get(roomType) + 1);

        // Remove booking
        bookings.remove(reservationId);

        System.out.println("Booking cancelled successfully: " + reservationId);
    }

    // Show inventory
    public void showInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }

    // Show rollback stack
    public void showRollbackStack() {
        System.out.println("\nRollback Stack (Recent cancellations): " + rollbackStack);
    }
}

// Main class
public class BookMyStayApp {

    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        try {
            // Book rooms
            system.bookRoom("R1", "Saranya", "Deluxe");
            system.bookRoom("R2", "Rahul", "Standard");

            // Cancel booking
            system.cancelBooking("R1");

            // Invalid cancellation
            system.cancelBooking("R3");

        } catch (BookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        system.showInventory();
        system.showRollbackStack();
    }
}