package nasa.space.apps.rocketlaunch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import nasa.space.apps.rocketlaunch.data.Launch;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Launch> launches = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int id = 1073;
        String name = "Ariane 5 ECA | BepiColombo";

        for(int index = 0; index < 1; index++){
            Launch launch = new Launch(id, name);
        }

    }
}
