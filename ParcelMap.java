package Logic_Model;

import java.util.HashMap;
import java.util.Map;

public class ParcelMap {

    private Map<String, Parcel> parcelMap;


    public ParcelMap() {
        parcelMap = new HashMap<>();
    }

    public void addParcel(Parcel parcel)
    {
        parcelMap.put(parcel.getParcelID(), parcel);
    }

    public Parcel getParcel(String parcelID) {

        return parcelMap.get(parcelID);
    }

    public Parcel removeParcel(String parcelID) {

        return parcelMap.remove(parcelID);
    }

    public boolean containsParcel(String parcelID) {

        return parcelMap.containsKey(parcelID);
    }

    public int getParcelCount() {
        return parcelMap.size();
    }

    public String toString() {
        String S = "";
        if (parcelMap.isEmpty()) {
            S = "No parcels in the list!";

        } else {
            for (Map.Entry<String, Parcel> entry : parcelMap.entrySet()) {
                Parcel parcel = entry.getValue();
                S += "Logic.Parcel ID: " + parcel.getParcelID() +
                        ", Status: " + parcel.getStatusofParcel() +
                        ", Weight: " + parcel.getWeight() +
                        ", Dimensions: " + parcel.getDimensions(parcel.getHeight(), parcel.getWidth(), parcel.getLength()) + "\n";
            }
        }

        return S;
    }

}



