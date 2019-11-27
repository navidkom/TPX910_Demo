package com.telpo.tpx910_demo;

import android.app.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

//import com.common.face.api.FaceUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import  android_serialport_api.SerialPort;

public class SerialPortActivity  extends AppCompatActivity implements View.OnClickListener {
    private Button fail, pass,closeProject,send;
    private TextView receive,send_text;
    Spinner devices_choose;
    ToggleButton tb_RS485_Mode;

    private LineNumberReader localLineNumberReader;
    private String localObject;
    private String lastDeviceName = "";

    private InputStream mInputStream;
    protected OutputStream mOutputStream;
    private ReadThread mReadThread;
    protected SerialPort mSerialPort;
    private byte[] OriginalReceive = new byte[65536];
    private int ReceiveCount = 0;
    private ArrayAdapter<String> adapter = null;
    private static final String [] langurage = null;
    private List<File> devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_serialport);

        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);
        devices_choose = (Spinner) findViewById(R.id.device_choose);
        tb_RS485_Mode = (ToggleButton) findViewById(R.id.rs485_mode);

        receive = (TextView) findViewById(R.id.receive);
        send_text = (TextView) findViewById(R.id.send_text);

        pass = (Button) findViewById(R.id.bt_pass);
        pass.setOnClickListener(this);

        initData();
    }

    /**
     * Find the device in the specified directory
     * @return
     */
    public List<File> findDevices(){
        //check devices
        File[] arrayOfFile;
        List<File> ttyOfFile;
        arrayOfFile = new File("/dev").listFiles();
        ttyOfFile = new ArrayList<>();
        try {
            localLineNumberReader = new LineNumberReader(new FileReader("/proc/tty/drivers"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (;;) {
            try {
                localObject = localLineNumberReader.readLine();
                if (localObject == null){
                    localLineNumberReader.close();
                    Log.e("SerialPort1", "close");
                    break;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String str = ((String) localObject).substring(0, 21).trim();
            String[] localObjects = ((String) localObject).split(" +");
            if ((localObjects.length >= 5) && (localObjects[(localObjects.length - 1)].equals("serial"))) {
                Log.e("SerialPort1", "Found new driver: " + str + " _on: " + localObjects[(localObjects.length - 4)]);
                for (int i = 0;i < arrayOfFile.length;i++) {
                    if (arrayOfFile[i].getAbsolutePath().startsWith(localObjects[(localObjects.length - 4)]))
                        if(arrayOfFile[i].getAbsolutePath().startsWith("/dev/tty")){
                            ttyOfFile.add(arrayOfFile[i]);
                            Log.e("SerialPort2", "Found new device path : " + arrayOfFile[i]);
                        }
                }
            }

        }
        return ttyOfFile;
    }

    public void initData() {
        //find Devices
        devices = findDevices();

        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View paramAnonymousView)
            {
                try{
                    mOutputStream.write(toByteArray(send_text.getText().toString()));
                }catch (IOException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "fail in send", Toast.LENGTH_SHORT).show();
                }catch (NullPointerException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "open Serial Port please", Toast.LENGTH_SHORT).show();
                }
            }
        });




        List<String> devices_name = new ArrayList<>();
        for (File device:devices){
            String str = device.getPath();
            str = str.replace("/dev/","");
            devices_name.add(str);
        }

        adapter = new ArrayAdapter<String>(this,R.layout.item_devices,devices_name);
        //Setting Dropdown List Style
        adapter.setDropDownViewResource(R.layout.item_devices);
        //add Adapter to spinner

        devices_choose.setAdapter(adapter);
        devices_choose.setVisibility(View.VISIBLE);//Set default display

        devices_choose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                String choose_device = ((TextView)arg1).getText().toString();
                for (int i = 0;i < devices.size();i++){
                    String str = devices.get(i).getPath();
                    str = str.replace("/dev/","");
                    if(choose_device.equals(str)) {
                        if(!str.equals(lastDeviceName)){
                            receive.setText("");
                            OriginalReceive = new byte[65536];
                        }
                        lastDeviceName = str;
                        //todo
                        openDevice(devices.get(i).getAbsolutePath());
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        tb_RS485_Mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    FaceUtil.RS485SetMode(1);
                } else {
//                    FaceUtil.RS485SetMode(0);
                }
            }
        });
    }
    /**
     * open Serial Port
     * @param devices_path Path of serial device name
     */
    protected void openDevice(String devices_path){
        try{
            mSerialPort = getSerialPort(devices_path);
            mOutputStream = mSerialPort.getOutputStream();
            mInputStream = mSerialPort.getInputStream();
            mReadThread = new ReadThread();
            mReadThread.start();
        }catch (SecurityException localSecurityException){
            localSecurityException.printStackTrace();
        }catch (IOException localIOException){
            localIOException.printStackTrace();
        }catch (InvalidParameterException localInvalidParameterException){
            localInvalidParameterException.printStackTrace();
        }
    }

    /**
     * get Serial Port info
     * @param devices_path Address of communication equipment
     * @return
     */
    public SerialPort getSerialPort(String devices_path)
            throws SecurityException, IOException, InvalidParameterException
    {
//        if (mSerialPort == null) {
//            String str1 = "/dev/ttyHSL1";//devices name
        String str1 = devices_path;//Address of communication equipment
        int m = 115200;//baud rate : 115200
        int i = 3;//Data bits:8
        int j = 0;//Parity bit:None
        int k = 0;//Stop bit:1
        Log.i("setting", "stop_bit=" + String.valueOf(k));
        if ((str1.length() != 0) && (m != -1) && (i != -1) && (j != -1) && (k != -1)) {
            mSerialPort = new SerialPort(new File(str1), m, i, j, k, 0);
        }
        Log.i("paramter", "path=" + str1);
        Log.i("paramter", "baudrate=" + String.valueOf(m));
        Log.i("paramter", "data_bits=" + String.valueOf(i));
        Log.i("paramter", "check_bit=" + String.valueOf(j));
        Log.i("paramter", "stop_bit=" + String.valueOf(k));
//        }
        return mSerialPort;
    }

    /**
     * String to byte[]
     * @param str String
     * @return
     */
    public static byte[] toByteArray(String str) {
        if (str == null) {
            return null;
        }
        byte[] byteArray = str.getBytes();
        return byteArray;
    }

    /**
     * Continuous reading of sent data in a sub-thread
     */
    private class ReadThread
            extends Thread
    {
        private ReadThread() {}
        public void run()
        {
            super.run();
            for (;;){
                if (isInterrupted()) {
                    label11:{
                        return;
                    }
                }
                try{
                    Thread.sleep(200L);
                    try{
                        byte[] arrayOfByte = new byte['?'];
                        if (mInputStream == null) {
                            return;
                        }
                        int i = mInputStream.read(arrayOfByte);
                        if (i <= 0) {
                            continue;
                        }
                        onDataReceived(arrayOfByte, i);
                    }catch (IOException localIOException){
                        localIOException.printStackTrace();
                        return;
                    }
                }catch (InterruptedException e){
                    for (;;){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Receiving data from serial port
     * @param paramArrayOfByte data
     * @param paramInt size
     */
    protected void onDataReceived(final byte[] paramArrayOfByte, final int paramInt){
        runOnUiThread(new Runnable(){
            public void run(){
                if (receive != null){
                    OriginalReceive = appedByteArray(paramArrayOfByte, paramInt);
                    ReceiveCount += paramInt;
                    if (ReceiveCount > 65536) {
                        ReceiveCount = 0;
                    }
                    if (OriginalReceive != null) {
                        receive.setText(new String(OriginalReceive, 0,ReceiveCount));
                    }
                }
            }
        });
    }

    /**
     * byte[] join before
     * @param paramArrayOfByte data
     * @param paramInt size
     * @return
     */
    byte[] appedByteArray(byte[] paramArrayOfByte, int paramInt)
    {
        int i = 0;
        for (;;){
            if (i >= paramInt) {
                return OriginalReceive;
            }
            OriginalReceive[(ReceiveCount + i)] = paramArrayOfByte[i];
            i += 1;
        }
    }
    @Override
    public void onClick(View v) {
        int ret = -1;

        switch (v.getId()) {
            case R.id.send:
                try{
                    mOutputStream.write(toByteArray(send_text.getText().toString()));
                }catch (IOException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "fail in send", Toast.LENGTH_SHORT).show();
                }catch (NullPointerException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "open Serial Port please", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_rs485_receive:
                break;
        }
    }
}
