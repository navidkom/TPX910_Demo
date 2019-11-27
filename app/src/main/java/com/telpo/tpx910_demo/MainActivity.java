package com.telpo.tpx910_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button  mButton_alarm, mButton_relay, mButton_rs485,mButton_SerialPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton_alarm = (Button) findViewById(R.id.btn_alarm);
        mButton_alarm.setOnClickListener(this);

        mButton_relay = (Button) findViewById(R.id.btn_relay);
        mButton_relay.setOnClickListener(this);

        mButton_rs485 = (Button) findViewById(R.id.btn_rs485);
        mButton_rs485.setOnClickListener(this);

        mButton_SerialPort = (Button) findViewById(R.id.btn_SerialPort);
        mButton_SerialPort.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_alarm:
                startActivity(new Intent(MainActivity.this, AlarmActivity.class));
                break;
            case R.id.btn_relay:
                startActivity(new Intent(MainActivity.this, RelayActivity.class));
                break;
            case R.id.btn_rs485:
                startActivity(new Intent(MainActivity.this, RS485Activity.class));
                break;
            case R.id.btn_SerialPort:
                startActivity(new Intent(MainActivity.this, SerialPortActivity.class));
                break;
        }
    }
}