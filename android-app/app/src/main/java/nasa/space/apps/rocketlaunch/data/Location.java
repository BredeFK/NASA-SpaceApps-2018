package nasa.space.apps.rocketlaunch.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Location implements Parcelable {
    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
    private ArrayList<Pad> pads;
    private String name;
    private String countrycode;

    public Location() {

    }

    protected Location(Parcel in) {
        pads = in.createTypedArrayList(Pad.CREATOR);
        name = in.readString();
        countrycode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(pads);
        dest.writeString(name);
        dest.writeString(countrycode);
    }

    @Override
    public int describeContents() {
        return 0;
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
            if (pad.toString() != null)
                padsString.append(pad.toString());
        }
        return String.format("%nLOCATION name: %s%nLOCATION COUNTRY CODE: %s%s", name, countrycode, padsString.toString());
    }
}
