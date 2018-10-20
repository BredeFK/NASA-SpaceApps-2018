package nasa.space.apps.rocketlaunch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import nasa.space.apps.rocketlaunch.data.Launch;

public class LaunchAdapter extends ArrayAdapter<Launch> {
    private Activity activity;
    private int resource;
    private boolean test = false;


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
        // if (convertView == null) {

        convertView = activity.getLayoutInflater().inflate(R.layout.launch_item, parent, false);
        TextView name = convertView.findViewById(R.id.launch_name);
        final TextView timeLeft = convertView.findViewById(R.id.launch_timeLeft);
        ImageView image = convertView.findViewById(R.id.launch_image);

        name.setText(launch.getName());



        Bitmap bitmapImage = launch.getRocket().getImageInBitmap();


        if (bitmapImage != null) {
            image.setImageBitmap(bitmapImage);
        }
        new CountDownTimer(launch.getNetInMills() - System.currentTimeMillis(), 1000) {

            public void onTick(long millisUntilFinished) {
                timeLeft.setText(getTimeInSmoothFormat(launch.getNetInMills() - System.currentTimeMillis()));
            }

            public void onFinish() {
                timeLeft.setText(R.string.take_off);
            }
        }.start();

            /*
            convertView = this.activity.getLayoutInflater().inflate(R.layout.product_view, parent, false);

            ViewHolder holder = new ViewHolder();

            holder.image = convertView.findViewById(R.id.product_image);
            holder.title = convertView.findViewById(R.id.product_title);
            holder.subTitle = convertView.findViewById(R.id.product_subtitle);
            holder.price = convertView.findViewById(R.id.product_price);
            holder.pricePerUnit = convertView.findViewById(R.id.product_price_pr_unit);
            holder.link = convertView.findViewById(R.id.product_link);
            convertView.setTag(holder);


            holder.image.setImageBitmap(productRow.getImageName());
            holder.title.setText(productRow.getTitle());
            holder.subTitle.setText(productRow.getSubTitle());
            System.out.println(productRow.getTitle() + ": " + productRow.getUnit());
            holder.price.setText(productRow.getPriceFormatted());
            holder.pricePerUnit.setText(productRow.getCompareUnitPriceWithUnit());

            final View finalView = convertView;
            holder.link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(getContext(), StorePageActivity.class);
                    bundle.putString("itemURL", productRow.getUrl().toString());
                    intent.putExtras(bundle);
                    finalView.getContext().startActivity(intent);
                }
            });
            */

        //}
        return convertView;
    }

    private String getTimeInSmoothFormat(long distance) {
        int days = (int) Math.floor(distance / (1000 * 60 * 60 * 24));
        int hours = (int) Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        int minutes = (int) Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) Math.floor((distance % (1000 * 60)) / 1000);

        String time = days + "d " + hours + "h "
                + minutes + "m " + seconds + "s ";
        return time;
    }
}
