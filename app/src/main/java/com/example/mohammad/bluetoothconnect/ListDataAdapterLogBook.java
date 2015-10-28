package com.example.mohammad.bluetoothconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by mohammad on 09.10.15.
 */
public class ListDataAdapterLogBook extends ArrayAdapter {
    List list = new ArrayList();
    public ListDataAdapterLogBook(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler {
        TextView id, driver, departure_date_time, departure_location,
                arrival_date_time, arrival_location;

    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null) {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.logbook_row_layout,parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.id = (TextView)row.findViewById(R.id.text_id);
            layoutHandler.driver = (TextView)row.findViewById(R.id.text_driver);
            layoutHandler.departure_date_time = (TextView)row.findViewById(R.id.text_departure_date_time);
            layoutHandler.departure_location = (TextView)row.findViewById(R.id.text_departure_location);
            layoutHandler.arrival_date_time = (TextView)row.findViewById(R.id.text_arrival_date_time);
            layoutHandler.arrival_location = (TextView)row.findViewById(R.id.text_arrival_location);
            row.setTag(layoutHandler);
        }
        else{
            layoutHandler = (LayoutHandler) row.getTag();
        }
        DataProviderLogBook dataProviderLogBook = (DataProviderLogBook)this.getItem(position);
        layoutHandler.id.setText(dataProviderLogBook.getId());
        layoutHandler.driver.setText(dataProviderLogBook.getDriver());
        layoutHandler.departure_date_time.setText(dataProviderLogBook.getDeparture_date_time());
        layoutHandler.departure_location.setText(dataProviderLogBook.getDeparture_location());
        layoutHandler.arrival_date_time.setText(dataProviderLogBook.getArrival_date_time());
        layoutHandler.arrival_location.setText(dataProviderLogBook.getArrival_location());
        return row;
    }
}
