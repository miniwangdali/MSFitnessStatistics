package pentagram.msfitnessstatic;

import android.util.Xml;

import com.microsoft.band.sensors.BandContactState;
import com.microsoft.band.sensors.HeartRateQuality;
import com.microsoft.band.sensors.MotionType;
import com.microsoft.band.sensors.UVIndexLevel;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by wang_ on 2016/9/26.
 */

public class DataValue {
    public static int heartRate = 0;
    public static HeartRateQuality heartQuality = HeartRateQuality.ACQUIRING;

    public static double accelerationX = 0.0;
    public static double accelerationY = 0.0;
    public static double accelerationZ = 0.0;

    public static long flightsAscended = 0l;
    public static long flightsAscendedToday = 0l;
    public static long flightsDescended = 0l;
    public static float altimeterRate = 0.0f;
    public static long steppingGain = 0l;
    public static long steppingLoss = 0l;
    public static long stepsAscended = 0l;
    public static long stepsDescended = 0l;
    public static long totalGain = 0l;
    public static long totalGainToday = 0l;
    public static long totalLoss = 0l;

    public static int brightness = 0;

    public static double airPressure = 0.0;
    public static double temperature = 0.0;

    public static long calories = 0l;
    public static long caloriesToday = 0l;

    public static BandContactState contact = BandContactState.UNKNOWN;

    public static MotionType motionType = MotionType.UNKNOWN;
    public static float pace = 0.0f;
    public static float speed = 0.0f;
    public static long distanceToday = 0l;
    public static long totalDistance = 0l;

    public static int resistance = 0;

    public static double angularVelocityX = 0.0;
    public static double angularVelocityY = 0.0;
    public static double angularVelocityZ = 0.0;

    public static long stepToday = 0l;
    public static long totalStep = 0l;

    public static double rrInterval = 0.0;

    public static double skinTemperature = 0.0;

    public static UVIndexLevel uvIndexValue = UVIndexLevel.NONE;

