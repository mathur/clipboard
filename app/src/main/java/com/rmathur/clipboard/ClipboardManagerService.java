package com.rmathur.clipboard;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

public class ClipboardManagerService extends Service {

    private static final int NOTIFICATION_ID = 100;
    private static final String NOTIFICATION_CHANNEL_ID = "main_channel";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Get the layouts to use in the custom notification
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification_small);
        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notification_large);
            createNotificationChannel();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Monitoring clipboard")
                    .setContentText("BET")
                    .setOngoing(true)
                    .setShowWhen(false)
                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(notificationLayout)
                    .setCustomBigContentView(notificationLayoutExpanded)
                    .setTicker("lol")
                    .setVisibility(Notification.VISIBILITY_SECRET)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setDefaults(0);

            Notification notification = builder.build();
            startForeground(NOTIFICATION_ID, notification);
//        } else {
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                    .setSmallIcon(R.drawable.ic_launcher_background)
//                    .setContentTitle("TITLE")
//                    .setContentText("BET")
//                    .setOngoing(true)
//                    .setDefaults(0)
//
//                    .setTicker("lol")
//                    .setPriority(NotificationCompat.PRIORITY_LOW)
//                    .setWhen(0);
//
//            Notification notification = builder.build();
//            startForeground(NOTIFICATION_ID, notification);
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                return;
            }
        }).start();

        return Service.START_STICKY;
    }

    private void createNotificationChannel() {
        String channelName = "Main Channel";
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_MIN));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}