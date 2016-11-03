package com.doteam.dopattern;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;


import static android.content.Intent.ACTION_SCREEN_OFF;
import static android.content.Intent.ACTION_USER_PRESENT;

//재실행되는 리시버
public class RestartReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION_USER_PRESENT)) {
            Intent i2 = new Intent(context, UnlockDetectService.class);
            MainActivity.IsFirst = true;
            context.startService(i2);
        }
    }
}