package com.doteam.dopattern;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class test extends Activity {
    PackageManager pm = null;
    Intent Service;
    public String getStringPreferences(String str) {
        SharedPreferences pref = getSharedPreferences(str, 0);
        String tempget = pref.getString(str, null);
        return tempget;
    }
    @Override
    public void onCreate(Bundle i){
        super.onCreate(i);
        pm = getPackageManager();
        Service = new Intent(getApplicationContext(),test.class);
    }

    public test() {

    }

    @Override
    public boolean onKeyDown(int KeyCode, KeyEvent event) {
        Toast.makeText(getApplicationContext(),"키눌림", Toast.LENGTH_SHORT).show();
        if (KeyEvent.KEYCODE_VOLUME_UP == KeyCode) {

            String targetapp = getStringPreferences("APP" + 0);
            if (targetapp != null && MainActivity.IsFirst) {
                Intent intent = pm.getLaunchIntentForPackage(targetapp);
                MainActivity.IsFirst = false;
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        } else if (KeyEvent.KEYCODE_VOLUME_DOWN == KeyCode) {

            String targetapp = getStringPreferences("APP" + 1);
            if (targetapp != null && MainActivity.IsFirst) {
                Intent intent = pm.getLaunchIntentForPackage(targetapp);
                MainActivity.IsFirst = false;
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                finish();
            }
        }
        return false;
    }
}
