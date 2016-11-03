package com.doteam.a123456.screenlock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import static android.content.Intent.ACTION_SCREEN_OFF;


//각종 broadcast 를 받는 클래스
public class ScreenReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION_SCREEN_OFF)) {

            Intent i = new Intent(context, com.doteam.a123456.MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i);
        }
    }
}