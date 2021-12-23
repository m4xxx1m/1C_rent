package com.company.onecrentapp.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.company.onecrentapp.R;
import com.company.onecrentapp.adapters.StationsAdapter;

public class StationsFragment extends Fragment {

    private ExpandableListView expandableListView;
    private StationsAdapter expandableListAdapter;

    public StationsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_stations, container, false);
        expandableListView = (ExpandableListView) inflatedView.findViewById(R.id.station_elv);
        expandableListAdapter = new StationsAdapter(getContext());
        expandableListView.setAdapter(expandableListAdapter);
        return inflatedView;
    }
}