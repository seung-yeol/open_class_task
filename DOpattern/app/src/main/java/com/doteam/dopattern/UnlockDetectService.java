package com.doteam.dopattern;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;


public class UnlockDetectService extends Service {


    PackageManager pm = null;

    public String getStringPreferences(String str) {
        SharedPreferences pref = getSharedPreferences(str, 0);
        String tempget = pref.getString(str, null);
        return tempget;
    }


    @Override
    public void onCreate() {

    }


    @Override
    public int onStartCommand(Intent i, int flags, int startid) {

        pm = getPackageManager();
        KeyguardManager km = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);


        if (!km.isKeyguardLocked()) {
            Toast.makeText(getApplicationContext(), "락해제", Toast.LENGTH_SHORT).show();

        }
            Intent Service = new Intent(getApplicationContext(), UnlockDetectService.class);
            stopService(Service);
            return super.onStartCommand(i, flags, startid);
    }


        //앱이 강제종료되면 다시 실행하게함.


//        if(targetapp!=null && MainActivity.IsFirst==true) {
//           MainActivity.IsFirst = false;
//        }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

