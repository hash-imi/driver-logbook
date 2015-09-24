package com.example.mohammad.bluetoothconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mohammad on 10.06.2015.
 */
public class UserDBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_DRIVER_TABLE =
            "CREATE TABLE "+ UserContract.NewUserInfo.TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, "+ UserContract.NewUserInfo.USER_PRENAME+" TEXT, "+
                            UserContract.NewUserInfo.USER_SURNAME+" TEXT, "+ UserContract.NewUserInfo.USER_MOBILE+" TEXT, "+ UserContract.NewUserInfo.USER_EMAIL+" TEXT, "+
                            UserContract.NewUserInfo.USER_REGISTER_NUMBER+" TEXT);";

    //Frage... reicht es für ein "Fahrtenbuchtabelle" nur eine weitere Tabelle zu erstellen?????
    //private static final int DATABASE_DEP_DATE_TIME_VERSION = 1;
    private static final String CREATE_DEP_DATE_TIME_TABLE =
            "CREATE TABLE "+ DepartureDateTimeContract.NewDepartureDateTimeInfo.TABLE_DEPARTURE_DATE_TIME+" ("+ DepartureDateTimeContract.NewDepartureDateTimeInfo.ID_DEPARTURE_DATE_TIME+" TEXT, "+
                            DepartureDateTimeContract.NewDepartureDateTimeInfo.DEPARTURE_DATE+" TEXT, "+ DepartureDateTimeContract.NewDepartureDateTimeInfo.DEPARTURE_TIME+ " TEXT);";

    //private static final int DATABASE_ARR_DATE_TIME_VERSION = 1;
    private static final String CREATE_ARR_DATE_TIME_TABLE =
            "CREATE TABLE "+ ArrivalDateTimeContract.NewArrivalDateTimeInfo.TABLE_ARRIVAL_DATE_TIME+" ("+ ArrivalDateTimeContract.NewArrivalDateTimeInfo.ID_ARRIVAL_DATE_TIME+" TEXT, "+
                            ArrivalDateTimeContract.NewArrivalDateTimeInfo.ARRIVAL_DATE+" TEXT, "+ ArrivalDateTimeContract.NewArrivalDateTimeInfo.ARRIVAL_TIME+" TEXT);";

    //private static final int DATABASE_DEP_LOC_VERSION = 1;
    private static final String CREATE_DEP_LOC_TABLE =
            "CREATE TABLE "+ DepartureLocationContract.NewDepartureLocationInfo.TABLE_DEPARTURE_LOCATION+" ("+ DepartureLocationContract.NewDepartureLocationInfo.ID_DEPARTURE_LOCATION+" TEXT, "+
                            DepartureLocationContract.NewDepartureLocationInfo.STREET+" Text, "+ DepartureLocationContract.NewDepartureLocationInfo.PLZ+" Text, "+
                            DepartureLocationContract.NewDepartureLocationInfo.DEPARTURE_LOCATION+" Text);";

    //private static final int DATABASE_ARR_LOC_VERSION = 1;
    private static final String CREATE_ARR_LOC_TABLE =
            "CREATE TABLE "+ ArrivalLocationContract.NewArrivalLocationInfo.TABLE_ARRIVAL_LOCATION+" ("+ ArrivalLocationContract.NewArrivalLocationInfo.ID_ARRIVAL_LOCATION+" TEXT, "+
                            ArrivalLocationContract.NewArrivalLocationInfo.STREET+" TEXT, "+ ArrivalLocationContract.NewArrivalLocationInfo.PLZ+" TEXT, "+
                            ArrivalLocationContract.NewArrivalLocationInfo.ARRIVAL_LOCATION+" TEXT);";

    //private static final in DATABASE_DISTANCE_VERSION = 1;
    private static final String CREATE_DISTANCE_TABLE =
            "CREATE TABLE "+ DistanceContract.NewDistanceContractInfo.TABLE_DISTANCE+" ("+ DistanceContract.NewDistanceContractInfo.ID_DISTANCE+" TEXT, "+
                            DistanceContract.NewDistanceContractInfo.ID_DEPARTURE_LOCATION+" TEXT, "+ DistanceContract.NewDistanceContractInfo.ID_ARRIVAL_LOCATION+" TEXT);";

    //private static final int DATABASE_LOG_BOOK_VERSION = 1;
    private static final String CREATE_LOG_BOOK_TABLE =
            "CREATE TABLE "+LogBookContract.NewLogBookInfo.TABLE_LOG_BOOK+" ("+ LogBookContract.NewLogBookInfo.ID+" TEXT, "+
                            LogBookContract.NewLogBookInfo.DEPARTURE_DATE_TIME+" TEXT, "+ LogBookContract.NewLogBookInfo.ARRIVAL_DATE_TIME+" TEXT, "+
                            LogBookContract.NewLogBookInfo.DEPARTURE_LOCATION+"TEXT, "+ LogBookContract.NewLogBookInfo.ARRIVAL_LOCATION+" TEXT);";

    public UserDBHelper(Context context){
        super(context, UserContract.NewUserInfo.TABLE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Database created/opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DRIVER_TABLE);
        Log.e("DATABASE OPERATION", "Table Driver created");
        db.execSQL(CREATE_DEP_DATE_TIME_TABLE);
        Log.e("DATABASE OPERATION", "Table dep_date_time created");
        db.execSQL(CREATE_ARR_DATE_TIME_TABLE);
        Log.e("DATABASE OPERATION", "Table arr_date_time created");
        db.execSQL(CREATE_DEP_LOC_TABLE);
        Log.e("DATABASE OPERATION", "Table dep_loc created");
        db.execSQL(CREATE_ARR_LOC_TABLE);
        Log.e("DATABASE OPERATION", "Table arr_loc created");
        db.execSQL(CREATE_DISTANCE_TABLE);
        Log.e("DATABASE OPERATION", "Table distance created");
        db.execSQL(CREATE_LOG_BOOK_TABLE);
        Log.e("DATABASE OPERATIONS", "Table created...");
    }

    public void addInformations(String prename, String surname, String mobile, String email, String register, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_PRENAME, prename);
        contentValues.put(UserContract.NewUserInfo.USER_SURNAME, surname);
        contentValues.put(UserContract.NewUserInfo.USER_MOBILE, mobile);
        contentValues.put(UserContract.NewUserInfo.USER_EMAIL, email);
        contentValues.put(UserContract.NewUserInfo.USER_REGISTER_NUMBER, register);
        db.insert(UserContract.NewUserInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row inserted...");
    }

    public Cursor getInformations(SQLiteDatabase db){
        Cursor cursor;
        String[] projections = {UserContract.NewUserInfo.USER_PRENAME,UserContract.NewUserInfo.USER_SURNAME,
                                UserContract.NewUserInfo.USER_MOBILE, UserContract.NewUserInfo.USER_EMAIL,
                                UserContract.NewUserInfo.USER_REGISTER_NUMBER};
        //table name, projection, selection, selection args, group rows, filter by row groups, sort order
        cursor = db.query(UserContract.NewUserInfo.TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
