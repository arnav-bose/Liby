package com.example.arnavbose.liby;

import android.content.SharedPreferences;

/**
 * Created by Pradumn K Mahanta on 24-04-2016.
 **/
public class AppData {
    public static SharedPreferences GCMRegisteration;
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static boolean LOGIN_CHECK = false;
    public static boolean WELCOME_MESSAGE = false;
    public static String SERVER_ADDRESS = "192.168.89.1";//10.0.2.2 for Emulator and 192.168.89.1 for phone.

    public static SharedPreferences myData;

}
