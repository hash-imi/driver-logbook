package com.example.mohammad.bluetoothconnect;

/**
 * Created by mohammad on 07.10.15.
 */
public class DataProviderLogBook {
        private String id;
        private String driver;
        private String departure_date_time;
        private String departure_location;
        private String arrival_date_time;
        private String arrival_location;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDriver() {
            return driver;
        }

        public void setSurname(String driver) {
            this.driver = driver;
        }

        public String getDeparture_date_time() {
            return departure_date_time;
        }

        public void setDeparture_date_time(String departure_date_time) {
            this.departure_date_time = departure_date_time;
        }

        public String getDeparture_location() {
            return departure_location;
        }

        public void setDeparture_location(String departure_location) {
            this.departure_location = departure_location;
        }

        public String getArrival_date_time() {
            return arrival_date_time;
        }

        public void setArrival_date_time(String arrival_date_time) {
            this.arrival_date_time = arrival_date_time;
        }

        public String getArrival_location(){
            return arrival_location;
        }

        public void setArrival_location(String arrival_location){
            this.arrival_location = arrival_location;
        }

        public DataProviderLogBook(String id, String driver, String departure_date_time, String departure_location,
                                   String arrival_date_time, String arrival_location){
            this.id = id;
            this.driver = driver;
            this.departure_date_time = departure_date_time;
            this.departure_location = departure_location;
            this.arrival_date_time = arrival_date_time;
            this.departure_location= arrival_location;
        }
    }
