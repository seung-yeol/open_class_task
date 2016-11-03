package com.sy.pattern;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sy.pattern.apklist.ApkListActivity;
import com.sy.pattern.screenlock.ScreenService;
//import com.sy.pattern.screenlock.ScreenService;


//패턴 스트링이 null이면 메인 액티비티 호출해서 prefCorrect 키값부터 넣고
//스타트 액티비티가 앱 실행시 화면!!
public class StartActivity extends Activity {

    public void saveStringPreferences(String str,String str2){
        SharedPreferences pref = getSharedPreferences(str,0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(str,str2);
        editor.commit();
    }

    public String getStringPreferences(String str) {
        SharedPreferences pref = getSharedPreferences(str,0);
        String tempget = pref.getString(str,null);
        return tempget;
    }
    private Button onBtn, offBtn, setbutton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //맨  처음 기본 암호가 있는지 확인 없으면 메인액티비티로 이동해서 설정해야함
        String patternString = getStringPreferences("prefCorrect");


        if(patternString == null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            finish();
            startActivity(intent);
        }
        setContentView(R.layout.startlayout);



        Button appsetbutton = (Button) findViewById(R.id.appsetbutton);
        Button appdeletebutton = (Button) findViewById(R.id.appdeletebutton);

        appsetbutton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                GlobalVariable.Delete_mode=false;
                Intent intent = new Intent(getApplicationContext(), ApkListActivity.class);
                startActivity(intent);

            }
        });
        appdeletebutton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                GlobalVariable.Delete_mode=true;
                if(GlobalVariable.Delete_mode) {
                    GlobalVariable.Delete_mode = false;

                    //String imsiapp = packageInfo.packageName;
                    for(int i=0;i<10;i++) {


                            //if (packname[i].equals(imsiapp)) {
                            saveStringPreferences("APP" + i, null);
                            saveStringPreferences("pattern" + i, null);

                            //}

                    }
                    Toast.makeText(getApplicationContext(),"앱과 패턴이 모두 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                }
//                Intent intent = new Intent(getApplicationContext(), ApkListActivity.class);
//                startActivity(intent);

            }
        });
        setbutton = (Button) findViewById(R.id.setbutton);
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
        setbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }


        });
        Button License = (Button)findViewById(R.id.License);
        License.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OpensourceLicense.class);
                startActivity(intent);
            }
        });
    }
}

