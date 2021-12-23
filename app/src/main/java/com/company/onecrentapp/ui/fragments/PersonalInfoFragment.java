package com.company.onecrentapp.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.company.onecrentapp.R;
import com.company.onecrentapp.models.User;
import com.google.android.material.button.MaterialButton;

public class PersonalInfoFragment extends Fragment {

    private View inflatedView;

    public PersonalInfoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        User user = User.getInstance();
        ((TextView)inflatedView.findViewById(R.id.status)).setText(user.status);
        ((TextView)inflatedView.findViewById(R.id.discount)).setText(user.discount + "%");
        ((TextView)inflatedView.findViewById(R.id.balance)).setText(user.balance + " баллов");
        ((TextView)inflatedView.findViewById(R.id.last_name)).setText(user.lastName);
        ((TextView)inflatedView.findViewById(R.id.first_name)).setText(user.firstName);
        ((TextView)inflatedView.findViewById(R.id.middle_name)).setText(user.middleName);
        ((TextView)inflatedView.findViewById(R.id.sex)).setText(user.sex);
        ((TextView)inflatedView.findViewById(R.id.date_of_birth)).setText(user.dateOfBirth);
        ((TextView)inflatedView.findViewById(R.id.email)).setText(user.email);
        ((TextView)inflatedView.findViewById(R.id.phone)).setText(user.phone);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_personal_info, container, false);
        inflatedView.findViewById(R.id.edit_button).setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_to_edit_personal_edit));
        MaterialButton toRentButton = inflatedView.findViewById(R.id.to_rent_button);
        if (User.getInstance().inRentVendorCode == null)
            toRentButton.setText(getString(R.string.start_rent));
        else
            toRentButton.setText(getString(R.string.stop_rent));
        toRentButton.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_from_info_to_rent));
        return inflatedView;
    }
}
