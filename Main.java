import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Home {
    private int squareFeet;
    private String address;
    private String city;
    private String state;
    private int zipCode;
    private String modelName;
    private String saleStatus;

    public Home(int squareFeet, String address, String city, String state, int zipCode, String modelName, String saleStatus) {
        this.squareFeet = squareFeet;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.modelName = modelName;
        this.saleStatus = saleStatus;
    }

    public String listHome() {
        return "Square Feet: " + squareFeet +
                ", Address: " + address +
                ", City: " + city +
                ", State: " + state +
                ", Zip Code: " + zipCode +
                ", Model Name: " + modelName +
                ", Sale Status: " + saleStatus;
    }

    public void updateSaleStatus(String newStatus) {
        this.saleStatus = newStatus;
    }
}

class HomeInventory {
    private ArrayList<Home> homes = new ArrayList<>();

    public String addHome(Home home) {
        try {
            homes.add(home);
            return "Home added successfully.";
        } catch (Exception e) {
            return "Failed to add home: " + e.getMessage();
        }
    }

    public String removeHome(Home home) {
        try {
            homes.remove(home);
            return "Home removed successfully.";
        } catch (Exception e) {
            return "Failed to remove home: " + e.getMessage();
        }
    }

    public String updateHome(Home home, String newStatus) {
        try {
            home.updateSaleStatus(newStatus);
            return "Home updated successfully.";
        } catch (Exception e) {
            return "Failed to update home: " + e.getMessage();
        }
    }

    public void printToFile(String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Home home : homes) {
                writer.write(home.listHome() + "\n");
            }
            writer.close();
            System.out.println("Information printed to file successfully.");
        } catch (IOException e) {
            System.out.println("Failed to print information to file: " + e.getMessage());
        }
    }

    public void listHomes() {
        for (Home home : homes) {
            System.out.println(home.listHome());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        HomeInventory inventory = new HomeInventory();

        // Adding a new home
        Home newHome = new Home(2000, "123 Main St", "Anytown", "CA", 12345, "Model A", "Available");
        System.out.println(inventory.addHome(newHome));

        // Listing all homes
        System.out.println("Listing all homes:");
        inventory.listHomes();

        // Removing a home
        System.out.println(inventory.removeHome(newHome));

        // Adding a new home
        Home anotherNewHome = new Home(2500, "456 Elm St", "Othertown", "NY", 54321, "Model B", "Under Contract");
        System.out.println(inventory.addHome(anotherNewHome));

        // Listing all homes
        System.out.println("Listing all homes after adding a new one:");
        inventory.listHomes();

        // Updating a home
        System.out.println(inventory.updateHome(anotherNewHome, "Sold"));

        // Listing all homes
        System.out.println("Listing all homes after updating status:");
        inventory.listHomes();

        // Asking user if they want to print to file
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to print the information to a file? (Y/N)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("Y")) {
            inventory.printToFile("C:\\Temp\\Home.txt");
        } else {
            System.out.println("Information will not be printed to a file.");
        }
    }
}
