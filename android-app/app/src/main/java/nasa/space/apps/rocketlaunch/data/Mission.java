package nasa.space.apps.rocketlaunch.data;

public class Mission {
    private String name;
    private String description;
    private String wikiurl;

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
        return String.format("%nMISSION NAME: %s%nMISSION DESCRIPTION: %s%nMISSION WIKIURL: %s", name, description, wikiurl);
    }

    public String getWikiurl() {
        return wikiurl;
    }

    public void setWikiurl(String wikiurl) {
        this.wikiurl = wikiurl;
    }
}
