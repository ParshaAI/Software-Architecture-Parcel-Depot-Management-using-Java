package Logic_Model;
import Presentation_View.GUIView;
import Presentation_View.ConsoleView;


import java.io.*;

import java.util.*;

public class Manager {

    private CustomerQueue customerQueue;
    private ParcelMap parcelMap;
    private Log log;
    private Worker worker;
    private int customerSequenceID = 1;
    private ConsoleView consoleView;

    public Manager() {
        customerQueue = new CustomerQueue();
        parcelMap = new ParcelMap();
        log = new Log();
        worker = new Worker("Worker-1");
        consoleView = new ConsoleView();
        GUIView guiView = new GUIView(this);
    }

    public void start() {
        int choice;
        do {
            choice = consoleView.displayMenu();

            switch (choice) {
                case 1:
                    loadCustomers();
                    consoleView.displayMessage("Customers loaded successfully.");
                    break;
                case 2:
                    loadParcels();
                    consoleView.displayMessage("Parcels loaded successfully.");
                    break;
                case 3:
                    consoleView.displayCustomerQueue(customerQueue.toString());
                    break;
                case 4:
                    consoleView.displayParcels(parcelMap.toString());
                    break;
                case 5:
                    processCustomer();
                    break;
                case 6:
                    consoleView.displayLog(log.toString());
                    break;
                case 7:
                    consoleView.displayMessage("Exiting system. Goodbye!");
                    break;
                default:
                    consoleView.displayMessage("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }


    public void loadCustomers() {
        try {

            List<Customer> customers = Customer.loadCustomersFromFile("src/Database/Customer.csv");


            for (Customer customer : customers) {
                customerQueue.addCustomer(customer);
                log.addEvent("Added customer: " + customer.getFirstName() + " " + customer.getLastName() + " with Parcel ID: " + customer.getParcelID());
            }


        } catch (IOException e) {
            System.out.println("Error reading customer file: " + e.getMessage());
        }
    }

    public Log getLog() {

        return log;
    }


    public void loadParcels() {
        String parcelFilePath = "src/Database/Parcels.csv";
        try {
            Parcel.readParcelsFromFile(parcelFilePath, parcelMap, log);

        } catch (IOException e) {
            System.out.println("Error reading parcel file: " + e.getMessage());
        }
    }

    public CustomerQueue getCustomerQueue() {
        return customerQueue;
    }


    public ParcelMap getParcelMap() {
        return parcelMap;
    }





    public void displayCustomerQueue() {
        System.out.println(customerQueue.toString());
    }


    public void displayParcels() {
        System.out.println(parcelMap.toString());
    }


    public String processCustomer() {
        Customer customer = customerQueue.nextCustomer();

        if (customer == null) {
            System.out.println("No customers in the queue.");
            return null;
        }

        Parcel parcel = parcelMap.getParcel(customer.getParcelID());

        if (parcel == null) {
            System.out.println("Parcel not found for customer: " + customer.getFirstName() + " " + customer.getLastName());
            return null;
        }

        String result = worker.processCustomer(customerQueue, customer, parcel, parcelMap, log);
        System.out.println(result);
        return result;
    }



    public void writeLogsToFile() {

        log.writeToFile("src/Logic_Model/log.txt");
    }


    public void runGUIView() {
        GUIView guiView = new GUIView(this);
        guiView.show();
    }


    public static void main(String[] args) {
        // Gives user the option to control this from either GUI or Console mode.
        Manager manager = new Manager();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose view type: ");
        System.out.println("1. Console View");
        System.out.println("2. GUI View");
        int choice = scanner.nextInt();
        if (choice == 1) {
            manager.start();
        } else if (choice == 2) {
            manager.runGUIView();
        } else {
            System.out.println("Invalid choice. Exiting...");
        }

    }


}


