package com.telpo.tpx910_demo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.common.face.api.FaceUtil;

public class RelayActivity  extends Activity {

    private Switch mrelay[] = new Switch[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relay);

        mrelay[0] = (Switch) findViewById(R.id.switch_ralay1);
        mrelay[1] = (Switch) findViewById(R.id.switch_ralay2);
        mrelay[2] = (Switch) findViewById(R.id.switch_ralay3);
        mrelay[3] = (Switch) findViewById(R.id.switch_ralay4);
        mrelay[4] = (Switch) findViewById(R.id.switch_ralay5);


        for(int i=0; i<5; i++){
            mrelay[i].setChecked(false);
            // Init Relay
            FaceUtil.RelaysSet(i+1, 0);
        }

        mrelay[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    FaceUtil.RelaysSet(1, 1);
                else
                    FaceUtil.RelaysSet(1, 0);
            }
        });

        mrelay[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    FaceUtil.RelaysSet(2, 1);
                else
                    FaceUtil.RelaysSet(2, 0);
            }
        });

        mrelay[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    FaceUtil.RelaysSet(3, 1);
                else
                    FaceUtil.RelaysSet(3, 0);
            }
        });

        mrelay[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    FaceUtil.RelaysSet(4, 1);
                else
                    FaceUtil.RelaysSet(4, 0);
            }
        });

        mrelay[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    FaceUtil.RelaysSet(5, 1);
                else
                    FaceUtil.RelaysSet(5, 0);
            }
        });
    }
}
