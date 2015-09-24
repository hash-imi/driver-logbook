package com.example.mohammad.bluetoothconnect;

import android.provider.BaseColumns;

/**
 * Created by mohammad on 25.07.15.
 */
public class DepartureDateTimeContract {

    public DepartureDateTimeContract(){

    }
    public static abstract class NewDepartureDateTimeInfo implements BaseColumns{
        public static final String ID_DEPARTURE_DATE_TIME = "id_departure_date_time";
        public static final String DEPARTURE_DATE = "departure_date";
        public static final String DEPARTURE_TIME = "departure_time";
        public static final String TABLE_DEPARTURE_DATE_TIME =  "departure_date_time_info";
    }
}
