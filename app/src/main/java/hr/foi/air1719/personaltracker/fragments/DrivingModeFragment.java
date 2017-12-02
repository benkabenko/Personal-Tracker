package hr.foi.air1719.personaltracker.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import hr.foi.air1719.core.facade.DatabaseFacade;
import hr.foi.air1719.database.entities.Activity;
import hr.foi.air1719.database.entities.ActivityMode;
import hr.foi.air1719.database.entities.GpsLocation;
import hr.foi.air1719.location.IGPSActivity;
import hr.foi.air1719.location.MyLocation;
import hr.foi.air1719.personaltracker.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by DrazenVuk on 11/30/2017.
 */

public class DrivingModeFragment extends Fragment implements IGPSActivity {

    MyLocation myLocation = null;
    TextView txtSpeed=null;
    TextView txtTotalKm=null;
    Button btnDrivingStart = null;
    Button btnShowTrip = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.driving_mode_fragment, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtSpeed = (TextView) getView().findViewById(R.id.txtSpeedInfo);
        txtTotalKm = (TextView) getView().findViewById(R.id.txtTotalKm);

        btnDrivingStart = (Button) getView().findViewById(R.id.btnDrivingStart);
        btnDrivingStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClick_Start(v);
            }
        });

        btnShowTrip = (Button) getView().findViewById(R.id.btnShowTrip);
        btnShowTrip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClick_ShowTrip(v);
            }
        });

        btnShowTrip.setVisibility(View.INVISIBLE);
    }


    public void onClick_Start(View v) {

        if(myLocation ==null)
        {
            Toast.makeText(this.getActivity(), "Start driving mode", Toast.LENGTH_SHORT).show();
            myLocation = new MyLocation();
            myLocation.LocationStart(this);
            btnDrivingStart.setText("Stop");
            btnShowTrip.setVisibility(View.INVISIBLE);
        }
        else
        {
            Toast.makeText(this.getActivity(), "Stop driving mode", Toast.LENGTH_SHORT).show();
            myLocation.LocationListenerStop();
            myLocation = null;
            btnDrivingStart.setText("Start");
            btnShowTrip.setVisibility(View.VISIBLE);
        }
    }

    public void onClick_ShowTrip(View v) {
        Toast.makeText(this.getActivity(), "TODO", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void locationChanged(Location location) {

        try {
            int speed = (int) ((location.getSpeed() * 3600) / 1000);
            txtSpeed.setText(speed + " km/h");
            txtTotalKm.setText("0 km");
            

            DatabaseFacade dbfacade = new DatabaseFacade(getView().getContext());
            Activity activity = new Activity(ActivityMode.DRIVING);
            dbfacade.saveLocation(new GpsLocation(activity.getActivityId(), location.getLongitude(), location.getLatitude(), location.getAccuracy()));

        }catch (Exception E)
        {
            E.printStackTrace();
        }
    }
}
