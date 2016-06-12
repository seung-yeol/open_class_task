package com.sy.pattern;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;



//가로세로 길이를 설정하는 액티비티
public class SettingActivity extends Activity {
    public void saveintPreferences(String str, int a){
        SharedPreferences pref = getSharedPreferences(str,0);

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(str,a);
        editor.commit();

    }

    int rows=3;
    int cols=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settinglayout);

        final TextView print = (TextView) findViewById(R.id.textView);
        SeekBar rawlength = (SeekBar) findViewById(R.id.seekBar);
        rawlength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                print.setText("가로길이 : " + (progress +3));
                rows = progress + 3;
                saveintPreferences("prefRows",rows);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        final TextView print2 = (TextView) findViewById(R.id.textView2);
        SeekBar collength = (SeekBar) findViewById(R.id.seekBar2);
        collength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress2, boolean fromUser) {
                print2.setText("세로길이 : " + (progress2 +3));
                cols = progress2 + 3;
                saveintPreferences("prefCols",cols);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar2) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar2) {}
        });

        Button setbutton = (Button) findViewById(R.id.setbutton);

        setbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //설정창을 누르면 종료되고 변경값으로 다시 실행되게 만듦
                GlobalVariable.Columns = cols;
                GlobalVariable.Rows=rows;

                finish();
            }
        });
    }
}
