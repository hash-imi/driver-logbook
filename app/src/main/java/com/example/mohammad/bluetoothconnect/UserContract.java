package com.example.mohammad.bluetoothconnect;

import android.provider.BaseColumns;

/**
 * Created by Mohammad on 10.06.2015.
 */
public class UserContract {

    public UserContract(){

    }

    public static abstract class NewUserInfo implements BaseColumns{
        //Initialise the column name
        public static final String USER_PRENAME = "user_prename";
        public static final String USER_SURNAME = "user_surname";
        public static final String USER_EMAIL = "user_email";
        public static final String USER_MOBILE = "user_mobile";
        public static final String USER_REGISTER_NUMBER = "user_register_number";
        public static final String TABLE_NAME = "user_info";
        //public static final String TABLE_NAME =  "reg_info";

    }
}
