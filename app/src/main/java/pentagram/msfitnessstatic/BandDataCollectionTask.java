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
import com.microsoft.band.InvalidBandVersionException;
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
                        DataValue.heartRate = bandHeartRateEvent.getHeartRate();
                        DataValue.heartQuality = bandHeartRateEvent.getQuality();
                        heartRateTextView.setText(String.format("%s", DataValue.heartRate));
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
                        DataValue.accelerationX = bandAccelerometerEvent.getAccelerationX();
                        DataValue.accelerationY = bandAccelerometerEvent.getAccelerationY();
                        DataValue.accelerationZ = bandAccelerometerEvent.getAccelerationZ();
                        accelerometerTextView.setText(String.format("X : %.4s\nY : %.4s\nZ : %.4s",
                                DataValue.accelerationX, DataValue.accelerationY, DataValue.accelerationZ));

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
                        DataValue.flightsAscended = bandAltimeterEvent.getFlightsAscended();
                        DataValue.flightsDescended = bandAltimeterEvent.getFlightsDescended();
                        DataValue.altimeterRate = bandAltimeterEvent.getRate();
                        DataValue.steppingGain = bandAltimeterEvent.getSteppingGain();
                        DataValue.steppingLoss = bandAltimeterEvent.getSteppingLoss();
                        DataValue.stepsAscended = bandAltimeterEvent.getStepsAscended();
                        DataValue.stepsDescended = bandAltimeterEvent.getStepsDescended();
                        try{
                            DataValue.flightsAscendedToday = bandAltimeterEvent.getFlightsAscendedToday();
                            DataValue.totalGainToday = bandAltimeterEvent.getTotalGainToday();
                        } catch (InvalidBandVersionException e) {
                            e.printStackTrace();
                        }
                        DataValue.totalGain = bandAltimeterEvent.getTotalGain();
                        DataValue.totalLoss = bandAltimeterEvent.getTotalLoss();
                        altimeterTextView.setText(String.format("FlightsAscendedToday : %s\nFlightsDescended : %s\nRate : %s\nSteppingGain : %s\nSteppingLoss : %s\nStepsAscended : %s\nStepsDescended : %s\nTotalGainToday : %s\nTotalLoss : %s",
                                DataValue.flightsAscendedToday, DataValue.flightsDescended, DataValue.altimeterRate, DataValue.steppingGain,
                                DataValue.steppingLoss, DataValue.stepsAscended, DataValue.stepsDescended, DataValue.totalGainToday, DataValue.totalLoss));
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
                        DataValue.brightness = bandAmbientLightEvent.getBrightness();
                        ambientLightTextView.setText(String.format("%s", DataValue.brightness));
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
                        DataValue.airPressure = bandBarometerEvent.getAirPressure();
                        DataValue.temperature = bandBarometerEvent.getTemperature();
                        barometerTextView.setText(String.format("Air Pressure : %.7s\nTemperature : %.4s",
                                DataValue.airPressure, DataValue.temperature));
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
                        DataValue.calories = bandCaloriesEvent.getCalories();
                        try {
                            DataValue.caloriesToday = bandCaloriesEvent.getCaloriesToday();
                        } catch (InvalidBandVersionException e) {
                            e.printStackTrace();
                        }
                        caloriesTextView.setText(String.format("%s", DataValue.caloriesToday));
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
                        DataValue.contact = bandContactEvent.getContactState();
                        contactTextView.setText(String.format("%s", DataValue.contact));
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

                        DataValue.motionType = bandDistanceEvent.getMotionType();
                        DataValue.pace = bandDistanceEvent.getPace();
                        DataValue.speed = bandDistanceEvent.getSpeed();
                        try{
                            DataValue.distanceToday = bandDistanceEvent.getDistanceToday();
                        } catch (InvalidBandVersionException e) {
                            e.printStackTrace();
                        }
                        DataValue.totalDistance = bandDistanceEvent.getTotalDistance();
                        distanceTextView.setText(String.format("Current Motion : %s\nPace : %s\nSpeed : %s\nToday\'s Distance : %s\nTotal Distance : %s",
                                DataValue.motionType, DataValue.pace, DataValue.speed, DataValue.distanceToday, DataValue.totalDistance));
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
                        DataValue.resistance = bandGsrEvent.getResistance();
                        gsrTextView.setText(String.format("%s", DataValue.resistance));
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
                        DataValue.angularVelocityX = bandGyroscopeEvent.getAngularVelocityX();
                        DataValue.angularVelocityY = bandGyroscopeEvent.getAngularVelocityY();
                        DataValue.angularVelocityZ = bandGyroscopeEvent.getAngularVelocityZ();
                        gyroscopeTextView.setText(String.format("X : %.4s\nY : %.4s\nZ : %.4s",
                                DataValue.angularVelocityX, DataValue.angularVelocityY, DataValue.angularVelocityZ));
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
                        try{
                            DataValue.stepToday = bandPedometerEvent.getStepsToday();
                        } catch (InvalidBandVersionException e) {
                            e.printStackTrace();
                        }
                        DataValue.totalStep = bandPedometerEvent.getTotalSteps();

                        pedometerTextView.setText(String.format("%s", DataValue.stepToday));
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
                        DataValue.rrInterval = bandRRIntervalEvent.getInterval();
                        rrIntervalTextView.setText(String.format("%.4s", DataValue.rrInterval));
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
                        DataValue.skinTemperature = bandSkinTemperatureEvent.getTemperature();
                        skinTemperatureTextView.setText(String.format("%.5s", DataValue.skinTemperature));
                    }
                });
            }
        };
        uvEventListener = new BandUVEventListener() {
            @Override
            public void onBandUVChanged(final BandUVEvent bandUVEvent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DataValue.uvIndexValue = bandUVEvent.getUVIndexLevel();
                        uvTextView.setText(String.format("Index Level : %s\n", DataValue.uvIndexValue));
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
                    fwVersion = bandClient.getFirmwareVersion().await();
                    hwVersion = bandClient.getHardwareVersion().await();
                    Log.d("version info", fwVersion + "\n" + hwVersion);
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
