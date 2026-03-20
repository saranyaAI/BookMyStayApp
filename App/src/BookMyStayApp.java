import java.util.*;

// Add-on Service class
class AddOnService {
    String name;
    int price;

    AddOnService(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Available add-on services
        List<AddOnService> services = new ArrayList<>();
        services.add(new AddOnService("WiFi", 100));
        services.add(new AddOnService("Breakfast", 200));
        services.add(new AddOnService("Airport Pickup", 500));
        services.add(new AddOnService("Extra Bed", 300));

        System.out.println("Welcome to Add-On Service Selection");

        // Display services
        System.out.println("\nAvailable Services:");
        for (int i = 0; i < services.size(); i++) {
            System.out.println((i + 1) + ". " + services.get(i).name + " - $" + services.get(i).price);
        }

        // User selects services
        System.out.print("\nEnter number of services you want to select: ");
        int n = sc.nextInt();

        List<AddOnService> selected = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter service number: ");
            int choice = sc.nextInt();

            if (choice >= 1 && choice <= services.size()) {
                selected.add(services.get(choice - 1));
            } else {
                System.out.println("Invalid choice!");
                i--; // retry
            }
        }

        // Display selected services and total cost
        int total = 0;
        System.out.println("\nSelected Services:");

        for (AddOnService s : selected) {
            System.out.println(s.name + " - $" + s.price);
            total += s.price;
        }

        System.out.println("\nTotal Add-On Cost: $" + total);

        sc.close();
    }
}