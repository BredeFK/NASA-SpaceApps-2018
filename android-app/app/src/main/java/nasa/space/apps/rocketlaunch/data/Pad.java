package nasa.space.apps.rocketlaunch.data;

public class Pad {
    private String name;
    private String wikiURL;

    public Pad(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiURL() {
        return wikiURL;
    }

    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }

    @Override
    public String toString() {
        return String.format("%nPAD NAME: %s%nPAD WIKIURL: %s", name, wikiURL);
    }
}
