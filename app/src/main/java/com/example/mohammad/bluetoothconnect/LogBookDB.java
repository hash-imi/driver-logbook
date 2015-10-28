package com.example.mohammad.bluetoothconnect;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mohammad on 29.09.15.
 */
public class LogBookDB extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSION = 3;
    public static final String COLUMN_ID = "_ID";

    /*private static final String CREATE_DEP_DATE_TIME_TABLE =
            "CREATE TABLE "+ DepartureDateTimeContract.NewDepartureDateTimeInfo.TABLE_DEPARTURE_DATE_TIME+" ("+ DepartureDateTimeContract.NewDepartureDateTimeInfo.ID_DEPARTURE_DATE_TIME+" TEXT, "+
                    DepartureDateTimeContract.NewDepartureDateTimeInfo.DEPARTURE_DATE+" TEXT, "+ DepartureDateTimeContract.NewDepartureDateTimeInfo.DEPARTURE_TIME+ " TEXT);";*/

    /*private static final int DATABASE_ARR_DATE_TIME_VERSION = 1;
    private static final String CREATE_ARR_DATE_TIME_TABLE =
            "CREATE TABLE "+ ArrivalDateTimeContract.NewArrivalDateTimeInfo.TABLE_ARRIVAL_DATE_TIME+" ("+ ArrivalDateTimeContract.NewArrivalDateTimeInfo.ID_ARRIVAL_DATE_TIME+" TEXT, "+
                    ArrivalDateTimeContract.NewArrivalDateTimeInfo.ARRIVAL_DATE+" TEXT, "+ ArrivalDateTimeContract.NewArrivalDateTimeInfo.ARRIVAL_TIME+" TEXT);";*/

    /*private static final int DATABASE_DEP_LOC_VERSION = 1;
    private static final String CREATE_DEP_LOC_TABLE =
            "CREATE TABLE "+ DepartureLocationContract.NewDepartureLocationInfo.TABLE_DEPARTURE_LOCATION+" ("+ DepartureLocationContract.NewDepartureLocationInfo.ID_DEPARTURE_LOCATION+" TEXT, "+
                    DepartureLocationContract.NewDepartureLocationInfo.STREET+" Text, "+ DepartureLocationContract.NewDepartureLocationInfo.PLZ+" Text, "+
                    DepartureLocationContract.NewDepartureLocationInfo.DEPARTURE_LOCATION+" Text);";*/

    /*private static final int DATABASE_ARR_LOC_VERSION = 1;
    private static final String CREATE_ARR_LOC_TABLE =
            "CREATE TABLE "+ ArrivalLocationContract.NewArrivalLocationInfo.TABLE_ARRIVAL_LOCATION+" ("+ ArrivalLocationContract.NewArrivalLocationInfo.ID_ARRIVAL_LOCATION+" TEXT, "+
                    ArrivalLocationContract.NewArrivalLocationInfo.STREET+" TEXT, "+ ArrivalLocationContract.NewArrivalLocationInfo.PLZ+" TEXT, "+
                    ArrivalLocationContract.NewArrivalLocationInfo.ARRIVAL_LOCATION+" TEXT);";*/

    /*private static final in DATABASE_DISTANCE_VERSION = 1;
    private static final String CREATE_DISTANCE_TABLE =
            "CREATE TABLE "+ DistanceContract.NewDistanceContractInfo.TABLE_DISTANCE+" ("+ DistanceContract.NewDistanceContractInfo.ID_DISTANCE+" TEXT, "+
                    DistanceContract.NewDistanceContractInfo.ID_DEPARTURE_LOCATION+" TEXT, "+ DistanceContract.NewDistanceContractInfo.ID_ARRIVAL_LOCATION+" TEXT);";*/

    private static final String CREATE_LOG_BOOK_TABLE =
            "CREATE TABLE "+LogBookContract.NewLogBookInfo.TABLE_LOG_BOOK+" ("+ LogBookContract.NewLogBookInfo.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    LogBookContract.NewLogBookInfo.DRIVER_NAME+" TEXT, "+ LogBookContract.NewLogBookInfo.DEPARTURE_DATE_TIME+" TEXT, "+
                    LogBookContract.NewLogBookInfo.DEPARTURE_LOCATION+" TEXT, "+ LogBookContract.NewLogBookInfo.ARRIVAL_DATE_TIME+" TEXT, "+
                    LogBookContract.NewLogBookInfo.ARRIVAL_LOCATION+" TEXT);";

    private static final String DATABASE_VERSION_1 = "ALTER TAEBLE "+
            LogBookContract.NewLogBookInfo.TABLE_LOG_BOOK+" ADD COLUMN "+
            LogBookContract.NewLogBookInfo.ARRIVAL_DATE_TIME+ " string;";

    private static final String DATABASE_VERSION_2 = "ALTER TAEBLE "+
            LogBookContract.NewLogBookInfo.TABLE_LOG_BOOK+" ADD COLUMN "+
            LogBookContract.NewLogBookInfo.ARRIVAL_LOCATION+" string;";


    public LogBookDB(Context context){
        super(context, LogBookContract.NewLogBookInfo.TABLE_LOG_BOOK, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Logbook Database created/opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOG_BOOK_TABLE);
        Log.e("DATABASE OPERATIONS", "Table created...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DATABASE OPERATIONS", oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS TABLE_LOG_BOOK");
        onCreate(db);
        if(DATABASE_VERSION < 2){
            db.execSQL(DATABASE_VERSION_1);
        }
        if(DATABASE_VERSION < 3){
            db.execSQL(DATABASE_VERSION_2);
        }
    }

    public void addDriverInformations(String id, String driver, String departure_date_time, String departure_location, String arrival_date_time, String arrival_location, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(LogBookContract.NewLogBookInfo.ID, id);
        contentValues.put(LogBookContract.NewLogBookInfo.DRIVER_NAME, driver);
        contentValues.put(LogBookContract.NewLogBookInfo.DEPARTURE_DATE_TIME, departure_date_time);
        contentValues.put(LogBookContract.NewLogBookInfo.DEPARTURE_LOCATION, departure_location);
        contentValues.put(LogBookContract.NewLogBookInfo.ARRIVAL_DATE_TIME, arrival_date_time);
        contentValues.put(LogBookContract.NewLogBookInfo.ARRIVAL_LOCATION, arrival_location);
        db.insert(LogBookContract.NewLogBookInfo.TABLE_LOG_BOOK, null, contentValues);
        Log.d("DATABASE OPERATIONS", "ID, DRIVER_NAME, DEPARTURE_DATE_TIME, DEPARTURE_LOCATION, inserted...");
    }

    public void addDriverInformationsUpdate(String id, String driver, String departure_date_time, String departure_location, String arrival_date_time, String arrival_location, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(LogBookContract.NewLogBookInfo.ID, id);
        contentValues.put(LogBookContract.NewLogBookInfo.DRIVER_NAME, driver);
        contentValues.put(LogBookContract.NewLogBookInfo.DEPARTURE_DATE_TIME, departure_date_time);
        contentValues.put(LogBookContract.NewLogBookInfo.DEPARTURE_LOCATION, departure_location);
        contentValues.put(LogBookContract.NewLogBookInfo.ARRIVAL_DATE_TIME, arrival_date_time);
        contentValues.put(LogBookContract.NewLogBookInfo.ARRIVAL_LOCATION, arrival_location);
        db.beginTransaction();
        try {
            //db.update(LogBookContract.NewLogBookInfo.TABLE_LOG_BOOK, contentValues, LogBookContract.NewLogBookInfo.ID + " = ?", null);
            //db.update(LogBookContract.NewLogBookInfo.TABLE_LOG_BOOK, contentValues, LogBookContract.NewLogBookInfo.ARRIVAL_DATE_TIME, null);
            //db.insert(LogBookContract.NewLogBookInfo.TABLE_LOG_BOOK, null, contentValues);
            Log.d("DATABASE OPERATIONS", "ARRIVAL_DATE_TIME, ARRIVAL_LOCATION, inserted...");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            Log.d("DATABASE OPERATIONS", "TRANSACTION WAS ENDED");
        }
    }

    public int addDriverInformationsUpdate2(String id, String driver, String departure_date_time, String departure_location, String arrival_date_time, String arrival_location, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(LogBookContract.NewLogBookInfo.ARRIVAL_DATE_TIME, arrival_date_time);
        contentValues.put(LogBookContract.NewLogBookInfo.ARRIVAL_LOCATION, arrival_location);
        return db.update(LogBookContract.NewLogBookInfo.TABLE_LOG_BOOK, contentValues,LogBookContract.NewLogBookInfo.ID + " = ?", null);
    }


    public Cursor getDriverInformations(SQLiteDatabase db){
        Cursor cursor;
        String[] projections = {LogBookContract.NewLogBookInfo.ID, LogBookContract.NewLogBookInfo.DRIVER_NAME,
                LogBookContract.NewLogBookInfo.DEPARTURE_DATE_TIME, LogBookContract.NewLogBookInfo.DEPARTURE_LOCATION,
                LogBookContract.NewLogBookInfo.ARRIVAL_DATE_TIME, LogBookContract.NewLogBookInfo.ARRIVAL_LOCATION};
        //table name, projection, selection, selection args, group rows, filter by row groups, sort order
        cursor = db.query(LogBookContract.NewLogBookInfo.TABLE_LOG_BOOK, projections, null, null, null, null, null);
        return cursor;
    }
}
