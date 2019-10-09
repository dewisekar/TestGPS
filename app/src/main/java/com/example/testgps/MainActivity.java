package com.example.testgps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LocationManager lm;
    private LocationListener ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ll = new lokasiListener();
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED){
            return;
        }

        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 50, 200, ll);

    }

    private class lokasiListener implements LocationListener{
        private TextView txtLat, txtLong;
        @Override
        public void onLocationChanged(Location location) {
            txtLat = (TextView) findViewById(R.id.text_lat);
            txtLong = (TextView) findViewById(R.id.text_long);

            txtLat.setText(String.valueOf(location.getLatitude()));
            txtLong.setText(String.valueOf(location.getLongitude()));
            Toast.makeText(getBaseContext(), "GPS Capture:", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
}


