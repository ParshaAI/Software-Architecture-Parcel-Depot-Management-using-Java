package Logic_Model;

import java.util.LinkedList;


public class CustomerQueue {

    private LinkedList<Customer> customerQueue;
    private int sequenceNo;




    public CustomerQueue() {
        customerQueue = new LinkedList<>();
        sequenceNo = 1;

    }

    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
        sequenceNo++;
    }

    public Customer currentCustomer() {
        if (!customerQueue.isEmpty()) {
            return customerQueue.poll();
        } else {
            return null;
        }

    }


    public Customer nextCustomer() {
        if (!customerQueue.isEmpty()) {
            return customerQueue.peek();
        } else {
            return null;
        }
    }

    public void removeCustomer(Customer customer) {
        customerQueue.remove(customer);
    }


    public boolean isEmpty() {
        return customerQueue.isEmpty();

    }

    public int getQueueCount() {
        return customerQueue.size();
    }


    public String toString() {
        // If the queue is empty, return a message
        if (customerQueue.isEmpty()) {
            return "The queue is empty!";
        }

        // Start the string with the queue size
        String result = "The current queue has " + customerQueue.size() + " customers in it.\n";

        // Loop through each customer in the queue and concatenate their details to the result string
        for (Customer customer : customerQueue) {
            result += "Sequence No: " + customer.getSequenceNo() +
                    ", Customer: " + customer.getFirstName() + " " + customer.getLastName() +
                    ", Parcel ID: " + customer.getParcelID() + "\n";
        }

        return result;
    }
}




