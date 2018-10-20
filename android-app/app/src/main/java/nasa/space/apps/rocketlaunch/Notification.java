package nasa.space.apps.rocketlaunch;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import nasa.space.apps.rocketlaunch.data.Launch;

public class Notification extends IntentService {

    // private static final String KEY = "messagesCount";
    // private static int oldCount = 0;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;

    public Notification() {
        super("Notification");
    }


    @Override
    public void onHandleIntent(@Nullable Intent intent) {

        this.notificationManager = (NotificationManager) this.getSystemService(Service.NOTIFICATION_SERVICE);

        // Create the NotificationChannel only on API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    this.getString(R.string.app_name), "MessagesChannel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Notification");

            // Register channel with the system
            notificationManager.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(this, channel.getId());
        } else {
            builder = new NotificationCompat.Builder(this);
        }

        sendNotification();
    }

    private void sendNotification() {

        String Title = this.getString(R.string.take_off);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Set icon
        this.builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        this.builder.setContentTitle(Title);
        this.builder.setContentText(getString(R.string.messageSoon));
        this.builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        this.builder.setContentIntent(pendingIntent);
        this.builder.setAutoCancel(true);                           // Closes the notification when clicked on
        this.notificationManager.notify(0, builder.build());

    }
}
