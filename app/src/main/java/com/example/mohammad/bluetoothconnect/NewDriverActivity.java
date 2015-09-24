package com.example.mohammad.bluetoothconnect;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewDriverActivity extends Activity {

    EditText DriverPreName, DriverSurName, DriverMobile, DriverEmail, DriverRegistration;
    Context context = this;
    UserDBHelper userDBHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_driver);
        DriverPreName = (EditText) findViewById(R.id.prenameText);
        DriverSurName = (EditText) findViewById(R.id.surnameText);
        DriverMobile = (EditText) findViewById(R.id.mobileText);
        DriverEmail = (EditText) findViewById(R.id.emailText);
        DriverRegistration = (EditText) findViewById(R.id.registrationNumber);
    }

    public void addContact(View view){
        String prename = DriverPreName.getText().toString();
        String surname = DriverSurName.getText().toString();
        String mobile = DriverMobile.getText().toString();
        String email = DriverEmail.getText().toString();
        String register = DriverRegistration.getText().toString();
        userDBHelper = new UserDBHelper(context);
        sqLiteDatabase = userDBHelper.getWritableDatabase();
        userDBHelper.addInformations(prename, surname, mobile, email, register, sqLiteDatabase);
        Toast.makeText(getBaseContext(), "Driver data saved", Toast.LENGTH_LONG).show();
        userDBHelper.close();
        super.finish();
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_driver, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
