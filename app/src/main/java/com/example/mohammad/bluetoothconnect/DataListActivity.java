package com.example.mohammad.bluetoothconnect;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class DataListActivity extends Activity {
    private TextView new_mapsviewBtn;

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDBHelper userDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        listView = (ListView)findViewById(R.id.list_view);
        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.data_row_layout);
        listView.setAdapter(listDataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //startActivity(new Intent(DataListActivity.this, MapsActivity.class));
                Intent intent = new Intent(DataListActivity.this, MapsActivity.class);
                DataProvider res = (DataProvider) listView.getItemAtPosition(position);
                //Bundle b = new Bundle();
                //b.putInt(res.getSurname(), 1); //Your id
                intent.putExtra("surname", res.getSurname().toString()); //Put your id to your next Intent
                startActivity(intent);
                //finish();
                //Toast.makeText(DataListActivity.this, res.getSurname(), Toast.LENGTH_SHORT).show();
            }
        });

        userDbHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getInformations(sqLiteDatabase);
        if(cursor.moveToFirst()){
            do{
                String prename, surname, email, mobile, register;
                prename = cursor.getString(0);
                surname = cursor.getString(1);
                email = cursor.getString(2);
                mobile = cursor.getString(3);
                register = cursor.getString(4);
                DataProvider dataProvider = new DataProvider(prename, surname, email, mobile, register);
                listDataAdapter.add(dataProvider);
            }while(cursor.moveToNext());
        }
    }

    /*public void onListDriverClick(ListView parent, View v, int position, long id){
        Toast.makeText(this, "You have selected " + databaseList()[position], Toast.LENGTH_LONG).show();
    }*/


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_list, menu);
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
