package pentagram.msfitnessstatic;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.microsoft.band.BandClient;
import com.microsoft.band.BandClientManager;
import com.microsoft.band.BandException;
import com.microsoft.band.BandInfo;
import com.microsoft.band.BandIOException;
import com.microsoft.band.BandPendingResult;
import com.microsoft.band.ConnectionState;
import com.microsoft.band.UserConsent;
import com.microsoft.band.sensors.BandAccelerometerEvent;
import com.microsoft.band.sensors.BandAccelerometerEventListener;
import com.microsoft.band.sensors.BandAltimeterEvent;
import com.microsoft.band.sensors.BandAltimeterEventListener;
import com.microsoft.band.sensors.BandAmbientLightEventListener;
import com.microsoft.band.sensors.BandBarometerEventListener;
import com.microsoft.band.sensors.BandCaloriesEventListener;
import com.microsoft.band.sensors.BandContactEventListener;
import com.microsoft.band.sensors.BandDistanceEventListener;
import com.microsoft.band.sensors.BandGsrEventListener;
import com.microsoft.band.sensors.BandGyroscopeEventListener;
import com.microsoft.band.sensors.BandHeartRateEvent;
import com.microsoft.band.sensors.BandHeartRateEventListener;
import com.microsoft.band.sensors.BandPedometerEventListener;
import com.microsoft.band.sensors.BandRRIntervalEventListener;
import com.microsoft.band.sensors.BandSkinTemperatureEventListener;
import com.microsoft.band.sensors.BandUVEventListener;
import com.microsoft.band.sensors.HeartRateConsentListener;
import com.microsoft.band.sensors.SampleRate;


/**
 * Created by wang_ on 2016/9/17.
 */
public class BandConnectionTask extends AsyncTask<String, String, String> {

    private Activity activity;
    private TextView heartRateTextView;
    private TextView accelerometerTextView;
    private TextView altimeterTextView;
    private TextView ambientLightTextView;
    private TextView barometerTextView;
    private TextView caloriesTextView;
    private TextView contactTextView;
    private TextView distanceTextView;
    private TextView gsrTextView;
    private TextView gyroscopeTextView;
    private TextView pedometerTextView;
    private TextView rrIntervalTextView;
    private TextView skinTemperatureTextView;
    private TextView uvTextView;

    private BandClient bandClient;

    private BandHeartRateEventListener heartRateEventListener;
    private BandAccelerometerEventListener accelerometerEventListener;
    private BandAltimeterEventListener altimeterEventListener;
    private BandAmbientLightEventListener ambientLightEventListener;
    private BandBarometerEventListener barometerEventListener;
    private BandCaloriesEventListener caloriesEventListener;
    private BandContactEventListener contactEventListener;
    private BandDistanceEventListener distanceEventListener;
    private BandGsrEventListener gsrEventListener;
    private BandGyroscopeEventListener gyroscopeEventListener;
    private BandPedometerEventListener pedometerEventListener;
    private BandRRIntervalEventListener rrIntervalEventListener;
    private BandSkinTemperatureEventListener skinTemperatureEventListener;
    private BandUVEventListener uvEventListener;

    public BandConnectionTask(Activity context, TextView heartRateValueTextView,
                              TextView accelerometerValueTextView, TextView altimeterValueTextView, TextView ambientLightValueTextView, TextView barometerValueTextView, TextView caloriesValueTextView,
                              TextView contactValueTextView, TextView distanceValueTextView, TextView gsrValueTextView, TextView gyroscopeValueTextView, TextView pedometerValueTextView,
                              TextView rrIntervalValueTextView, TextView skinTemperatureValueTextView, TextView uvTextValueView) {
        this.activity = context;
        this.heartRateTextView = heartRateValueTextView;
        this.accelerometerTextView = accelerometerValueTextView;
        this.altimeterTextView = altimeterValueTextView;

        heartRateEventListener = new BandHeartRateEventListener() {
            @Override
            public void onBandHeartRateChanged(final BandHeartRateEvent bandHeartRateEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        heartRateTextView.setText(String.format("Heart Rate = %d beats per minute\n" + "Quality = %s\n", bandHeartRateEvent.getHeartRate(), bandHeartRateEvent.getQuality()));
//                        Log.d("heartRate", String.format("Heart Rate = %d beats per minute\n" + "Quality = %s\n", bandHeartRateEvent.getHeartRate(), bandHeartRateEvent.getQuality()));
                    }
                });
            }
        };

        accelerometerEventListener = new BandAccelerometerEventListener() {
            @Override
            public void onBandAccelerometerChanged(final BandAccelerometerEvent bandAccelerometerEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        accelerometerTextView.setText(String.format("AccelerationX : %s\nAccelerationY : %s\nAccelerationZ : %s\n", bandAccelerometerEvent.getAccelerationX(), bandAccelerometerEvent.getAccelerationY(), bandAccelerometerEvent.getAccelerationZ()));

                    }
                });
            }
        };

        altimeterEventListener = new BandAltimeterEventListener() {
            @Override
            public void onBandAltimeterChanged(final BandAltimeterEvent bandAltimeterEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        altimeterTextView.setText(String.format("FlightsAscended : %s\nFlightsDescended : %s\nRate : %s\nSteppingGain : %s\nSteppingLoss : %s\nStepsAscended : %s\nStepsDescended : %s\nTotalGain : %s\nTotalGain : %s\n",
                                 bandAltimeterEvent.getFlightsAscended(), bandAltimeterEvent.getFlightsDescended(), bandAltimeterEvent.getRate(), bandAltimeterEvent.getSteppingGain(), bandAltimeterEvent.getSteppingLoss(),
                                bandAltimeterEvent.getStepsAscended(), bandAltimeterEvent.getStepsDescended(), bandAltimeterEvent.getTotalGain(), bandAltimeterEvent.getTotalLoss()));
                    }
                });
            }
        };
    }

    @Override
    protected String doInBackground(String... params) {
        String fwVersion = null;
        String hwVersion = null;

        BandInfo[] pairedBands = BandClientManager.getInstance().getPairedBands();                          // Get a list of paired Bands
        if(pairedBands == null || pairedBands.length == 0){
            Log.d("pairs", "no pairs");
            return null;
        }
        bandClient = BandClientManager.getInstance().create(activity, pairedBands[0]);      // Create a BandClient object

        // Connect to the Band
        BandPendingResult<ConnectionState>  pendingResult = bandClient.connect();
        try{

            ConnectionState state = pendingResult.await();
            if(state == ConnectionState.CONNECTED){
                Log.d("connection", "connected successfully!");
                if(bandClient.getSensorManager().getCurrentHeartRateConsent() != UserConsent.GRANTED){
                    bandClient.getSensorManager().requestHeartRateConsent(activity, new HeartRateConsentListener() {
                        @Override
                        public void userAccepted(boolean consentGiven) {

                        }
                    });
                }
                bandClient.getSensorManager().registerHeartRateEventListener(heartRateEventListener);
                bandClient.getSensorManager().registerAccelerometerEventListener(accelerometerEventListener, SampleRate.MS16);
                bandClient.getSensorManager().registerAltimeterEventListener(altimeterEventListener);
            }else{
                Log.d("connection", "failed!");
            }
        }catch (InterruptedException ex){
            Log.e("interrupted error", ex.getMessage());
        }catch(BandIOException ex){
            Log.e("BandIO error", ex.getMessage());
        }catch (BandException ex){
            Log.e("band error", ex.getMessage());
        }catch (Exception ex){
            Log.e("other error", ex.getMessage());
        }
        return null;
    }

}
