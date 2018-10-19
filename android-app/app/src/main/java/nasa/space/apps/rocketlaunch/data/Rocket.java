package nasa.space.apps.rocketlaunch.data;

public class Rocket {
    private String name;
    private String familyName;
    private String wikiURL;
    private String imageURL;

    public Rocket(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getWikiURL() {
        return wikiURL;
    }

    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return String.format("%nROCKET NAME: %s%nROCKET FAMILY NAME: %s%nROCKET WIKI URL: %s%nIMAGE URL: %s", name, familyName, wikiURL, imageURL);
    }
}
