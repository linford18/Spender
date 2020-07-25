package com.example.user.spender;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by user on 26-08-2017.
 */
public class Notification_receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager =(NotificationManager) context.getSystemService((Context.NOTIFICATION_SERVICE));
        Intent repeating_intent = new Intent(context,MainActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingintent = PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingintent)
                .setSmallIcon(R.drawable.ic_settings_white_24dp)
                .setContentTitle("Have you added all details yet?")
                .setContentText("Add today's expenditure before you go to sleep.")
                .setAutoCancel(true)
                ;
        notificationManager.notify(100,builder.build());
    }
}