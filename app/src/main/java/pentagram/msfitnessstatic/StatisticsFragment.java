package pentagram.msfitnessstatic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class StatisticsFragment extends Fragment {

    public StatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);
        TextView heartRateTextView = (TextView)view.findViewById(R.id.heart_rate_text);
        TextView accelerometerTextView = (TextView)view.findViewById(R.id.accelerometer_text);
        TextView altimeterTextView = (TextView)view.findViewById(R.id.altimeter_text);
        TextView ambientLightTextView = (TextView)view.findViewById(R.id.ambientLight_text);
        TextView barometerTextView = (TextView)view.findViewById(R.id.barometer_text);
        TextView caloriesTextView = (TextView)view.findViewById(R.id.calories_text);
        TextView contactTextView = (TextView)view.findViewById(R.id.contact_text);
        TextView distanceTextView = (TextView)view.findViewById(R.id.distance_text);
        TextView gsrTextView = (TextView)view.findViewById(R.id.gsr_text);
        TextView gyroscopeTextView = (TextView)view.findViewById(R.id.gyroscope_text);
        TextView pedometerTextView = (TextView)view.findViewById(R.id.pedometer_text);
        TextView rrIntervalTextView = (TextView)view.findViewById(R.id.rrinterval_text);
        TextView skinTemperatureTextView = (TextView)view.findViewById(R.id.skin_temperature_text);
        TextView uvTextView = (TextView)view.findViewById(R.id.uv_text);

        BandDataCollectionTask myTask = new BandDataCollectionTask(getActivity(), heartRateTextView, accelerometerTextView, altimeterTextView, ambientLightTextView, barometerTextView, caloriesTextView, contactTextView,
                distanceTextView, gsrTextView, gyroscopeTextView, pedometerTextView, rrIntervalTextView, skinTemperatureTextView, uvTextView);
        myTask.execute("");

        Intent intent = new Intent(getActivity(), BandDataSenderService.class);
        getActivity().startService(intent);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
