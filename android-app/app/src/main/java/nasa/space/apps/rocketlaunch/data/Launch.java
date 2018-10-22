package nasa.space.apps.rocketlaunch.data;


import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import nasa.space.apps.rocketlaunch.MainActivity;

public class Launch implements Parcelable {

    private int id;
    private String name;
    private Location location;
    private String net;
    private long netInMills;
    private Rocket rocket;
    private ArrayList<Mission> missions;
    private Lsp lsp;

    public Launch() {
    }


    protected Launch(Parcel in) {
        id = in.readInt();
        name = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
        net = in.readString();
        netInMills = in.readLong();
        rocket = in.readParcelable(Rocket.class.getClassLoader());
        missions = in.createTypedArrayList(Mission.CREATOR);
        lsp = in.readParcelable(Lsp.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeParcelable(location, flags);
        dest.writeString(net);
        dest.writeLong(netInMills);
        dest.writeParcelable(rocket, flags);
        dest.writeTypedList(missions);
        dest.writeParcelable(lsp, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Launch> CREATOR = new Creator<Launch>() {
        @Override
        public Launch createFromParcel(Parcel in) {
            return new Launch(in);
        }

        @Override
        public Launch[] newArray(int size) {
            return new Launch[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public ArrayList<Mission> getMissions() {
        return missions;
    }

    public void setMissions(ArrayList<Mission> missions) {
        this.missions = missions;
    }

    public Lsp getLsp() {
        return lsp;
    }

    public void setLsp(Lsp lsp) {
        this.lsp = lsp;
    }

    public void setnetInMills() {
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss", Locale.ENGLISH);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateString = net.replace(" UTC", "");

        try {
            Date date = format.parse(dateString);
            System.out.println("NOT ERROR: " + dateString);
            this.netInMills = date.getTime();
        } catch (ParseException e) {
            System.out.println("ERROR: " + dateString);
            this.netInMills = System.currentTimeMillis();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNetInMills() {
        return netInMills;
    }

    @Override
    public String toString() {

        StringBuilder missionsString = new StringBuilder();
        for (Mission mission : missions) {
            if (mission.toString() != null)
                missionsString.append(mission.toString());
        }
        return String.format("%nLAUNCH ID: %s%nLAUNCH NAME: %s%s%nLAUNCH NET: %s%s%s", id, name, location.toString(), net, rocket.toString(), missionsString.toString());
    }
}
