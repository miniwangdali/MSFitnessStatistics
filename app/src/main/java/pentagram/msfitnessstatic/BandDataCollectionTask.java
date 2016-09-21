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
import com.microsoft.band.sensors.BandAmbientLightEvent;
import com.microsoft.band.sensors.BandAmbientLightEventListener;
import com.microsoft.band.sensors.BandBarometerEvent;
import com.microsoft.band.sensors.BandBarometerEventListener;
import com.microsoft.band.sensors.BandCaloriesEvent;
import com.microsoft.band.sensors.BandCaloriesEventListener;
import com.microsoft.band.sensors.BandContactEvent;
import com.microsoft.band.sensors.BandContactEventListener;
import com.microsoft.band.sensors.BandDistanceEvent;
import com.microsoft.band.sensors.BandDistanceEventListener;
import com.microsoft.band.sensors.BandGsrEvent;
import com.microsoft.band.sensors.BandGsrEventListener;
import com.microsoft.band.sensors.BandGyroscopeEvent;
import com.microsoft.band.sensors.BandGyroscopeEventListener;
import com.microsoft.band.sensors.BandHeartRateEvent;
import com.microsoft.band.sensors.BandHeartRateEventListener;
import com.microsoft.band.sensors.BandPedometerEvent;
import com.microsoft.band.sensors.BandPedometerEventListener;
import com.microsoft.band.sensors.BandRRIntervalEvent;
import com.microsoft.band.sensors.BandRRIntervalEventListener;
import com.microsoft.band.sensors.BandSkinTemperatureEvent;
import com.microsoft.band.sensors.BandSkinTemperatureEventListener;
import com.microsoft.band.sensors.BandUVEvent;
import com.microsoft.band.sensors.BandUVEventListener;
import com.microsoft.band.sensors.GsrSampleRate;
import com.microsoft.band.sensors.HeartRateConsentListener;
import com.microsoft.band.sensors.SampleRate;


/**
 * Created by wang_ on 2016/9/17.
 */
public class BandDataCollectionTask extends AsyncTask<String, String, String> {

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

