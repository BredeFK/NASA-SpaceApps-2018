package nasa.space.apps.rocketlaunch.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Lsp implements Parcelable {
    public static final Creator<Lsp> CREATOR = new Creator<Lsp>() {
        @Override
        public Lsp createFromParcel(Parcel in) {
            return new Lsp(in);
        }

        @Override
        public Lsp[] newArray(int size) {
            return new Lsp[size];
        }
    };
    private String name;
    private String wikiurl;

    public Lsp() {

    }

    protected Lsp(Parcel in) {
        name = in.readString();
        wikiurl = in.readString();
    }

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
        if (name != null && wikiurl != null) {
            return String.format("%nLSP NAME: %s%nLSP WIKIURL: %s", name, wikiurl);
        }
        return "";

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(wikiurl);
    }
}
