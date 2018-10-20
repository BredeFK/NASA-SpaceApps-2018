package nasa.space.apps.rocketlaunch.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Mission implements Parcelable {
    public static final Creator<Mission> CREATOR = new Creator<Mission>() {
        @Override
        public Mission createFromParcel(Parcel in) {
            return new Mission(in);
        }

        @Override
        public Mission[] newArray(int size) {
            return new Mission[size];
        }
    };
    private String name;
    private String description;
    private String wikiurl;

    public Mission() {

    }

    protected Mission(Parcel in) {
        name = in.readString();
        description = in.readString();
        wikiurl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(wikiurl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%nMISSION NAME: %s%nMISSION DESCRIPTION: %s%nMISSION WIKIURL: %s", name, description, wikiurl);
    }

    public String getWikiurl() {
        return wikiurl;
    }

    public void setWikiurl(String wikiurl) {
        this.wikiurl = wikiurl;
    }
}
