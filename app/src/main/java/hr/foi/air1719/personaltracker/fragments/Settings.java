package hr.foi.air1719.personaltracker.fragments;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.foi.air1719.personaltracker.Main;
import hr.foi.air1719.personaltracker.R;

/**
 * Created by Timotea on 5.12.2017..
 */

public class Settings extends Fragment {

    public Settings () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);

    }

    public void onResume(){
        super.onResume();

        ((Main) getActivity())
                .setActionBarTitle("Settings");

    }

}
