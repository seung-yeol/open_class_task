package com.sy.pattern.screenlock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.sy.pattern.MainActivity;
import com.sy.pattern.PatternView;
import com.sy.pattern.R;


public class ScreenLockActivity extends Activity {

    //이제 비번이 영구저장되니까 임시 리셋버튼 때문에 만든거고 나중에 지울것
    public void saveStringPreferences(String str,String str2){
        SharedPreferences pref = getSharedPreferences(str,0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(str,str2);
        editor.commit();


    }

    private PatternView patternView= MainActivity.patternView;
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
        setContentView(R.layout.screenlocklayout);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED

                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

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

    //스크린액티비티가 정지할때( 홈버튼이라던가 백버튼 메뉴버튼 눌럿을때)
    //실행되는부분.   문제가있는데, 홈버튼을 누르면 5초후에 반응함
    //구글에서 막아버려서..
    @Override
    public void onPause(){
        super.onPause();

        finish();
        Intent intent = new Intent(getApplicationContext(), ScreenLockActivity.class);
        startActivity(intent);

    }

    //요거는 하드웨어버튼 누르면 무시하는건데 백버튼은 무시하는데 메뉴버튼,홈버튼이 무시가 안됨
    //구글에서 보안땜시 막아서 그래서 위에거 추가하긴햇는데 그것마저도 문제가 있네
    //최상위 뷰 띄우는것도 있긴한데 그것도 곧있으면 막힐거 같애서 그냥 위에걸로 하는게 낳을듯.
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if(event.isSystem()) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}