    public static String getAllData(){
        StringWriter result = new StringWriter();
        XmlSerializer xmlSerializer = Xml.newSerializer();
        try{
            xmlSerializer.setOutput(result);
            xmlSerializer.startDocument("utf-8", true);
            xmlSerializer.startTag(null, "Band");

            xmlSerializer.startTag(null, "HeartRate");
            xmlSerializer.text(String.format("%s", heartRate));
            xmlSerializer.endTag(null, "HeartRate");

            xmlSerializer.startTag(null, "Accelerometer");
            xmlSerializer.startTag(null, "X");
            xmlSerializer.text(String.format("%s", accelerationX));
            xmlSerializer.endTag(null, "X");
            xmlSerializer.startTag(null, "Y");
            xmlSerializer.text(String.format("%s", accelerationY));
            xmlSerializer.endTag(null, "Y");
            xmlSerializer.startTag(null, "Z");
            xmlSerializer.text(String.format("%s", accelerationZ));
            xmlSerializer.endTag(null, "Z");
            xmlSerializer.endTag(null, "Accelerometer");

            xmlSerializer.startTag(null, "Altimeter");
            xmlSerializer.startTag(null, "FlightsAsendedToday");
            xmlSerializer.text(String.format("%s", flightsAscendedToday));
            xmlSerializer.endTag(null, "FlightsAsendedToday");
            xmlSerializer.startTag(null, "FlightsAsended");
            xmlSerializer.text(String.format("%s", flightsAscended));
            xmlSerializer.endTag(null, "FlightsAsended");
            xmlSerializer.startTag(null, "FlightsDescended");
            xmlSerializer.text(String.format("%s", flightsDescended));
            xmlSerializer.endTag(null, "FlightsDescended");
            xmlSerializer.startTag(null, "Rate");
            xmlSerializer.text(String.format("%s", altimeterRate));
            xmlSerializer.endTag(null, "Rate");
            xmlSerializer.startTag(null, "SteppingGain");
            xmlSerializer.text(String.format("%s", steppingGain));
            xmlSerializer.endTag(null, "SteppingGain");
            xmlSerializer.startTag(null, "SteppingLoss");
            xmlSerializer.text(String.format("%s", steppingLoss));
            xmlSerializer.endTag(null, "SteppingLoss");
            xmlSerializer.startTag(null, "StepsAscended");
            xmlSerializer.text(String.format("%s", stepsAscended));
            xmlSerializer.endTag(null, "StepsAscended");
            xmlSerializer.startTag(null, "StepsDescended");
            xmlSerializer.text(String.format("%s", stepsDescended));
            xmlSerializer.endTag(null, "StepsDescended");
            xmlSerializer.startTag(null, "TotalGainToday");
            xmlSerializer.text(String.format("%s", totalGainToday));
            xmlSerializer.endTag(null, "TotalGainToday");
            xmlSerializer.startTag(null, "TotalGain");
            xmlSerializer.text(String.format("%s", totalGain));
            xmlSerializer.endTag(null, "TotalGain");
            xmlSerializer.startTag(null, "TotalLoss");
            xmlSerializer.text(String.format("%s", totalLoss));
            xmlSerializer.endTag(null, "TotalLoss");
            xmlSerializer.endTag(null, "Altimeter");

            xmlSerializer.startTag(null, "AmbientLight");
            xmlSerializer.text(String.format("%s", brightness));
            xmlSerializer.endTag(null, "AmbientLight");

            xmlSerializer.startTag(null, "Barometer");
            xmlSerializer.startTag(null, "AirPressure");
            xmlSerializer.text(String.format("%s", airPressure));
            xmlSerializer.endTag(null, "AirPressure");
            xmlSerializer.startTag(null, "Temperature");
            xmlSerializer.text(String.format("%s", temperature));
            xmlSerializer.endTag(null, "Temperature");
            xmlSerializer.endTag(null, "Barometer");

            xmlSerializer.startTag(null, "Calories");
            xmlSerializer.startTag(null, "Today");
            xmlSerializer.text(String.format("%s", caloriesToday));
            xmlSerializer.endTag(null, "Today");
            xmlSerializer.startTag(null, "Total");
            xmlSerializer.text(String.format("%s", calories));
            xmlSerializer.endTag(null, "Total");
            xmlSerializer.endTag(null, "Calories");

            xmlSerializer.startTag(null, "ContactState");
            xmlSerializer.text(String.format("%s", contact));
            xmlSerializer.endTag(null, "ContactState");

            xmlSerializer.startTag(null, "Distance");
            xmlSerializer.startTag(null, "MotionType");
            xmlSerializer.text(String.format("%s", motionType));
            xmlSerializer.endTag(null, "MotionType");
            xmlSerializer.startTag(null, "Pace");
            xmlSerializer.text(String.format("%s", pace));
            xmlSerializer.endTag(null, "Pace");
            xmlSerializer.startTag(null, "Speed");
            xmlSerializer.text(String.format("%s", speed));
            xmlSerializer.endTag(null, "Speed");
            xmlSerializer.startTag(null, "DistanceToday");
            xmlSerializer.text(String.format("%s", distanceToday));
            xmlSerializer.endTag(null, "DistanceToday");
            xmlSerializer.startTag(null, "TotalDistance");
            xmlSerializer.text(String.format("%s", totalDistance));
            xmlSerializer.endTag(null, "TotalDistance");
            xmlSerializer.endTag(null, "Distance");

            xmlSerializer.startTag(null, "Gsr");
            xmlSerializer.text(String.format("%s", resistance));
            xmlSerializer.endTag(null, "Gsr");

            xmlSerializer.startTag(null, "Pace");
            xmlSerializer.text(String.format("%s", pace));
            xmlSerializer.endTag(null, "Pace");

            xmlSerializer.startTag(null, "Gyroscope");
            xmlSerializer.startTag(null, "X");
            xmlSerializer.text(String.format("%s", angularVelocityX));
            xmlSerializer.endTag(null, "X");
            xmlSerializer.startTag(null, "Y");
            xmlSerializer.text(String.format("%s", angularVelocityY));
            xmlSerializer.endTag(null, "Y");
            xmlSerializer.startTag(null, "Z");
            xmlSerializer.text(String.format("%s", angularVelocityZ));
            xmlSerializer.endTag(null, "Z");
            xmlSerializer.endTag(null, "Gyroscope");

            xmlSerializer.startTag(null, "Pedometer");
            xmlSerializer.startTag(null, "Today");
            xmlSerializer.text(String.format("%s", stepToday));
            xmlSerializer.endTag(null, "Today");
            xmlSerializer.startTag(null, "Total");
            xmlSerializer.text(String.format("%s", totalStep));
            xmlSerializer.endTag(null, "Total");
            xmlSerializer.endTag(null, "Pedometer");

            xmlSerializer.startTag(null, "RRInterval");
            xmlSerializer.text(String.format("%s", rrInterval));
            xmlSerializer.endTag(null, "RRInterval");

            xmlSerializer.startTag(null, "SkinTemperature");
            xmlSerializer.text(String.format("%s", skinTemperature));
            xmlSerializer.endTag(null, "SkinTemperature");

            xmlSerializer.startTag(null, "UVIndexValue");
            xmlSerializer.text(String.format("%s", uvIndexValue));
            xmlSerializer.endTag(null, "UVIndexValue");

            xmlSerializer.endTag(null, "Band");
            xmlSerializer.endDocument();
            result.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