    public BandDataCollectionTask(Activity context, TextView heartRateValueTextView,
                                  TextView accelerometerValueTextView, TextView altimeterValueTextView, TextView ambientLightValueTextView, TextView barometerValueTextView, TextView caloriesValueTextView,
                                  TextView contactValueTextView, TextView distanceValueTextView, TextView gsrValueTextView, TextView gyroscopeValueTextView, TextView pedometerValueTextView,
                                  TextView rrIntervalValueTextView, TextView skinTemperatureValueTextView, TextView uvValueTextView) {
        this.activity = context;
        this.heartRateTextView = heartRateValueTextView;
        this.accelerometerTextView = accelerometerValueTextView;
        this.altimeterTextView = altimeterValueTextView;
        this.ambientLightTextView = ambientLightValueTextView;
        this.barometerTextView = barometerValueTextView;
        this.caloriesTextView = caloriesValueTextView;
        this.contactTextView = contactValueTextView;
        this.distanceTextView = distanceValueTextView;
        this.gsrTextView = gsrValueTextView;
        this.gyroscopeTextView = gyroscopeValueTextView;
        this.pedometerTextView = pedometerValueTextView;
        this.rrIntervalTextView = rrIntervalValueTextView;
        this.skinTemperatureTextView = skinTemperatureValueTextView;
        this.uvTextView = uvValueTextView;

        heartRateEventListener = new BandHeartRateEventListener() {
            @Override
            public void onBandHeartRateChanged(final BandHeartRateEvent bandHeartRateEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        heartRateTextView.setText(String.format("%d", bandHeartRateEvent.getHeartRate()));
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
                        accelerometerTextView.setText(String.format("X : %.4s\nY : %.4s\nZ : %.4s", bandAccelerometerEvent.getAccelerationX(), bandAccelerometerEvent.getAccelerationY(), bandAccelerometerEvent.getAccelerationZ()));

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
        ambientLightEventListener = new BandAmbientLightEventListener() {
            @Override
            public void onBandAmbientLightChanged(final BandAmbientLightEvent bandAmbientLightEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ambientLightTextView.setText(String.format("%s", bandAmbientLightEvent.getBrightness()));
                    }
                });
            }
        };
        barometerEventListener = new BandBarometerEventListener() {
            @Override
            public void onBandBarometerChanged(final BandBarometerEvent bandBarometerEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        barometerTextView.setText(String.format("Air Pressure : %.7s\nTemperature : %.4s\n", bandBarometerEvent.getAirPressure(), bandBarometerEvent.getTemperature()));
                    }
                });
            }
        };
        caloriesEventListener = new BandCaloriesEventListener() {
            @Override
            public void onBandCaloriesChanged(final BandCaloriesEvent bandCaloriesEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        caloriesTextView.setText(String.format("%s", bandCaloriesEvent.getCalories()));
                    }
                });
            }
        };
        contactEventListener = new BandContactEventListener() {
            @Override
            public void onBandContactChanged(final BandContactEvent bandContactEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contactTextView.setText(String.format("%s", bandContactEvent.getContactState()));
                    }
                });
            }
        };
        distanceEventListener = new BandDistanceEventListener() {
            @Override
            public void onBandDistanceChanged(final BandDistanceEvent bandDistanceEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        distanceTextView.setText(String.format("Current Motion : %s\nPace : %s\nSpeed : %s\nTotal Distance : %s\n",
                                bandDistanceEvent.getMotionType(), bandDistanceEvent.getPace(), bandDistanceEvent.getSpeed(), bandDistanceEvent.getTotalDistance()));
                    }
                });
            }
        };
        gsrEventListener = new BandGsrEventListener() {
            @Override
            public void onBandGsrChanged(final BandGsrEvent bandGsrEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gsrTextView.setText(String.format("%s", bandGsrEvent.getResistance()));
                    }
                });
            }
        };
        gyroscopeEventListener = new BandGyroscopeEventListener() {
            @Override
            public void onBandGyroscopeChanged(final BandGyroscopeEvent bandGyroscopeEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gyroscopeTextView.setText(String.format("X : %.4s\nY : %.4s\nZ : %.4s",
                                bandGyroscopeEvent.getAngularVelocityY(), bandGyroscopeEvent.getAngularVelocityY(), bandGyroscopeEvent.getAngularVelocityZ()));
                    }
                });
            }
        };
        pedometerEventListener = new BandPedometerEventListener() {
            @Override
            public void onBandPedometerChanged(final BandPedometerEvent bandPedometerEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pedometerTextView.setText(String.format("%s", bandPedometerEvent.getTotalSteps()));
                    }
                });
            }
        };
        rrIntervalEventListener = new BandRRIntervalEventListener() {
            @Override
            public void onBandRRIntervalChanged(final BandRRIntervalEvent bandRRIntervalEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rrIntervalTextView.setText(String.format("%.4s", bandRRIntervalEvent.getInterval()));
                    }
                });
            }
        };
        skinTemperatureEventListener = new BandSkinTemperatureEventListener() {
            @Override
            public void onBandSkinTemperatureChanged(final BandSkinTemperatureEvent bandSkinTemperatureEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        skinTemperatureTextView.setText(String.format("%s", bandSkinTemperatureEvent.getTemperature()));                    }
                });
            }
        };
        uvEventListener = new BandUVEventListener() {
            @Override
            public void onBandUVChanged(final BandUVEvent bandUVEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        uvTextView.setText(String.format("Index Level : %s\n", bandUVEvent.getUVIndexLevel()));
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
                }else{
                    Log.d("userconsent", "Granted!");
                    bandClient.getSensorManager().registerHeartRateEventListener(heartRateEventListener);
                    bandClient.getSensorManager().registerAccelerometerEventListener(accelerometerEventListener, SampleRate.MS16);
                    bandClient.getSensorManager().registerAltimeterEventListener(altimeterEventListener);
                    bandClient.getSensorManager().registerAmbientLightEventListener(ambientLightEventListener);
                    bandClient.getSensorManager().registerBarometerEventListener(barometerEventListener);
                    bandClient.getSensorManager().registerCaloriesEventListener(caloriesEventListener);
                    bandClient.getSensorManager().registerContactEventListener(contactEventListener);
                    bandClient.getSensorManager().registerDistanceEventListener(distanceEventListener);
                    bandClient.getSensorManager().registerGsrEventListener(gsrEventListener, GsrSampleRate.MS200);
                    bandClient.getSensorManager().registerGyroscopeEventListener(gyroscopeEventListener, SampleRate.MS16);
                    bandClient.getSensorManager().registerPedometerEventListener(pedometerEventListener);
                    bandClient.getSensorManager().registerRRIntervalEventListener(rrIntervalEventListener);
                    bandClient.getSensorManager().registerSkinTemperatureEventListener(skinTemperatureEventListener);
                    bandClient.getSensorManager().registerUVEventListener(uvEventListener);
                }

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
