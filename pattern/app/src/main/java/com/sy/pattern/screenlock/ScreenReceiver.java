package com.sy.pattern.screenlock;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class ScreenReceiver extends BroadcastReceiver {

    private KeyguardManager km = null;
    private KeyguardManager.KeyguardLock keyLock = null;
    private TelephonyManager telephonyManager = null;
    private boolean isPhoneIdle = true;


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

            // 안드로이드 기본 잠금화면 없애기 위해
            if (km == null)
                km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

            if (keyLock == null)
                keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);

            //전화 왔을시 스크린락을 해제하기위한 리스너,서비스 설정
            if(telephonyManager == null){
                telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
                telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
            }

            if(isPhoneIdle) {
                disableKeyguard();
            }

            Intent i = new Intent(context, ScreenLockActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public void disableKeyguard() {
        keyLock.disableKeyguard();
    }

    //ScreenService의 onDestroy()에서 mReceiver.reenableKeyguard();를
    // 호출해주면 내 잠금화면은 사라지고 기본 잠금화면이 다시 나타나게 됨
    // 우리한텐 별 필요없는거지만 혹시나해서
    public void reenableKeyguard() {
        keyLock.reenableKeyguard();
    }


    //전화오면 스크린락 해제됨
    private PhoneStateListener phoneListener = new PhoneStateListener(){

        @Override
        public void onCallStateChanged(int state, String incomingNumber){
            switch(state){
                case TelephonyManager.CALL_STATE_IDLE :
                    isPhoneIdle = true;
                    break;

                case TelephonyManager.CALL_STATE_RINGING :
                    isPhoneIdle = false;
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK :
                    isPhoneIdle = false;
                    break;
            }
        }
    };
}