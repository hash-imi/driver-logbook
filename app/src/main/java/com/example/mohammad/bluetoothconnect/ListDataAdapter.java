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
 * Created by mohammad on 16.07.15.
 */
public class ListDataAdapter extends ArrayAdapter{
    List list = new ArrayList();
    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler{
        TextView prename, surname, email, mobile, register;
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
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.data_row_layout, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.prename = (TextView)row.findViewById(R.id.text_driver_prename);
            layoutHandler.surname = (TextView)row.findViewById(R.id.text_driver_surname);
            layoutHandler.email = (TextView)row.findViewById(R.id.text_driver_email);
            layoutHandler.mobile = (TextView)row.findViewById(R.id.text_driver_mobilenumber);
            layoutHandler.register = (TextView)row.findViewById(R.id.text_driver_registration_number);
            row.setTag(layoutHandler);
        }
        else{
            layoutHandler = (LayoutHandler) row.getTag();
        }

        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.prename.setText(dataProvider.getPrename());
        layoutHandler.surname.setText(dataProvider.getSurname());
        layoutHandler.email.setText(dataProvider.getEmail());
        layoutHandler.mobile.setText(dataProvider.getMobile());
        layoutHandler.register.setText(dataProvider.getRegister());
        return row;
    }
}
