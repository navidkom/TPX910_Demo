package com.telpo.tpx910_demo.app;

import android.os.Build;
import android.os.Environment;

import com.telpo.tpx910_demo.utils.Utils;

public class Constants {
    /**测试项目
     * 可配置 如果根目录有配置文件 则读取配置文件信息生成测试项
     * 否则 按照V101 V200默认配置项测试
     * */
    public static final String SPEAKER = "扬声器测试";/**外放扬声器*/
    public static final String EARPHONE= "耳机测试";/**耳机孔*/
    public static final String BLUETOOTH= "蓝牙测试";/**蓝牙*/
    public static final String ETHERNET_LAN= "LAN口测试";/**lan口*/
    public static final String ETHERNET_WAN= "WAN口测试";/**wan口*/
    public static final String WIFI= "WIFI测试";/**wifi*/
    public static final String USBPORT= "USB测试";/**usb口*/
    public static final String TFCARD= "TF卡测试";/**tf卡插槽*/
    public static final String CAMERA = "前置相机测试";/**相机*/
    public static final String CAMERA_BACK = "后置相机测试";/**相机*/
    public static final String HDMI_IN = "HDMI输入测试";
    public static final String HDMI_OUT = "HDMI输出测试";
    public static final String BLF= "信号灯测试";/**信号指示灯*/
    public static final String LCD= "LCD测试";/**lcd屏*/
    public static final String SIM= "SIM卡测试";/**sim卡*/
    public static final String TOUCH_SCREEN= "触摸屏测试";/**触摸屏*/
    public static final String KEY_PAD= "按键测试";/**按键*/
    public static final String DC_ADAPTER = "充电测试";/**充电器插入拔出*/
    public static final String BATTERY="电池测试";/**电池电量*/
    public static final String HANDLE="手柄测试";/**手柄*/
    public static final String SPEAKER_TO_HANDLE="音频回环测试";
    public static final String AP_ROUTER="无线热点测试";/**无线热点*/
    public static final String BASIC_INFORMATION = "设备基本信息";/**软硬件版本*/
    public static final String PSTN_CALL = "PSTN测试";
    public static final String VOIP_CALL = "VOIP测试";
    public static final String PROPERTY_CALL = "物业管家测试";
    public static final String RELAY = "继电器测试";
    public static final String EXTERNAL_INPUT_SIGNAL = "外部输入信号测试";
    public static final String RS485_OR_RS232 = "RS485/RS232测试";

    /**
     * 测试项目的activity对应action
     * */
    public static final String SPEAKER_INTENT = "intent.factory.speaker";/**外放扬声器*/
    public static final String EARPHONE_INTENT= "intent.factory.earphone";/**耳机孔*/
    public static final String BLUETOOTH_INTENT= "intent.factory.bluetooth";/**蓝牙*/
    public static final String ETHERNET_LAN_INTENT= "intent.factory.lanport";/**lan口*/
    public static final String ETHERNET_WAN_INTENT= "intent.factory.wanport";/**wan口*/
    public static final String WIFI_INTENT= "intent.factory.wifi";/**wifi*/
    public static final String AP_ROUTER_INTENT="intent.factory.aprouter";/**无线热点测试*/
    public static final String USBPORT_INTENT= "intent.factory.usbport";/**usb口*/
    public static final String TFCARD_INTENT= "intent.factory.tfcard";/**tf卡插槽*/
    public static final String CARMERA_INTENT= "intent.factory.camera";/**相机*/
    public static final String CARMERA_INTENT_BACK= "intent.factory.backcamera";/**相机*/
    public static final String BLF_INTENT= "intent.factory.blf";/**信号指示灯*/
    public static final String LCD_INTENT= "intent.factory.lcd";/**lcd屏*/
    public static final String SIM_INTENT= "intent.factory.simcard";/**sim卡*/
    public static final String TOUCH_SCREEN_INTENT= "intent.factory.touchscreen";/**触摸屏*/
    public static final String KEY_PAD_INTENT= "intent.factory.keypad";/**按键*/
    public static final String DC_ADAPTER_INTENT = "intent.factory.chargeport";/**充电器插入拔出*/
    public static final String BATTERY_INTENT="intent.factory.battery";/**电池电量*/
    public static final String HANDLE_INTENT="intent.factory.handle";/**手柄测试*/
    public static final String BASIC_INFORMATION_INTENT = "intent.factory.information";/**软硬件版本*/
    public static final String PSTN_CALL_INTENT = "intent.factory.pstn.call";/**pstn版本通话测试*/
    public static final String VOIP_CALL_INTENT = "intent.factory.voip.call";/**voip版本通话测试*/
    public static final String PROPERTY_CALL_INTENT = "intent.factory.voip.call";/**voip版本通话测试*/
    public static final String ACTIVITY_MAIN_INTENT = "intent.factory.main";/**测试列表页*/
    public static final String HDMI_IN_INTENT = "intent.factory.hdmi.in";/**hdmi输入*/
    public static final String HDMI_OUT_INTENT = "intent.factory.hdmi.out";/**hdmi输出*/
    public static final String SPEAKER_TO_MIC_INTENT = "intent.factory.speakertomic";/**音频回环*/
    public static final String RELAY_INTENT = "intent.factory.relay";/**继电器*/
    public static final String EXTERNAL_INPUT_SIGNAL_INTENT = "intent.factory.external_input_signal";/**外部输入信号*/
    public static final String RS485_OR_RS232_INTENT = "intent.factory.rs485_rs232";/**RS485/RS232*/
    /**
     * 设备型号
     * */
    public static final String V200 = "V200";/**V200*/
    public static final String V101 = "V101";/**V101*/
    public static final String BRANCH_COMMON = "00";/**通用版本*/
    public static final String BRANCH_PROPERTY = "02";/**物业管家版本*/
    public static final String BRANCH_VOIP = "01";/**voip版本*/
    public static final String DEVICE_BRANCH = Utils.runSysProperty("getprop ro.telpo.branch");/**设备版本 包括 通用版本00 voip版本01 物业管家版本02*/

    public static final String DEVICE_MODEL = Build.MODEL;//Utils.runSysProperty("getprop ro.product.device");/**当前设备的设备型号 V101/V200....*/
    public static final String CONFIG_FILE_STORAGE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/factory_test_log.txt"; /**测试日志路径*/
    /**测试结果*/
    public static final int TEST_PASS = 1;/**测试通过*/
    public static final int TEST_FAIL = 0;/**测试未通过*/
    public static final int NOT_TEST_YET =2;/**尚未测试*/
    /**路径*/
    public static final String HANDLE_AUDIO_SWITCH_PATH = "/sys/class/hwctrl/gpio_ctrl/hand_free"; /**切换手柄通道*/
    public static final String SN_BAR_CODE_PATH = "data/data/com.telpo.engineeringmode/file/sn.png";/**sn条形码保存本地路径*/
    public static final String IMEI_BAR_CODE_PATH = "data/data/com.telpo.engineeringmode/file/imei.png"; /**imei条形码保存本地路径*/



}
