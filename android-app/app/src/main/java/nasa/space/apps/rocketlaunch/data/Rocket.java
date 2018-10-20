package nasa.space.apps.rocketlaunch.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Rocket {
    private String name;
    private String familyname;
    private String wikiurl;
    private String imageurl;
    private Bitmap imageInBitmap;

    public Rocket() {

    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getWikiurl() {
        return wikiurl;
    }

    public void setWikiurl(String wikiurl) {
        this.wikiurl = wikiurl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Bitmap getImageInBitmap() {
        return imageInBitmap;
    }

    public void setImageInBitmap() {
        this.imageInBitmap = getBitmapFromURL(this.imageurl);
    }

    @Override
    public String toString() {
        return String.format("%nROCKET NAME: %s%nROCKET FAMILY NAME: %s%nROCKET WIKI URL: %s%nIMAGE URL: %s", name, familyname, wikiurl, imageurl);
    }


}
