package com.doteam.dopattern.screenlock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//앱이 업데이트되었을때 재실행 하기 위해.
public class PackageReceiver extends BroadcastReceiver {
    @Override

    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (action.equals(Intent.ACTION_PACKAGE_ADDED)) {
            // 앱이 설치되었을 때

        } else if (action.equals(Intent.ACTION_PACKAGE_REMOVED)) {
            // 앱이 삭제되었을 때

        } else if (action.equals(Intent.ACTION_PACKAGE_REPLACED)) {
            // 앱이 업데이트 되었을 때
            Intent i = new Intent(context, ScreenService.class);
            context.startService(i);

        }



    }
}