package com.example.mohammad.bluetoothconnect;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class LogBookHistoryActivity extends Activity {
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    LogBookDB userDbHelper;
    Cursor cursor;
    ListDataAdapterLogBook listDataAdapterLogBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_book_history);
        listView = (ListView)findViewById(R.id.list_history_view);
        listDataAdapterLogBook = new ListDataAdapterLogBook(getApplicationContext(), R.layout.logbook_row_layout);
        listView.setAdapter(listDataAdapterLogBook);


        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LogBookHistoryActivity.this, MapsActivity.class);
                DataProviderLogBook res = (DataProviderLogBook) listView.getItemAtPosition(position);
                intent.putExtra("surname", res.getDeparture_location().toString()); //Put your id to your next Intent
                startActivity(intent);
            }
        });*/

        userDbHelper = new LogBookDB(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getDriverInformations(sqLiteDatabase);
        if(cursor.moveToFirst()){
            do{
                String id, driver, departure_date_time, departure_location, arrival_date_time, arrival_location;
                id = cursor.getString(0);
                driver = cursor.getString(1);
                departure_date_time = cursor.getString(2);
                departure_location = cursor.getString(3);
                arrival_date_time = cursor.getString(4);
                arrival_location = cursor.getString(5);
                DataProviderLogBook dataProviderLogBook = new DataProviderLogBook(id, driver, departure_date_time, departure_location, arrival_date_time, arrival_location);
                listDataAdapterLogBook.add(dataProviderLogBook);
            }while(cursor.moveToNext());
        }

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_book_history, menu);
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
