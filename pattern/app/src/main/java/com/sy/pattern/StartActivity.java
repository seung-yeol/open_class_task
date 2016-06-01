package com.sy.pattern;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sy.pattern.apklist.ApkListActivity;

public class StartActivity extends Activity {
    private Button onBtn, offBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

