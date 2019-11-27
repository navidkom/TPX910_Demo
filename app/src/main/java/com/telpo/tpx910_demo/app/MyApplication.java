package com.telpo.tpx910_demo.app;

import android.app.Application;
import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telpo.tpx910_demo.MainActivity;
import com.telpo.tpx910_demo.R;
import com.telpo.tpx910_demo.utils.Utils;

import cat.ereza.customactivityoncrash.config.CaocConfig;

import static com.telpo.tpx910_demo.app.Constants.NOT_TEST_YET;

public class MyApplication extends Application {
    public static Map<String,String> intentMap = new HashMap<>();
    public static List<String> testProjects = new ArrayList<>();
    public static List<Integer> testResult = new ArrayList<>();
    public static int SDK_VERSION = android.os.Build.VERSION.SDK_INT;


    @Override
    public void onCreate() {
        super.onCreate();
        initBugReport();
        initMap();
        initTestConfig();
        initTestResult();
    }
    /**
     * 初始化caoc bug分析工具
     * */
    private void initBugReport(){
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
                .enabled(true) //default: true
                .showErrorDetails(true) //default: true
                .showRestartButton(true) //default: true
                .trackActivities(true) //default: false
                .minTimeBetweenCrashesMs(2000) //default: 3000
                .errorDrawable(R.drawable.ic_launcher) //default: bug image
                .restartActivity(MainActivity.class) //default: null (your app's launch activity)
                .apply();
    }

