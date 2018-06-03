package id.co.hananda.atry.BroadcastReceiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

import id.co.hananda.atry.MainActivity;
import id.co.hananda.atry.R;

/**
 * Created by user on 3/6/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01"; //ANDROID 0 API 26
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);
//
//            notificationChannel.setDescription("Channel description");
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.setVibrationPattern(new long[] {0,1000,500,1000});
//            notificationChannel.enableVibration(true);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context , NOTIFICATION_CHANNEL_ID);
//
//            notificationBuilder.setAutoCancel(true)
//                    .setDefaults(Notification.DEFAULT_ALL)
//                    .setWhen(System.currentTimeMillis())
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setTicker("Wohoo!")
//                    .setContentTitle("Default notification")
//                    .setContentText("Lorem Ipsum dolor sit amet")
//                    .setContentInfo("info");
//            notificationManager.notify(1,notificationBuilder.build()); // ANDROID 0 API 26


        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                builder.setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("Buatanku Untukmu :D")
                        .setContentText("Hey! Dont forget me")
                        .setSound(alarmSound)
                        .setAutoCancel(true)
                        .setWhen(when)
                        .setContentIntent(pendingIntent)
                        .setVibrate(new long[] {1000,1000,1000,1000});


    }
}
