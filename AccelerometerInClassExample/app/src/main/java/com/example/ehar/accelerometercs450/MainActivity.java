package com.example.ehar.accelerometercs450;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{

    private AccelerometerHandler ah = null;
    private LocationHandler lh = null;

    public static final String lon = "LONGITUDE";
    public static final String lat = "LATITUDE";

    TextView z_accel_view = null;
    TextView x_accel_view = null;
    TextView y_accel_view = null;
    TextView longitude_view = null;
    TextView latitude_view = null;
    float[] observe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.z_accel_view = (TextView) findViewById(R.id.z_accel_view);
        this.x_accel_view = (TextView) findViewById(R.id.x_accel_view);
        this.y_accel_view = (TextView) findViewById(R.id.y_accel_view);

        this.longitude_view = (TextView) findViewById(R.id.long_value);
        this.latitude_view = (TextView) findViewById(R.id.lat_value);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.ah = new AccelerometerHandler(this);
        this.ah.addObserver(this);

        this.lh = new LocationHandler(this);
        this.lh.addObserver(this);

        //this.longitude_view.setText(Double.toString(lh.getLongitude()));
        //this.latitude_view.setText(Double.toString(lh.getLatitude()));

        String LO = getPreferences(MODE_PRIVATE).getString(lon, null);
        String LA = getPreferences(MODE_PRIVATE).getString(lat, null);

        longitude_view.setText(LO);
        latitude_view.setText(LA);

    }

    @Override
    protected void onPause() {
        super.onPause();

        getPreferences(MODE_PRIVATE).edit().putString(lon, Double.toString(lh.getLongitude())).apply();
        getPreferences(MODE_PRIVATE).edit().putString(lat, Double.toString(lh.getLatitude())).apply();
    }

    @Override
    public void update(Observable observable, Object o) {

        if (observable instanceof AccelerometerHandler) {
            observe = (float[]) o;
            this.z_accel_view.setText(Float.toString(observe[2]));
            this.x_accel_view.setText(Float.toString(observe[0]));
            this.y_accel_view.setText(Float.toString(observe[1]));
        } else {
            this.longitude_view.setText(Double.toString(lh.getLongitude()));
            this.latitude_view.setText(Double.toString(lh.getLatitude()));
        }
    }
}
