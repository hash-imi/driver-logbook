package com.example.mohammad.bluetoothconnect;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class MapsActivity extends FragmentActivity {
    private Button new_startBtn;
    private Button new_stopBtn;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        createMapView();
        addMarker();
        dateView();
        driverInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    //Initialises the mapView
    private void createMapView(){
        //Catch the null pointer exeption that may
        //be thrown when initilising the map
        try{
            if(null == googleMap){
                    googleMap = ((MapFragment)getFragmentManager().findFragmentById(
                    R.id.mapView)).getMap();

            //If the map is still null after attempted initialisation,
            //show an error to the user
            if(null == googleMap){
                Toast.makeText(getApplicationContext(),
                        "Error creating map", Toast.LENGTH_SHORT).show();
            }
        }
    } catch(NullPointerException exception){
        Log.e("mapApp", exception.toString());
        }
    }

    //Adds a marker to the map
    private void addMarker(){
        //Make sure that the map has been inititialised
        if(null != googleMap){
            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(0, 0))
                            .title("Marker")
                            .draggable(true)
            );
        }
    }

    public void onSearch(View view){
        EditText searchLocation = (EditText)findViewById(R.id.searchLocation);
        String location = searchLocation.getText().toString();
        List<Address> addressList = null;
        if(searchLocation != null || !location.equals("")){
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }

    }

    public void dateView(){
        new_startBtn = (Button)findViewById(R.id.startBtn);
        new_stopBtn = (Button)findViewById(R.id.stopBtn);
        new_stopBtn.setVisibility(View.GONE);
        TextView textView = (TextView)findViewById(R.id.dateView);
        String date = DateFormat.getDateTimeInstance().format(new Date());
        textView.setText(date);
    }

    public void driverInfo(){
        TextView textView = (TextView)findViewById(R.id.driverName);
        textView.setText(getIntent().getExtras().getString("surname"));
        //ListView listView = (ListView)findViewById(R.id.list_view);
        //String driver = DataListActivity.;
        //DataProvider res = (DataProvider) listView.getItemAtPosition(position);
        //Bundle b = new Bundle();
        //int value = b.getInt();
        //Intent intent = getIntent();
        //String surname1 = intent.getStringExtra(res.getSurname());
        // surname1.toString();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setMyLocationEnabled(true);
    }
}

