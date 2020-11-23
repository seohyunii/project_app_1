package com.dowellcomputer.fcmexample.fcmexample;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.PowerManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG="FirebaseMsgService";
    private String msg,title,color;


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        System.out.println("gg");
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE );
        @SuppressLint("InvalidWakeLockTag")
        PowerManager.WakeLock wakeLock = pm.newWakeLock( PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG" );
        wakeLock.acquire(3000);




        title=remoteMessage.getData().get("title");
        msg=remoteMessage.getData().get("body");
        System.out.println(title);
        System.out.println(msg);


        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent contentIntent=PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),0);
        String channelId="Channel ID";
        NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(this,channelId).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[]{1,1000});

        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            System.out.println("gggg");
            String channelName="Channel Name";
            NotificationChannel channel=new NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(channel);
    }
        notificationManager.notify(0,mBuilder.build());

        mBuilder.setContentIntent(contentIntent);
    }
}
