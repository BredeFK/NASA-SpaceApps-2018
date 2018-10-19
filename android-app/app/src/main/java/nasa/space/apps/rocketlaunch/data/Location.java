package nasa.space.apps.rocketlaunch.data;

import java.util.ArrayList;

public class Location {
    private ArrayList<Pad> pads;
    private String name;
    private String countryCode;

    public Location() {

    }

    public ArrayList<Pad> getPads() {
        return pads;
    }

    public void setPads(ArrayList<Pad> pads) {
        this.pads = pads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        StringBuilder padsString = new StringBuilder();
        for (Pad pad : pads) {
            if(pad.toString() != null)
                padsString.append(pad.toString());
        }
        return String.format("%nLOCATION name: %s%nLOCATION COUNTRY CODE: %s%s", name, countryCode,padsString.toString());
    }
}
