import java.util.*;

// Booking Request
class BookingRequest {
    String customerName;
    String roomType;

    public BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

// Thread-safe Booking System
class BookingSystem {

    private Map<String, Integer> inventory = new HashMap<>();
    private Queue<BookingRequest> requestQueue = new LinkedList<>();

    public BookingSystem() {
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);
    }

    // Add request (synchronized queue access)
    public synchronized void addRequest(BookingRequest request) {
        requestQueue.add(request);
        System.out.println("Request added: " + request.customerName + " -> " + request.roomType);
    }

    // Process booking (critical section)
    public synchronized void processBooking() {

        if (requestQueue.isEmpty()) {
            return;
        }

        BookingRequest req = requestQueue.poll();

        if (!inventory.containsKey(req.roomType)) {
            System.out.println("Invalid room type for " + req.customerName);
            return;
        }

        int available = inventory.get(req.roomType);

        if (available > 0) {
            // Critical section (protected)
            inventory.put(req.roomType, available - 1);

            System.out.println(Thread.currentThread().getName() +
                    " booked " + req.roomType +
                    " for " + req.customerName);
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " failed (No rooms) for " + req.customerName);
        }
    }

    public void showInventory() {
        System.out.println("\nFinal Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

// Worker Thread
class BookingWorker extends Thread {

    private BookingSystem system;

    public BookingWorker(BookingSystem system, String name) {
        super(name);
        this.system = system;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            system.processBooking();
            try {
                Thread.sleep(100); // simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        // Add booking requests
        system.addRequest(new BookingRequest("Saranya", "Deluxe"));
        system.addRequest(new BookingRequest("Rahul", "Deluxe"));
        system.addRequest(new BookingRequest("Anu", "Suite"));
        system.addRequest(new BookingRequest("John", "Suite")); // should fail
        system.addRequest(new BookingRequest("Mike", "Standard"));

        // Create threads
        Thread t1 = new BookingWorker(system, "Thread-1");
        Thread t2 = new BookingWorker(system, "Thread-2");

        // Start threads
        t1.start();
        t2.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.showInventory();
    }
}