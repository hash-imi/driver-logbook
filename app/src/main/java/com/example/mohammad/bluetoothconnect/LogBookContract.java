package com.example.mohammad.bluetoothconnect;

/**
 * Created by mohammad on 27.07.15.
 */
public class LogBookContract {
    public LogBookContract(){

    }
    public static abstract class NewLogBookInfo{
        public static final String ID = "id";
        public static final String DEPARTURE_DATE_TIME = "departure_date_time";
        public static final String ARRIVAL_DATE_TIME = "arrival_date_time";
        public static final String DEPARTURE_LOCATION = "departure_location";
        public static final String ARRIVAL_LOCATION = "arrival_location";
        public static final String DRIVER_NAME = "driver_name";
        public static final String TABLE_LOG_BOOK = "log_book_info";
    }
}
