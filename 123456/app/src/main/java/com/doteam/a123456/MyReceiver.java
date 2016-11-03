package com.doteam.a123456;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.content.Intent.ACTION_SCREEN_OFF;
import static android.content.Intent.ACTION_USER_PRESENT;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.FLAG_INCLUDE_STOPPED_PACKAGES;

public class MyReceiver extends BroadcastReceiver {

            @Override
            public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION_USER_PRESENT)) {
            Intent i = new Intent(context,MainActivity.class);
            //MainActivity.IsFirst=true;
            i.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
        if (intent.getAction().equals(ACTION_SCREEN_OFF)) {
            Intent i = new Intent(context,MainActivity.class);
            i.addFlags(FLAG_ACTIVITY_NEW_TASK);
            //i.addFlags(FLAG_INCLUDE_STOPPED_PACKAGES);
            context.startActivity(i);
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
