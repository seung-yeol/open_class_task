package com.sy.pattern;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;


public class SettingActivity extends Activity {

    private PatternView patternView;

    private String patternString;

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
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        final TextView print2 = (TextView) findViewById(R.id.textView2);
        SeekBar collength = (SeekBar) findViewById(R.id.seekBar2);
        collength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress2, boolean fromUser) {
                print2.setText("세로길이 : " + (progress2 +3));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar2) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar2) {

            }
        });

    }
}
