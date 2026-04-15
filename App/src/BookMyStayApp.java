import java.util.*;

// Custom Exception for Invalid Booking
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation class
class Reservation {
    String reservationId;
    String customerName;
    String roomType;
    int nights;
    double pricePerNight;

    public Reservation(String reservationId, String customerName, String roomType,
                       int nights, double pricePerNight) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.nights = nights;
        this.pricePerNight = pricePerNight;
    }

    public double getTotalCost() {
        return nights * pricePerNight;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Total Cost: " + getTotalCost());
        System.out.println("--------------------------");
    }
}

// Booking System with Validation
class BookingSystem {

    private Map<String, Integer> roomInventory = new HashMap<>();
    private List<Reservation> reservations = new ArrayList<>();

    public BookingSystem() {
        roomInventory.put("Standard", 2);
        roomInventory.put("Deluxe", 2);
        roomInventory.put("Suite", 1);
    }

    // Validation method (Fail-Fast)
    private void validateBooking(String roomType, int nights) throws InvalidBookingException {

        if (!roomInventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (nights <= 0) {
            throw new InvalidBookingException("Number of nights must be greater than 0");
        }

        if (roomInventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }
    }

    // Book room
    public void bookRoom(String id, String name, String roomType, int nights, double price) {
        try {
            validateBooking(roomType, nights);

            // Reduce inventory (Guard state)
            roomInventory.put(roomType, roomInventory.get(roomType) - 1);

            Reservation res = new Reservation(id, name, roomType, nights, price);
            reservations.add(res);

            System.out.println("Booking Successful!");
            res.display();

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }

    public void showInventory() {
        System.out.println("\nCurrent Room Availability:");
        for (String type : roomInventory.keySet()) {
            System.out.println(type + ": " + roomInventory.get(type));
        }
    }
}

// Main class
public class BookMyStayApp{

    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        // Valid booking
        system.bookRoom("R101", "Saranya", "Deluxe", 2, 3000);

        // Invalid room type
        system.bookRoom("R102", "Rahul", "Premium", 2, 4000);

        // Invalid nights
        system.bookRoom("R103", "Anu", "Standard", 0, 2000);

        // Overbooking test
        system.bookRoom("R104", "John", "Suite", 2, 5000);
        system.bookRoom("R105", "Mike", "Suite", 1, 5000); // Should fail

        system.showInventory();
    }
}