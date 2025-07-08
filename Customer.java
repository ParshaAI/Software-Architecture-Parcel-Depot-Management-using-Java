package Logic_Model;

import java.io.*;
import java.util.*;

public class Customer {
    private String firstName;
    private String lastName;
    private int sequenceNo;
    private String parcelID;


    public Customer(String fullName, String pID) {
        String[] nameParts = fullName.split(" ", 2);
        if (nameParts.length == 2) {
            firstName = nameParts[0];
            lastName = nameParts[1];
        } else {
            firstName = nameParts[0];
            lastName = " ";
        }
        parcelID = pID;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String fn) {
        firstName = fn;
    }

    public void setLastName(String ln) {
        lastName = ln;
    }


    public void setSequenceNo(int sno) {
        sequenceNo = sno;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public String getParcelID() {
        return parcelID;
    }

    public static List<Customer> loadCustomersFromFile(String filename)
            throws IOException {
        List<Customer> customers = new ArrayList<Customer>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine();

        int customerSequenceID = 1;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values.length == 2) {
                String customerName = values[0].trim();
                String parcelID = values[1].trim();

                Customer customer = new Customer(customerName, parcelID);

                customer.setSequenceNo(customerSequenceID++);
                customers.add(customer);
            }
        }
        br.close();
        return customers;
    }
}
