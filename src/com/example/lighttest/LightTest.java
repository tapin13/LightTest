package com.example.lighttest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;

public class LightTest extends Activity  implements SensorEventListener {
	TextView textView;
	StringBuilder builder = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		textView = new TextView(this);
		setContentView(textView);
		
		SensorManager manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		if(manager.getSensorList(Sensor.TYPE_LIGHT).size() == 0) {
			textView.setText("No accelerometer installed");
		} else {
			Sensor light = manager.getSensorList(Sensor.TYPE_LIGHT).get(0);
			if(!manager.registerListener(this, light, SensorManager.SENSOR_DELAY_FASTEST)) {
				textView.setText("Couldn't register sensor listener");
			}
		}
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		builder.setLength(0);
		builder.append("Light level: ");
		builder.append(event.values[0]);
		builder.append(" SI lux units");
		textView.setText(builder.toString());
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// nothing to do ;)
	}

}


