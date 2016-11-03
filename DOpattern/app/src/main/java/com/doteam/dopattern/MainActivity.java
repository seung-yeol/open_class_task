package com.doteam.dopattern;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.doteam.dopattern.apklist.ApkListActivity;
import com.doteam.dopattern.screenlock.ScreenService;

public class MainActivity extends AppCompatActivity {

    public static boolean Delete_mode;
    public static boolean Isupbutton;
    public static boolean IsFirst;
    public static boolean onService;

    public String getStringPreferences(String str) {
        SharedPreferences pref = getSharedPreferences(str, 0);
        String tempget = pref.getString(str, null);
        return tempget;
    }

    private Button onBtn, offBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //맨  처음 기본 암호가 있는지 확인 없으면 메인액티비티로 이동해서 설정해야함
        String patternString = getStringPreferences("prefCorrect");

        setContentView(R.layout.activity_main);


        Button upsetbutton = (Button) findViewById(R.id.upsetbutton);
        Button updeletebutton = (Button) findViewById(R.id.updeletebutton);

        Button downsetbutton = (Button) findViewById(R.id.downsetbutton);
        Button downdeletebutton = (Button) findViewById(R.id.downdeletebutton);

        upsetbutton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Delete_mode = false;
                Isupbutton = true;
                Intent intent = new Intent(getApplicationContext(), ApkListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        downsetbutton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Delete_mode = false;
                Isupbutton = false;
                Intent intent = new Intent(getApplicationContext(), ApkListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        updeletebutton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Delete_mode = true;
                Isupbutton = true;
                Intent intent = new Intent(getApplicationContext(), ApkListActivity.class);
                startActivity(intent);

            }
        });

        downdeletebutton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Delete_mode = true;
                Isupbutton = false;
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
                onService=true;  //나중에 프리퍼런스로
                startService(intent);

            }
        });
        offBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                stopService(intent);

            }

        });

    }
}