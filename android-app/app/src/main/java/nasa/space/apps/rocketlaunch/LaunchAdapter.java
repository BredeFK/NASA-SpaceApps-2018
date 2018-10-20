package nasa.space.apps.rocketlaunch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import nasa.space.apps.rocketlaunch.data.Launch;

class ViewHolder {
    TextView name;
    TextView timeLeft;
    TextView lspName;
    ImageView image;

}

public class LaunchAdapter extends ArrayAdapter<Launch> {
    private Activity activity;
    private int resource;
    private boolean test = false;
    private SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());


    public LaunchAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.activity = (Activity) context;
        this.resource = resource;
    }

    @Override
    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        final Launch launch = this.getItem(position);

        if (launch == null) {
            throw new NullPointerException();
        }

        // This simple line makes the ListView way smoother, thanks to
        // https://stackoverflow.com/a/25381936/8883030
        //if (convertView == null) {

        convertView = activity.getLayoutInflater().inflate(R.layout.launch_item, parent, false);
        final ViewHolder holder = new ViewHolder();

        holder.name = convertView.findViewById(R.id.info_name);
        holder.timeLeft = convertView.findViewById(R.id.launch_timeLeft);
        holder.lspName = convertView.findViewById(R.id.launch_lsp);
        holder.image = convertView.findViewById(R.id.info_image);
        convertView.setTag(holder);

        holder.name.setText(launch.getName());
        if (launch.getLsp() != null) {
            holder.lspName.setText(launch.getLsp().getName());
        } else {
            holder.lspName.setText("N/A");
        }

        Drawable.ConstantState black = activity.getResources().getDrawable(android.R.color.black).getConstantState();
        if (holder.image.getDrawable().getConstantState() == black) {
            Bitmap bitmapImage = launch.getRocket().getImageInBitmap();

            if (bitmapImage != null) {
                holder.image.setImageBitmap(bitmapImage);
            }
        }


        new CountDownTimer(launch.getNetInMills() - System.currentTimeMillis(), 1000) {

            public void onTick(long millisUntilFinished) {
                holder.timeLeft.setText(getTimeInSmoothFormat(launch.getNetInMills() - System.currentTimeMillis()));
                if ((launch.getNetInMills() - System.currentTimeMillis() - 900000) < 0 && (launch.getNetInMills() - System.currentTimeMillis() - 900000) > -2000) {
                    boolean isChecked = preferences.getBoolean("checked", true);
                    if (isChecked) {
                        Intent serviceIntent = new Intent(activity, Notification.class);
                        activity.startService(serviceIntent);
                    }
                }
            }

            public void onFinish() {
                holder.timeLeft.setText(R.string.take_off);
            }
        }.start();

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, LaunchInfo.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("LaunchObject", launch);
                intent.putExtras(bundle);
                activity.startActivity(intent);
            }
        });

        return convertView;
    }

    private String getTimeInSmoothFormat(long distance) {
        int days = (int) Math.floor(distance / (1000 * 60 * 60 * 24));
        int hours = (int) Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        int minutes = (int) Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) Math.floor((distance % (1000 * 60)) / 1000);


        return String.format(Locale.getDefault(), "%dd %dh %dm %ds", days, hours, minutes, seconds);
    }
}
