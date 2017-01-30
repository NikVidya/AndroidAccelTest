package com.example.nick.a381week4part2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager mySensorManager;
    Sensor myAccelerometer;
    float[] accel_vals = new float[3];
    EditText accelXEditText, accelYEditText, accelZEditText;
    TextView sensorTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        myAccelerometer = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        accelXEditText = (EditText) findViewById(R.id.editTextX);
        accelYEditText = (EditText) findViewById(R.id.editTextY);
        accelZEditText = (EditText) findViewById(R.id.editTextZ);

        sensorTextView = (TextView) findViewById(R.id.sensorText);
    }
    protected void onResume(){
        super.onResume();
        mySensorManager.registerListener(this, myAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        List<Sensor> mList = mySensorManager.getSensorList(Sensor.TYPE_ALL);
        for (int i = 0; i < mList.size(); i++) {
            sensorTextView.append("\n" + mList.get(i).getName());
        }
    }
    protected void onPause(){
        mySensorManager.unregisterListener(this);
        super.onPause();
    }
    public void onSensorChanged (SensorEvent event) {
        int type = event.sensor.getType();

        if (type == Sensor.TYPE_ACCELEROMETER) {
            accel_vals = event.values;
            accelXEditText.setText("AccelX: " + accel_vals[0]);
            accelYEditText.setText("AccelY: " + accel_vals[1]);
            accelZEditText.setText("AccelZ: " + accel_vals[2]);
        }
    }
    public void onAccuracyChanged(Sensor s, int i) {

    }
}
