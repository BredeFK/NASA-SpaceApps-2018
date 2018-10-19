package nasa.space.apps.rocketlaunch;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

import nasa.space.apps.rocketlaunch.data.Launch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO : Change this to Heroku link
        String dataURL = "https://launchlibrary.net/1.4/launch/next/1000";

        // TODO : Fix nullpointer in LSP

        overrideNetworkOnMainThreadException();
        ArrayList<Launch> launches = null;

        try {
            launches = fillLaunchArray(dataURL);
        } catch (IOException | JSONException e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (launches != null) {
            System.out.print("RESULT\n");
            for (Launch launch : launches) {
                System.out.println(launch.toString());
            }
        }
    }

    private void overrideNetworkOnMainThreadException() {
        // TODO : Remove this code and put it in async later : https://stackoverflow.com/a/9289190/8883030
        // https://developer.android.com/reference/android/os/AsyncTask
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    protected ArrayList<Launch> fillLaunchArray(String urlName) throws IOException, JSONException {
        ArrayList<Launch> launches = new ArrayList<>();
        URL url = new URL(urlName);
        JSONObject jsonObject = getJsonObjectFromURL(url);

        if (jsonObject == null) {
            return launches;
        }

        JSONArray jsonArray = jsonObject.getJSONArray("launches");

        Gson gson = new Gson();

        Launch[] launches1 = gson.fromJson(jsonArray.toString(), Launch[].class);

        return new ArrayList<>(Arrays.asList(launches1));
    }

    private JSONObject getJsonObjectFromURL(URL url) throws IOException {
        InputStream is = url.openConnection().getInputStream();
        StringBuilder sb = new StringBuilder();
        int cp;
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")))) {
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            return new JSONObject(jsonText);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        return null;
    }
}
