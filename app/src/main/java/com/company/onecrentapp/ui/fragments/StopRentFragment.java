package com.company.onecrentapp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.company.onecrentapp.R;
import com.company.onecrentapp.ui.activities.StopRentActivity;

public class StopRentFragment extends Fragment {

    public StopRentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_stop_rent, container, false);
        inflatedView.findViewById(R.id.take_photo_button).setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), StopRentActivity.class);
            startActivity(intent);
            Navigation.findNavController(view).popBackStack();
        });
        inflatedView.findViewById(R.id.cancel_rent_button).setOnClickListener(view ->
                Navigation.findNavController(view).popBackStack());
        return inflatedView;
    }
}
