package nasa.space.apps.rocketlaunch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

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
    private ArrayList<Launch> launches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String dataURL = "https://nasa-spaceapps-2018.herokuapp.com/api";

        try {
            fillLaunchList(dataURL);
        } catch (IOException | JSONException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void overrideNetworkOnMainThreadException() {
        // TODO : Remove this code and put it in async later : https://stackoverflow.com/a/9289190/8883030
        // https://developer.android.com/reference/android/os/AsyncTask
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    protected ArrayList<Launch> fillLaunchArray(String urlName) throws IOException, JSONException {
        overrideNetworkOnMainThreadException();
        URL url = new URL(urlName);
        JSONObject jsonObject = getJsonObjectFromURL(url);

        if (jsonObject == null) {
            return new ArrayList<>();
        }

        JSONArray jsonArray = jsonObject.getJSONArray("launches");

        Gson gson = new Gson();

        Launch[] launches = gson.fromJson(jsonArray.toString(), Launch[].class);

        return new ArrayList<>(Arrays.asList(launches));
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

    private void fillLaunchList(String url) throws IOException, JSONException {
        ListView listView = findViewById(R.id.LaunchesList);
        launches = fillLaunchArray(url);
        LaunchAdapter adapter = new LaunchAdapter(MainActivity.this, R.layout.launch_item);
        if (launches.size() != 0) {
            adapter.clear();
            for (Launch launch : launches) {
                //    if(launch.getLsp().getName().equals("SpaceX")){
                launch.setnetInMills();
                adapter.add(launch);
                //    }

            }
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(MainActivity.this, "Could not get data :/", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {

            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            sendLSPnamesForSpinner(intent);
            startActivity(intent);
        }
        return true;
    }

    private void sendLSPnamesForSpinner(Intent intent) {
        ArrayList<String> lspNames = new ArrayList<>();
        if (launches != null) {
            lspNames.add(getString(R.string.all));
            for (Launch launch : launches) {
                if (!lspNames.contains(launch.getLsp().getName()))
                    lspNames.add(launch.getLsp().getName());
            }
        }

        intent.putExtra("length", lspNames.size());
        for (int i = 0; i < lspNames.size(); i++) {
            intent.putExtra("name" + i, lspNames.get(i));
        }
    }
}
