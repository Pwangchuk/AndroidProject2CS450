package com.example.ehar.accelerometercs450;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Observable;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by plwang13 on 9/14/2016.
 */
public class LocationHandler extends java.util.Observable implements LocationListener {

    MainActivity act;
    private LocationManager locationManager;
    private Location location;
    private double longitude;
    private double latitude;

    String[] perm = new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    public LocationHandler(MainActivity act) {
        this.act = act;
        this.locationManager = (LocationManager) act.getSystemService(Activity.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(act, perm, 0);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0,this);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        longitude = location.getLongitude();
        latitude = location.getLatitude();
        setChanged();
        notifyObservers();
    }

    public double getLongitude() {
        this.longitude = location.getLongitude();
        return longitude;
    }

    public double getLatitude() {
        this.latitude = location.getLatitude();
        return latitude;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(act, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(act, perm, 0);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0,this);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        longitude = location.getLongitude();
        latitude = location.getLatitude();
        setChanged();
        notifyObservers();
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
