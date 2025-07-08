package Presentation_View;

import java.util.Scanner;

public class ConsoleView {

    public int displayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n ==== Parcel Management System ===");
        System.out.println("1. Load Customers from Customers CSV");
        System.out.println("2. Load Parcels from Parcels CSV");
        System.out.println("3. Display Customer Queue");
        System.out.println("4. Display Parcels");
        System.out.println("5. Process Current Customer");
        System.out.println("6. Log Events");
        System.out.println("7. Exit");
        System.out.print("Enter Your Choice Here: ");
        return scanner.nextInt();
    }

    public void displayCustomerQueue(String customerQueue) {
        System.out.println(customerQueue);
    }

    public void displayParcels(String parcelMap) {
        System.out.println(parcelMap);
    }

    public void displayLog(String logEntries) {
        System.out.println("Log of Events:");
        System.out.println(logEntries);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }


}
