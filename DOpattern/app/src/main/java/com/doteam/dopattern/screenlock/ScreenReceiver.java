package com.doteam.dopattern.screenlock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.doteam.dopattern.MainActivity;

import static android.content.Intent.ACTION_SCREEN_OFF;
import static android.content.Intent.ACTION_USER_PRESENT;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


//각종 broadcast 를 받는 클래스
public class ScreenReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION_USER_PRESENT)) {

            Intent i = new Intent(context, com.doteam.dopattern.UnlockDetectService.class);
            MainActivity.IsFirst=true;
            context.startService(i);
        }
//        if (intent.getAction().equals(ACTION_SCREEN_OFF)) {
//
//            Intent i = new Intent(context, com.doteam.dopattern.OnKeyDown.class);
//            MainActivity.IsFirst=true;
//            context.startService(i);
//        }
        if (intent.getAction().equals(ACTION_SCREEN_OFF)) {

            Intent i = new Intent(context, com.doteam.dopattern.BlankActivity.class);
//            MainActivity.IsFirst=true;
            i.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

    }
}