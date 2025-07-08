package Logic_Model;

public class Worker {
    private String workerID;




    public Worker(String wid) {
        workerID = wid;
    }

    public String processCustomer(CustomerQueue customerQueue, Customer customer, Parcel parcel, ParcelMap parcelMap, Log log) {
        String customerDetails = "Processing Customer: " +
                customer.getFirstName() + " " + customer.getLastName() +
                " with Parcel ID: " + parcel.getParcelID();

        double parcelFee = calculateFee(parcel);

        log.addEvent("Processed Current Customer: " + customer.getFirstName() + " " + customer.getLastName() +
                " with Current Parcel ID: " + parcel.getParcelID() + " and Fee : " + parcelFee);

        releaseParcel(customer, parcel, parcelMap, log);

        customerQueue.removeCustomer(customer);

        return customerDetails + " and Fee: " + parcelFee;

    }

    public double calculateFee(Parcel parcel) {
        double parcelFee = 0.0;

        double dimensions = parcel.getHeight() * parcel.getLength() * parcel.getWidth();

        parcelFee += dimensions * 0.5;

        parcelFee += parcel.getWeight() * 0.5;

        parcelFee = applyDiscount(parcel, parcelFee);

        return parcelFee;

    }

    public double applyDiscount(Parcel parcel, double parcelFee) {
        String parcelID = parcel.getParcelID();

        if (parcelID.charAt(0) == 'X') {
            try {

                int idNo = Integer.parseInt(parcelID.substring(1));

                if (idNo < 100) { //if parcel id has numbers less than 100, give discount
                    parcelFee *= 0.7;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid parcel ID: " + parcelID);
            }

        }
        return parcelFee;
    }


    public void releaseParcel(Customer customer, Parcel parcel, ParcelMap parcelMap, Log log) {
        parcel.setStatus(Parcel.Status.COLLECTED);


        log.addEvent("Parcel with ID: " + parcel.getParcelID() + " has been collected by " +
                customer.getFirstName() + " " + customer.getLastName());
        parcelMap.removeParcel(parcel.getParcelID());

    }

}