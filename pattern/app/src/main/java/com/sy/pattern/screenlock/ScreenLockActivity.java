package com.sy.pattern.screenlock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.sy.pattern.GlobalVariable;
import com.sy.pattern.PatternView;
import com.sy.pattern.R;


//전원을 끄면 ScreenService에 의해 실행되어지는 액티비티
public class ScreenLockActivity extends Activity {

    int i = 0;

    public void saveStringPreferences(String str, String str2) {
        SharedPreferences pref = getSharedPreferences(str, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(str, str2);
        editor.commit();

    }

    public String getStringPreferences(String str) {
        SharedPreferences pref = getSharedPreferences(str, 0);
        String tempget = pref.getString(str, null);
        return tempget;
    }

    public int getintPreferences(String str) {   //키를 가지고 값을 불러옴
        SharedPreferences pref = getSharedPreferences(str, 0);
        int tempget = pref.getInt(str, 0);
        return tempget;
    }
    //이제 비번이 영구저장되니까 임시 리셋버튼 때문에 만든거고 나중에 지울것

    static public PatternView patternView;
    static public String patternString;


    //여기있는 정수프리퍼런스 저장은 개발중에 만든것으로 그냥 리셋버튼 누르면 비밀번호 null되는거

    public void saveintPreferences(String str, int a) {   //프리퍼런스 저장 키와 정수형 키값을 받음
        SharedPreferences pref = getSharedPreferences(str, a);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(str, a);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);


        GlobalVariable.Rows = getintPreferences("prefRows");
        GlobalVariable.Columns = getintPreferences("prefCols");
        setContentView(R.layout.screenlocklayout);
        patternView = (PatternView) findViewById(R.id.patternView3);

        patternString = getStringPreferences("prefCorrect");


        Toast.makeText(getApplicationContext(), "패턴 비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
        patternView.setOnPatternDetectedListener(new PatternView.OnPatternDetectedListener() {

            @Override
            public void onPatternDetected() {
                String amho = patternView.getPatternString();
                if (patternString.equals(amho)) {
                    finish();

                    //요거때문에 패턴풀면 아에 꺼진거
                    //android.os.Process.killProcess(android.os.Process.myPid());
                } else if (i < 10) {
                    for (i = 0; i < 10; i++) {
                        if (amho.equals(getStringPreferences("pattern" + i))) {
                            PackageManager pm = getPackageManager();
                            Intent intent = pm.getLaunchIntentForPackage(getStringPreferences("APP" + i));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                            //요거때문에 패턴풀면 아에 꺼진거
                            //android.os.Process.killProcess(android.os.Process.myPid());
                            break;
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "틀렸습니다! 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                    patternView.clearPattern();
                }

            }
        });
        //이제 비번이 영구저장되니까 임시 리셋버튼 만든거고 나중에 지울것

    }
}
