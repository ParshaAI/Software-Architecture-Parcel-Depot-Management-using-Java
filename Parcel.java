package Logic_Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parcel {
    private String parcelID;
    private int daysInDepot;
    private double height;
    private double width;
    private double length;
    private double weight;
    private Status status;


    public Parcel(String pID, int days, double h, double w, double l, double we) {
        parcelID = pID;
        daysInDepot = days;
        height = h;
        width = w;
        length = l;
        weight = we;
        status = Status.WAITING_FOR_COLLECTION; // setting it to default because they need to be collected.
    }

    public String getDimensions(double height, double width, double length) {
        return height + " x " + width + " x " + length;
    }

    public enum Status {
        WAITING_FOR_COLLECTION,
        COLLECTED;
    }


    public void setStatus(Status stat) {
        status = stat;
    }

    public Status getStatusofParcel() {
        return status;
    }


    public String getParcelID() {
        return parcelID;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }




    public double getWeight() {
        return weight;
    }


    public static void readParcelsFromFile(String filePath, ParcelMap parcelMap, Log log) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values.length >= 6) {
                String parcelID = values[0].trim();
                double width = Double.parseDouble(values[1].trim());
                double height = Double.parseDouble(values[2].trim());
                double length = Double.parseDouble(values[3].trim());
                int daysInDepot = Integer.parseInt(values[4].trim());
                double weight = Double.parseDouble(values[5].trim());

                Parcel parcel = new Parcel(parcelID, daysInDepot, height, width, length, weight);
                parcelMap.addParcel(parcel);
                parcel.setStatus(Parcel.Status.WAITING_FOR_COLLECTION);
                log.addEvent("Added parcel: " + parcelID + " with dimensions: " + height + " x " + width + " x " + length);
            }
        }
        br.close();
    }
}
