package com.sy.pattern;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//메인 액티비티가 각종 암호를 저장하는걸로 하자
public class MainActivity extends Activity {
    static public PatternView patternView;

    static public String patternString;

    public int getintPreferences(String str) {   //키만 받음
        SharedPreferences pref = getSharedPreferences(str,0);
        int tempget = pref.getInt(str,0);
        return tempget;
    }
    public String getStringPreferences(String str) {
        SharedPreferences pref = getSharedPreferences(str,0);
        String tempget = pref.getString(str,null);
        return tempget;
    }
    public void saveStringPreferences(String str,String str2){
        SharedPreferences pref = getSharedPreferences(str,0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(str,str2);
        editor.commit();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        GlobalVariable.Rows=getintPreferences("prefRows");
        GlobalVariable.Columns=getintPreferences("prefCols");
        setContentView(R.layout.activity_main);
        patternView = (PatternView) findViewById(R.id.patternView);
        patternString = getStringPreferences("prefCorrect");


        //스트링이 중복되지 않는 곳에 들어가게 일단 null  아닌지 판단하려고 불러들여옴

        int count=0;

//prefCorrect에 키값이 null이면 일단 기본 패턴 비밀번호를 입력한다
        //이부분에서 리셋때문에 버그생기는거 수정해야함
        //앱이 켜진 채로 리셋시키면 앱연동창 켰을 때 기본 비밀번호 설정이 뜸

        if (patternString == null){
            Toast.makeText(getApplicationContext(),"미완성버전, 맨처음 입력한 패턴이 기본 비밀번호가 됩니다.", Toast.LENGTH_LONG).show();
            patternView.setOnPatternDetectedListener(new PatternView.OnPatternDetectedListener() {

                @Override
                public void onPatternDetected() {
                    patternString = patternView.getPatternString();
                    saveStringPreferences("prefCorrect",patternString);
                    patternView.clearPattern();

                    finish();
                }
            });
        }
        //기본 패턴이 있다면 연동된 프로그램 패턴 저장

        else if(count <10){
            patternView.setOnPatternDetectedListener(new PatternView.OnPatternDetectedListener() {

                String targetpattern[]={null,null,null,null,null,null,null,null,null,null};
                @Override
                public void onPatternDetected() {

                    targetpattern[GlobalVariable.list]= patternView.getPatternString();
                    saveStringPreferences("pattern"+GlobalVariable.list,targetpattern[GlobalVariable.list]);
                    Toast.makeText(getApplicationContext(),"저장되었습니다.",Toast.LENGTH_SHORT).show();
                    patternView.clearPattern();

                    finish();
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
            finish();
        }

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