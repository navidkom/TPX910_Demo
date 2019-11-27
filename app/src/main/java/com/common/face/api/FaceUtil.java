package com.common.face.api;

public class FaceUtil {


    static {
        System.load("/system/lib/libfaceutil.so");
    }

    //----------------------------------------------------------------------
    // Name:			RelaysSet
    // Parameters:
    //      num         number of the relay, 1~5
    //		on			1==>turn on，0==>turn off
    // Returns:			=0 : success;
    //					<0 : error;
    // Description:		relay setting
    //----------------------------------------------------------------------
    public static native int RelaysSet(int num, int on);


    //----------------------------------------------------------------------
    // Name:			RS485SetMode
    // Parameters:
    //		mode		1==>send mode，0==>receive mode
    // Returns:			=0 : success;
    //					<0 : error;
    // Description:		set the RS485 mode
    //----------------------------------------------------------------------
    public static native int RS485SetMode(int mode);


    //----------------------------------------------------------------------
    // Name:			ExtInputGetState
    // Parameters:
    //		name		the name of external input signal, ExtAlarm1 ~ ExtAlarm8
    // Returns:			0 or 1 : signal state;
    //					<0 : error;
    // Description:		get the external input state
    //----------------------------------------------------------------------
    public static native int ExtInputGetState(String name);
}
