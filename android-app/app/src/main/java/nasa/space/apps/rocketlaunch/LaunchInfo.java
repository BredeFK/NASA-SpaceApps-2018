package nasa.space.apps.rocketlaunch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import nasa.space.apps.rocketlaunch.data.Launch;

public class LaunchInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_info);

        fillInfoAboutLaunch();

    }

    protected void fillInfoAboutLaunch() {
        TextView name = findViewById(R.id.info_name);
        TextView desc = findViewById(R.id.info_description);
        ImageView image = findViewById(R.id.info_image);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Launch launch = new Launch();
            launch = bundle.getParcelable("LaunchObject");
            if(launch != null){
                name.setText(launch.getName());
                desc.setText(launch.getMissions().get(0).getDescription());
                image.setImageBitmap(launch.getRocket().getImageInBitmap());
            } else {
                Toast.makeText(this, "Could not get Launch data :/", Toast.LENGTH_LONG).show();
            }
        }

    }
}
