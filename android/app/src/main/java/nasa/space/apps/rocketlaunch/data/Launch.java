package nasa.space.apps.rocketlaunch.data;

import java.util.ArrayList;

public class Launch {
    private int id;
    private String name;
    private Location location;
    private Rocket rocket;
    private ArrayList<Mission> missions;
    private LaunchServicesProgram lsp;

    public Launch(int id, String name){
        this.id = id;
        this.name = name;
    }

}
