package pentagram.msfitnessstatic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView heartRateTextView = (TextView)findViewById(R.id.heart_rate_text);
        TextView accelerometerTextView = (TextView)findViewById(R.id.accelerometer_text);
        TextView altimeterTextView = (TextView)findViewById(R.id.altimeter_text);
        TextView ambientLightTextView = (TextView)findViewById(R.id.ambientLight_text);
        TextView barometerTextView = (TextView)findViewById(R.id.barometer_text);
        TextView caloriesTextView = (TextView)findViewById(R.id.calories_text);
        TextView contactTextView = (TextView)findViewById(R.id.contact_text);
        TextView distanceTextView = (TextView)findViewById(R.id.distance_text);
        TextView gsrTextView = (TextView)findViewById(R.id.gsr_text);
        TextView gyroscopeTextView = (TextView)findViewById(R.id.gyroscope_text);
        TextView pedometerTextView = (TextView)findViewById(R.id.pedometer_text);
        TextView rrIntervalTextView = (TextView)findViewById(R.id.rrinterval_text);
        TextView skinTemperatureTextView = (TextView)findViewById(R.id.skin_temperature_text);
        TextView uvTextView = (TextView)findViewById(R.id.uv_text);

        BandDataCollectionTask myTask = new BandDataCollectionTask(this, heartRateTextView, accelerometerTextView, altimeterTextView, ambientLightTextView, barometerTextView, caloriesTextView, contactTextView,
                distanceTextView, gsrTextView, gyroscopeTextView, pedometerTextView, rrIntervalTextView, skinTemperatureTextView, uvTextView);
        myTask.execute("");
        Intent intent = new Intent(this, BandDataSenderService.class);
        startService(intent);
    }
}
