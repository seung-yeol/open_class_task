package com.doteam.dopattern;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * Created by voidblue on 2016-10-30.
 */
public class BlankActivity extends Activity{
    PackageManager pm = null;
    KeyguardManager km;
    KeyguardManager.KeyguardLock keyLock;
    public String getStringPreferences(String str) {
        SharedPreferences pref = getSharedPreferences(str, 0);
        String tempget = pref.getString(str, null);
        return tempget;
    }

    @Override
    public void onCreate(Bundle saveinitInstance){
        super.onCreate(saveinitInstance);
        pm = getPackageManager();
        KeyguardManager km = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);

//        if (!km.isKeyguardLocked()) {
//            Toast.makeText(getApplicationContext(), "락해제", Toast.LENGTH_SHORT).show();
//
//        }
    }



    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (KeyEvent.KEYCODE_VOLUME_DOWN == keyCode) {
            String targetapp = getStringPreferences("APP" + 1);
            if (targetapp != null) {
                Intent intent = pm.getLaunchIntentForPackage(targetapp);

                finish();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

        }
        if (KeyEvent.KEYCODE_VOLUME_UP == keyCode) {
            String targetapp = getStringPreferences("APP" + 0);
            if (targetapp != null) {
                Intent intent = pm.getLaunchIntentForPackage(targetapp);
                finish();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
        return false;
    }
}
