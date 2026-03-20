import java.util.*;

// Booking Request class
class BookingRequest {
    String customerName;
    String roomType;
    int nights;

    BookingRequest(String customerName, String roomType, int nights) {
        this.customerName = customerName;
        this.roomType = roomType;
        this.nights = nights;
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Queue to store booking requests
        Queue<BookingRequest> queue = new LinkedList<>();

        System.out.println("Welcome to Booking Request Queue System");

        // Taking number of requests
        System.out.print("Enter number of booking requests: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        // Input booking requests
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for request " + (i + 1));

            System.out.print("Customer Name: ");
            String name = sc.nextLine();

            System.out.print("Room Type: ");
            String roomType = sc.nextLine();

            System.out.print("Number of nights: ");
            int nights = sc.nextInt();
            sc.nextLine(); // consume newline

            queue.add(new BookingRequest(name, roomType, nights));
        }

        // Processing booking requests
        System.out.println("\nProcessing Booking Requests...");

        while (!queue.isEmpty()) {
            BookingRequest req = queue.poll();

            System.out.println("\nProcessing Request:");
            System.out.println("Customer: " + req.customerName);
            System.out.println("Room Type: " + req.roomType);
            System.out.println("Nights: " + req.nights);
            System.out.println("Status: Booking Confirmed");
        }

        System.out.println("\nAll booking requests processed.");

        sc.close();
    }
}
