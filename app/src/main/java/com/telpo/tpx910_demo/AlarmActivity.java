package com.telpo.tpx910_demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.common.face.api.FaceUtil;

public class AlarmActivity extends Activity {

    private TextView mTextView_alarm[] = new TextView[8];
    private TextView mTextView_pir[] = new TextView[2];
    private int alarm_state[] = new int[8];
    private int pir_state[] = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        mTextView_alarm[0] = (TextView) findViewById(R.id.textView_alarm1);
        mTextView_alarm[1] = (TextView) findViewById(R.id.textView_alarm2);
        mTextView_alarm[2] = (TextView) findViewById(R.id.textView_alarm3);
        mTextView_alarm[3] = (TextView) findViewById(R.id.textView_alarm4);
        mTextView_alarm[4] = (TextView) findViewById(R.id.textView_alarm5);
        mTextView_alarm[5] = (TextView) findViewById(R.id.textView_alarm6);
        mTextView_alarm[6] = (TextView) findViewById(R.id.textView_alarm7);
        mTextView_alarm[7] = (TextView) findViewById(R.id.textView_alarm8);

        mTextView_pir[0] = (TextView) findViewById(R.id.textView_pir1);
        mTextView_pir[1] = (TextView) findViewById(R.id.textView_pir2);

        //get current states of alarm
        int i;
        for(i=0; i<8; i++) {
            try {
                alarm_state[i] = FaceUtil.ExtInputGetState("ExtAlarm"+(i+1));
            }catch (Exception e){
                alarm_state[i] = -1;
            }
            mTextView_alarm[i].setText("Alarm" + (i+1) + ":" + alarm_state[i]);
        }

        for(i=0; i<2; i++) {
            try {
                pir_state[i] = FaceUtil.ExtInputGetState("ExtPIR"+(i+1));
            }catch (Exception e){
                pir_state[i] = -1;
            }
            mTextView_pir[i].setText("PIR" + (i+1) + ":" + pir_state[i]);
        }


        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.ExtInput");
        registerReceiver(mUsbReceiver, filter);
    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String name;
            int state, i;

            name = intent.getStringExtra("name");
            state = intent.getIntExtra("state", 0);
            String test;


            Log.d("TAG", "onReceive: " + name + "," + state);

            for(i=0; i<8; i++)
            {
                if(name.equals("ExtAlarm" + (i+1))) {
                    alarm_state[i] = state;
                    mTextView_alarm[i].setText("Alarm" + (i+1) + ":" + alarm_state[i]);
                }
            }

            for(i=0; i<2; i++)
            {
                if(name.equals("ExtPIR" + (i+1))) {
                    pir_state[i] = state;
                    mTextView_pir[i].setText("PIR" + (i+1) + ":" + pir_state[i]);
                }
            }
        }
    };
}
