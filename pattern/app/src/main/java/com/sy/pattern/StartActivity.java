package com.sy.pattern;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sy.pattern.apklist.ApkListActivity;
import com.sy.pattern.screenlock.ScreenService;


//패턴 스트링이 null이면 메인 액티비티 호출해서 prefCorrect 키값부터 넣고
//스타트 액티비티가 앱 실행시 화면!!
public class StartActivity extends Activity {


    public String getStringPreferences(String str) {
        SharedPreferences pref = getSharedPreferences(str,0);
        String tempget = pref.getString(str,null);
        return tempget;
    }
    private Button onBtn, offBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String patternString = getStringPreferences("prefCorrect");


        if(patternString == null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            finish();
            startActivity(intent);
        }
        setContentView(R.layout.startlayout);




        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ApkListActivity.class);
                startActivity(intent);

            }
        });

        onBtn = (Button) findViewById(R.id.onbtn);
        offBtn = (Button) findViewById(R.id.offbtn);

        onBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ScreenService.class);

                startService(intent);

            }
        });
        offBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick (View v){

                Intent intent = new Intent(getApplicationContext(), ScreenService.class);

                stopService(intent);

            }

        });
    }
}

