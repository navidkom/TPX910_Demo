package com.telpo.tpx910_demo.utils;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Hashtable;

import static android.content.Context.BATTERY_SERVICE;
import static android.graphics.Color.BLACK;

public class Utils {

    /***
     * 获取设备属性 command 为指令 返回结果为一行时使用
     * */
    public static String runSysProperty(String command) {
        String prop = "";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            String line = null;
            if ((line = in.readLine()) != null) {
                prop = line;
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            prop = "";
        } finally {
            try {
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return prop;
    }
}
