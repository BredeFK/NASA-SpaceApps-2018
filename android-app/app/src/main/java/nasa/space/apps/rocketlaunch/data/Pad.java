package nasa.space.apps.rocketlaunch.data;

public class Pad {
    private String name;
    private String wikiurl;

    public Pad(){

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
        return String.format("%nPAD NAME: %s%nPAD WIKIURL: %s", name, wikiurl);
    }
}
