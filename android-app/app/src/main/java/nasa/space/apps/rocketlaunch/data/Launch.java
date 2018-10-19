package nasa.space.apps.rocketlaunch.data;


import java.util.ArrayList;

public class Launch {
    private String name;
    private Location location;
    private String net;
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

    @Override
    public String toString() {

        StringBuilder missionsString = new StringBuilder();
        for (Mission mission : missions) {
            if (mission.toString() != null)
                missionsString.append(mission.toString());
        }
        return String.format("%nLAUNCH NAME: %s%s%nLAUNCH NET: %s%s%s", name, location.toString(), net, rocket.toString(), missionsString.toString());
    }
}
