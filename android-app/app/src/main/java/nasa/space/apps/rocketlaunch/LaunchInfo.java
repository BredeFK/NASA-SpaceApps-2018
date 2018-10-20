package nasa.space.apps.rocketlaunch;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        TextView launchName = findViewById(R.id.info_name);
        ImageView image = findViewById(R.id.info_image);
        TextView mission = findViewById(R.id.info_missionName);
        TextView missionDescription = findViewById(R.id.info_description);
        TextView lsp = findViewById(R.id.staticLsp);
        TextView lspInfo = findViewById(R.id.info_lsp);
        TextView rocket = findViewById(R.id.staticRocket);
        TextView rocketNameAndFamily = findViewById(R.id.info_rocket);
        TextView location = findViewById(R.id.staticLocation);
        TextView locationAndCountry = findViewById(R.id.info_location);
        TextView pad = findViewById(R.id.staticPad);
        TextView padInfo = findViewById(R.id.info_pad);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Launch launch = new Launch();
            launch = bundle.getParcelable("LaunchObject");
            if (launch != null) {
                launchName.setText(launch.getName());
                image.setImageBitmap(launch.getRocket().getImageInBitmap());
                mission.setText(launch.getMissions().get(0).getName());
                missionDescription.setText(launch.getMissions().get(0).getDescription());
                lspInfo.setText(launch.getLsp().getName());
                rocketNameAndFamily.setText(String.format("%s | %s", launch.getRocket().getName(), launch.getRocket().getFamilyname()));
                locationAndCountry.setText(String.format("%s [%s]", launch.getLocation().getName(), launch.getLocation().getCountrycode()));
                padInfo.setText(launch.getLocation().getPads().get(0).getName());
                final Launch finalLaunch = launch;

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (finalLaunch.getRocket().getWikiurl() != null)
                            openWebPage(finalLaunch.getRocket().getWikiurl());
                    }
                });

                if (finalLaunch.getMissions().get(0).getWikiurl() != null) {
                    isClickable(mission);
                    mission.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openWebPage(finalLaunch.getMissions().get(0).getWikiurl());
                        }
                    });
                }

                if (finalLaunch.getLsp().getWikiurl() != null) {
                    isClickable(lsp);
                    lsp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openWebPage(finalLaunch.getLsp().getWikiurl());
                        }
                    });
                }

                if (finalLaunch.getRocket().getWikiurl() != null) {
                    isClickable(rocket);
                    rocket.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openWebPage(finalLaunch.getRocket().getWikiurl());
                        }
                    });
                }

                if (finalLaunch.getLocation().getPads().get(0).getWikiurl() != null) {
                    isClickable(pad);
                    pad.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openWebPage(finalLaunch.getLocation().getPads().get(0).getWikiurl());
                        }
                    });
                }


            } else {
                Toast.makeText(this, "Could not get Launch data :/", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void isClickable(TextView textView) {
        textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void openWebPage(String url) {
        Uri uri = Uri.parse(url);
        if (uri != null) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browserIntent);
        }
    }
}
