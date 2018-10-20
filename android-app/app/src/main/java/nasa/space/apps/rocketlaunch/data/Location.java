package nasa.space.apps.rocketlaunch.data;

import java.util.ArrayList;

public class Location {
    private ArrayList<Pad> pads;
    private String name;
    private String countrycode;

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

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    @Override
    public String toString() {
        StringBuilder padsString = new StringBuilder();
        for (Pad pad : pads) {
            if(pad.toString() != null)
                padsString.append(pad.toString());
        }
        return String.format("%nLOCATION name: %s%nLOCATION COUNTRY CODE: %s%s", name, countrycode,padsString.toString());
    }
}
