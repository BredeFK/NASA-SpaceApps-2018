package nasa.space.apps.rocketlaunch.data;

public class Mission {
    private String name;
    private String description;

    public Mission(){

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
        return String.format("%nMISSION NAME: %s%nMISSION DESCRIPTION: %s", name, description);
    }
}
