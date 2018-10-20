package nasa.space.apps.rocketlaunch.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Pad implements Parcelable {
    private String name;
    private String wikiurl;

    public Pad() {

    }

    protected Pad(Parcel in) {
        name = in.readString();
        wikiurl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(wikiurl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pad> CREATOR = new Creator<Pad>() {
        @Override
        public Pad createFromParcel(Parcel in) {
            return new Pad(in);
        }

        @Override
        public Pad[] newArray(int size) {
            return new Pad[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiurl() {
        return wikiurl;
    }

    public void setWikiurl(String wikiurl) {
        this.wikiurl = wikiurl;
    }

    @Override
    public String toString() {
        return String.format("%nPAD NAME: %s%nPAD WIKIURL: %s", name, wikiurl);
    }
}
