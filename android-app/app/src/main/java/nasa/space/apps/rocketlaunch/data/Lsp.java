package nasa.space.apps.rocketlaunch.data;

public class Lsp {
    private String name;
    private String wikiURL;

    public Lsp(){

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
        if(name != null && wikiURL != null){
            return String.format("%nLSP NAME: %s%nLSP WIKIURL: %s", name, wikiURL);
        }
        return "";

    }
}
