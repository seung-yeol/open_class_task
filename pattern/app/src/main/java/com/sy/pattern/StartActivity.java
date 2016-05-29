package com.sy.pattern;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sy.pattern.apklist.ApkListActivity;

public class StartActivity extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startlayout);

        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ApkListActivity.class);
                startActivity(intent);


            }
        });
    }
}