    /**
     * 在这里配置相应测试activity对应的intent
     * 以键值对方式存储
     * **/
    private  void initMap(){
        intentMap.put(Constants.BASIC_INFORMATION, Constants.BASIC_INFORMATION_INTENT);
        intentMap.put(Constants.SPEAKER, Constants.SPEAKER_INTENT);
        intentMap.put(Constants.EARPHONE, Constants.EARPHONE_INTENT);
        intentMap.put(Constants.BLUETOOTH, Constants.BLUETOOTH_INTENT);
        intentMap.put(Constants.ETHERNET_LAN, Constants.ETHERNET_LAN_INTENT);
        intentMap.put(Constants.ETHERNET_WAN, Constants.ETHERNET_WAN_INTENT);
        intentMap.put(Constants.WIFI, Constants.WIFI_INTENT);
        intentMap.put(Constants.USBPORT, Constants.USBPORT_INTENT);
        intentMap.put(Constants.TFCARD, Constants.TFCARD_INTENT);
        intentMap.put(Constants.CAMERA, Constants.CARMERA_INTENT);
        intentMap.put(Constants.CAMERA_BACK, Constants.CARMERA_INTENT_BACK);
        intentMap.put(Constants.BLF, Constants.BLF_INTENT);
        intentMap.put(Constants.LCD, Constants.LCD_INTENT);
        intentMap.put(Constants.SIM, Constants.SIM_INTENT);
        intentMap.put(Constants.TOUCH_SCREEN, Constants.TOUCH_SCREEN_INTENT);
        intentMap.put(Constants.KEY_PAD, Constants.KEY_PAD_INTENT);
        intentMap.put(Constants.DC_ADAPTER, Constants.DC_ADAPTER_INTENT);
        intentMap.put(Constants.BATTERY, Constants.BATTERY_INTENT);
        intentMap.put(Constants.HANDLE, Constants.HANDLE_INTENT);
        intentMap.put(Constants.AP_ROUTER, Constants.AP_ROUTER_INTENT);
        intentMap.put(Constants.PSTN_CALL, Constants.PSTN_CALL_INTENT);
        intentMap.put(Constants.VOIP_CALL, Constants.VOIP_CALL_INTENT);
        intentMap.put(Constants.PROPERTY_CALL, Constants.PROPERTY_CALL_INTENT);
        intentMap.put(Constants.SPEAKER_TO_HANDLE,Constants.SPEAKER_TO_MIC_INTENT);
        intentMap.put(Constants.HDMI_IN,Constants.HDMI_IN_INTENT);
        intentMap.put(Constants.HDMI_OUT,Constants.HDMI_OUT_INTENT);
        intentMap.put(Constants.RELAY,Constants.RELAY_INTENT);
        intentMap.put(Constants.EXTERNAL_INPUT_SIGNAL,Constants.EXTERNAL_INPUT_SIGNAL_INTENT);
        intentMap.put(Constants.RS485_OR_RS232,Constants.RS485_OR_RS232_INTENT);
    }
    /**
     * 在这里登记需要测试的项目
     * 每次新增测试项 只需要继承BaseFactoryActivity 同时为其配置对应action 和测试名称
     * 然后在这里登记
     * **/
    private void initTestConfig(){
        int index = 0;

        if(Utils.runSysProperty("getprop ro.boot.board.name").startsWith("TPX910")
                ||Utils.runSysProperty("getprop ro.boot.board.name").startsWith("TPS465B")) {
            testProjects.add(index++, Constants.BASIC_INFORMATION);
            testProjects.add(index++, Constants.SPEAKER);
            testProjects.add(index++, Constants.BLUETOOTH);
            testProjects.add(index++, Constants.WIFI);
            testProjects.add(index++, Constants.AP_ROUTER);
            testProjects.add(index++, Constants.ETHERNET_WAN);
            testProjects.add(index++, Constants.ETHERNET_LAN);
            testProjects.add(index++, Constants.TOUCH_SCREEN);
            testProjects.add(index++, Constants.LCD);
            testProjects.add(index++, Constants.USBPORT);
            testProjects.add(index++, Constants.CAMERA);
            testProjects.add(index++, Constants.RELAY);
            testProjects.add(index++, Constants.EXTERNAL_INPUT_SIGNAL);
            testProjects.add(index++, Constants.RS485_OR_RS232);
        }else {
            testProjects.add(index++, Constants.BASIC_INFORMATION);
            testProjects.add(index++, Constants.SPEAKER);
            testProjects.add(index++, Constants.BLUETOOTH);
            testProjects.add(index++, Constants.WIFI);
            testProjects.add(index++, Constants.AP_ROUTER);
            testProjects.add(index++, Constants.ETHERNET_WAN);
            testProjects.add(index++, Constants.ETHERNET_LAN);
            testProjects.add(index++, Constants.SIM);
            testProjects.add(index++, Constants.TOUCH_SCREEN);
            testProjects.add(index++, Constants.LCD);
//        testProjects.add(10, Constants.DC_ADAPTER);
            testProjects.add(index++, Constants.BATTERY);
            testProjects.add(index++, Constants.HANDLE);
            if (Build.MODEL.startsWith("V2")) {
                testProjects.add(index++, Constants.SPEAKER_TO_HANDLE);
            }
            testProjects.add(index++, Constants.BLF);
            testProjects.add(index++, Constants.USBPORT);
            testProjects.add(index++, Constants.TFCARD);
            testProjects.add(index++, Constants.CAMERA);
            if (Build.MODEL.startsWith("V2")) {
                testProjects.add(index++, Constants.HDMI_IN);
                testProjects.add(index++, Constants.HDMI_OUT);
            }
//        testProjects.add(19, Constants.CAMERA_BACK);
            testProjects.add(index++, Constants.KEY_PAD);
            testProjects.add(index++, Constants.PSTN_CALL);
            /**物业管家 和 通用版本添加 pstn测试*/
//        if(Constants.DEVICE_BRANCH.equals(Constants.BRANCH_COMMON)) {
//            testProjects.add(17, Constants.PSTN_CALL);
//        } else if(Constants.DEVICE_BRANCH.equals(Constants.BRANCH_PROPERTY)){
//            testProjects.add(17, Constants.PSTN_CALL);}
//        if(DEVICE_MODEL.equals(V200)){
//        }
//        else if(DEVICE_MODEL.equals(V101)){
//            if(Constants.DEVICE_BRANCH.equals(Constants.BRANCH_VOIP)){
//                testProjects.add(16, Constants.KEY_PAD);
//            }else {
//                testProjects.add(17, Constants.KEY_PAD);
//            }
//        }
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void initTestResult(){
        for(int i = 0;i<testProjects.size();i++){
            testResult.add(i,NOT_TEST_YET);
        }
    }
}
