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


    public UserDBHelper(Context context){
        super(context, UserContract.NewUserInfo.TABLE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Database created/opened...");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DRIVER_TABLE);
        Log.e("DATABASE OPERATION", "Table Driver created");
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
