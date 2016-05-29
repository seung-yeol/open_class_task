package com.sy.pattern;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class CheckingActivity extends Activity {

    //이제 비번이 영구저장되니까 임시 리셋버튼 때문에 만든거고 나중에 지울것
    public void saveStringPreferences(String str,String str2){
        SharedPreferences pref = getSharedPreferences(str,0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(str,str2);
        editor.commit();
    }

    private PatternView patternView=MainActivity.patternView;
    private String patternString=MainActivity.patternString;

    //여기있는 정수프리퍼런스 저장은 개발중에 만든것으로 그냥 리셋버튼 누르면 비밀번호 null되는거
    public void saveintPreferences(String str, int a){   //프리퍼런스 저장 키와 정수형 키값을 받음
        SharedPreferences pref= getSharedPreferences(str,a);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(str,a);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checking_main);
        patternView = (PatternView) findViewById(R.id.patternView2);
        Toast.makeText(getApplicationContext(), "패턴 비밀번호를 입력하세요!", Toast.LENGTH_LONG).show();
        patternView.setOnPatternDetectedListener(new PatternView.OnPatternDetectedListener() {

            @Override
            public void onPatternDetected() {

                if (patternString.equals(patternView.getPatternString())) {
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                    //exit app
                    //여기 if구문에 특수기능을 넣어야함
                    //선택 앱 실행 되는 기능.
                }
                Toast.makeText(getApplicationContext(), "틀렸습니다! 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                patternView.clearPattern();
            }
        });
        //이제 비번이 영구저장되니까 임시 리셋버튼 만든거고 나중에 지울것
        Button resetbutton = (Button) findViewById(R.id.resetbutton);
        resetbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveStringPreferences("prefCorrect",null);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
}