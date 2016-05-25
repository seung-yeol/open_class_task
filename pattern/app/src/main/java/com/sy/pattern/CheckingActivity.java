package com.sy.pattern;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class CheckingActivity extends Activity {

    private PatternView patternView=MainActivity.patternView;

    private String patternString=MainActivity.patternString;

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
                    //여기 if구문에 특수기능을 넣어야함f

                }


                Toast.makeText(getApplicationContext(), "틀렸습니다! 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                patternView.clearPattern();
            }
        });
    }
}