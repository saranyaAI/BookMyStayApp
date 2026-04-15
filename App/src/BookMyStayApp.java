import java.util.*;

// Reservation class representing a confirmed booking
class Reservation {
    private String reservationId;
    private String customerName;
    private String roomType;
    private int nights;
    private double pricePerNight;

    public Reservation(String reservationId, String customerName, String roomType, int nights, double pricePerNight) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.nights = nights;
        this.pricePerNight = pricePerNight;
    }

    public double getTotalCost() {
        return nights * pricePerNight;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                ", Customer: " + customerName +
                ", Room: " + roomType +
                ", Nights: " + nights +
                ", Total Cost: $" + getTotalCost();
    }
}

// BookingHistory maintains confirmed reservations in insertion order
class BookingHistory {
    private List<Reservation> reservations;

    public BookingHistory() {
        reservations = new ArrayList<>();
    }

    // Store confirmed booking
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    // Retrieve all reservations (read-only copy)
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }
}

// Reporting service for generating summaries
class BookingReportService {

    public void generateSummaryReport(List<Reservation> reservations) {
        System.out.println("\n===== Booking Summary Report =====");

        int totalBookings = reservations.size();
        double totalRevenue = 0;

        Map<String, Integer> roomTypeCount = new HashMap<>();

        for (Reservation r : reservations) {
            totalRevenue += r.getTotalCost();

            roomTypeCount.put(
                    r.getRoomType(),
                    roomTypeCount.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Revenue: $" + totalRevenue);

        System.out.println("\nRoom Type Distribution:");
        for (String type : roomTypeCount.keySet()) {
            System.out.println(type + ": " + roomTypeCount.get(type));
        }
    }

    public void displayAllBookings(List<Reservation> reservations) {
        System.out.println("\n===== Booking History =====");

        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }
}

// Main class
public class BookMyStayApp {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulating confirmed bookings
        Reservation r1 = new Reservation("R001", "Alice", "Single", 2, 100);
        Reservation r2 = new Reservation("R002", "Bob", "Double", 3, 150);
        Reservation r3 = new Reservation("R003", "Charlie", "Suite", 1, 300);

        // Add to booking history (in order)
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Admin views booking history
        reportService.displayAllBookings(history.getAllReservations());

        // Admin generates report
        reportService.generateSummaryReport(history.getAllReservations());
    }
}