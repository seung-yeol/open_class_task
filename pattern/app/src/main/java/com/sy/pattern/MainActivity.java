package com.sy.pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    static public PatternView patternView;

    static public String patternString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patternView = (PatternView) findViewById(R.id.patternView);
        Toast.makeText(getApplicationContext(),"미완성버전, 맨처음 입력한 패턴이 비밀번호가 됩니다.", Toast.LENGTH_LONG).show();
        patternView.setOnPatternDetectedListener(new PatternView.OnPatternDetectedListener() {

            @Override
            public void onPatternDetected() {
                if (patternString == null) {
                    patternString = patternView.getPatternString();
                    patternView.clearPattern();

                    finish();
                    Intent intent = new Intent(getApplicationContext(),CheckingActivity.class);
                    startActivity(intent);
                }

            }
        });



        Button button = (Button) findViewById(R.id.button);  //설정을 누르면 설정창에 들어가는 부분
        button.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               finish();  //패턴 종료후 설정창 켜지게함
               Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
               startActivity(intent);
           }

        });





    }

}
