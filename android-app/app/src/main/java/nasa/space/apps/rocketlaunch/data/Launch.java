package nasa.space.apps.rocketlaunch.data;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Launch {
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
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss", Locale.getDefault());
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = format.parse(net.replace(" UTC", ""));
            this.netInMills = date.getTime();
        } catch (ParseException e) {
            this.netInMills = -1;
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
