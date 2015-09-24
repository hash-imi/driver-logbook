package com.example.mohammad.bluetoothconnect;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends Activity {
    protected static final int DISCOVERY_REQUEST = 1;
    /** Called when the activity is first created. */
    private BluetoothAdapter btAdapter;
    private Button new_driverBtn;
    private Button new_viewdriverBtn;
    private Button new_mapsBtn;
    public TextView statusUpdate;
    public Button connect;
    public Button disconnect;
    public Button create_driver;
    public ImageView logo;
    public String toastText = "";
    private BluetoothDevice remoteDevice;

    //Create BoadcastReceiver to receive state changes
    BroadcastReceiver bluetoothState = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //String prevStateExtra = BluetoothAdapter.EXTRA_PREVIOUS_STATE;
            String stateExtra = BluetoothAdapter.EXTRA_STATE;
            int state = intent.getIntExtra(stateExtra, -1);
            //int previousState = intent.getIntExtra(prevStateExtra, -1);
            //String toastText = "";
            switch (state) {
                case (BluetoothAdapter.STATE_TURNING_ON): {
                    toastText = "Bluetooth turning on";
                    Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
                    break;
                }
                case (BluetoothAdapter.STATE_ON): {
                    toastText = "Bluetooth on";
                    Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
                    setupUI();
                    break;
                }
                case (BluetoothAdapter.STATE_TURNING_OFF): {
                    toastText = "Bluetooth turning off";
                    Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
                    break;
                }
                case (BluetoothAdapter.STATE_OFF): {
                    toastText = "Bluetooth off";
                    Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
                    setupUI();
                    break;
                }
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickNewDriver();
        OnClickViewDriver();
        OnClickMaps();
        setupUI();
        //setCreate_driver();
    }//end onCreate

    public void OnClickNewDriver(){
        new_driverBtn = (Button)findViewById(R.id.create_driverBtn);
        new_driverBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.mohammad.bluetoothconnect.NewDriverActivity");
                startActivity(intent);
            }

        });
    }

    public void OnClickViewDriver(){
        new_viewdriverBtn = (Button)findViewById(R.id.view_driverBtn);
        new_viewdriverBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent("com.example.mohammad.bluetoothconnect.DataListActivity");
                startActivity(new Intent(MainActivity.this,DataListActivity.class));
            }
        });
    }

    public void OnClickMaps(){
        new_mapsBtn = (Button)findViewById(R.id.mapsBtn);
        new_mapsBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });
    }


    //connect and disconnect bluetooth
    private void setupUI(){
        //get references
        final TextView statusUpdate = (TextView)findViewById(R.id.textView);
        final Button connect = (Button)findViewById(R.id.connectBtn);
        final Button disconnect = (Button)findViewById(R.id.disconnectBtn);
        final ImageView imageView = (ImageView)findViewById(R.id.imageView);

        //set display view
        disconnect.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter.isEnabled()){
            String address = btAdapter.getAddress();
            String name = btAdapter.getName();
            String statusText = name + " : " + address;
            statusUpdate.setText(statusText);
            disconnect.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            connect.setVisibility(View.GONE);
        }
        else{
            connect.setVisibility(View.VISIBLE);
            statusUpdate.setText("Bluetooth off");
        }

        connect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //String actionStateChanged = BluetoothAdapter.ACTION_STATE_CHANGED;
                //String actionRequestEnable = BluetoothAdapter.ACTION_REQUEST_ENABLE;
                //IntentFilter filter = new IntentFilter(actionStateChanged);
                //registerReceiver(bluetoothState, filter);
                //startActivityForResult(new Intent(actionRequestEnable), 0);

                //register for discovery events
                String scanModeChanged = BluetoothAdapter.ACTION_SCAN_MODE_CHANGED;
                String beDiscoverable = BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE;
                IntentFilter filter = new IntentFilter(scanModeChanged);
                registerReceiver(bluetoothState, filter);
                startActivityForResult(new Intent(beDiscoverable), DISCOVERY_REQUEST);
            }
        }); //end connect OnClickListener

        disconnect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btAdapter.disable();
                connect.setVisibility(View.VISIBLE);
                disconnect.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                statusUpdate.setText("Bluetooth is off");
            }
        }); //end disconnect onClickListener

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == DISCOVERY_REQUEST){
            Toast.makeText(MainActivity.this, "Discovery in Progress", Toast.LENGTH_SHORT).show();
            setupUI();
            findDevices();
        }
    }

    private void findDevices(){
        String lastUsedRemoteDevice = getLastUsedRemoteBTDevice();
        if(lastUsedRemoteDevice != null){
            toastText = "Checking for known paired devices, namely: "+lastUsedRemoteDevice;
            Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
            //see if this device is in a list of currently visible (?), paired devices
            Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
            for(BluetoothDevice pairedDevice : pairedDevices){
                if(pairedDevice.getAddress().equals(lastUsedRemoteDevice)){
                    toastText = "Found device: " + pairedDevice.getName() + "@" + lastUsedRemoteDevice;
                    Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
                    remoteDevice = pairedDevice;
                }
            }
        } //end if
        if(remoteDevice == null){
            toastText = "Starting discovery for remote devices...";
            Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
            //start discovery
            if(btAdapter.startDiscovery()) {
                toastText = "Discovery thread started...Scanning for devices";
                Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
                registerReceiver(discoveryResult, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            }
        }
    }// end find devices

    //Create a BroadcastReceiver to receive device discovery
    BroadcastReceiver discoveryResult = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String remoteDeviceName = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
            //BluetoothDevice remoteDevice1;
            remoteDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            toastText = "Discovered: " + remoteDeviceName;
            Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
            //statusUpdate.setText(statusText);
        }
    };

    private String getLastUsedRemoteBTDevice(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String result = prefs.getString("LAST_REMOTE_DEVICES_ADDRESS", null);
        return result;
    }

    //Create new driver
    private void setCreate_driver(){
        final Button create_driver = (Button)findViewById(R.id.create_driverBtn);

        create_driver.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }
} //end MainActivity

