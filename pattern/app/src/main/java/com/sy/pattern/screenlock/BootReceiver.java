package com.sy.pattern.screenlock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


//폰 부팅시 실행하는 리시버
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i = new Intent(context, ScreenService.class);
            context.startService(i);
        }
    }
}
