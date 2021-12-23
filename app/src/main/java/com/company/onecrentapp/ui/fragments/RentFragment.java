package com.company.onecrentapp.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.company.onecrentapp.R;
import com.company.onecrentapp.models.History;
import com.company.onecrentapp.models.Nomenclature;
import com.company.onecrentapp.models.User;
import com.google.android.material.button.MaterialButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RentFragment extends Fragment {

    private View inflatedView;

    public RentFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        MaterialButton button = inflatedView.findViewById(R.id.rent_button);
        if (User.getInstance().inRentVendorCode == null) {
            button.setText(R.string.start_rent);
            button.setOnClickListener(view -> {
                Navigation.findNavController(view).navigate(R.id.action_to_start_rent);
            });
        }
        else {
            TextView tv = inflatedView.findViewById(R.id.good_info);
            if (User.getInstance().inRentVendorCode != null)
            {
                Nomenclature nomenclature = null;
                for (Nomenclature n : Nomenclature.getInstanceArray())
                {
                    if (n.vendorCode.equals(User.getInstance().inRentVendorCode))
                    {
                        nomenclature = n;
                    }
                }
                Date currentDate = Calendar.getInstance().getTime();
                String pattern = "dd/MM/yyyy HH:mm";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                Date takeDate = null;
                try {
                    takeDate = format.parse(History.getLastInstance().datetimeTake);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tv.setText(nomenclature.name + "\nТариф: " + nomenclature.price + "\nВремя в аренде: " +
                        (currentDate.getTime() - takeDate.getTime()) / 60000L + "\n\n\n\n");
            }
            else
            {
                tv.setText("");
            }
            button.setText(R.string.stop_rent);
            button.setOnClickListener(view -> {
                Navigation.findNavController(view).navigate(R.id.action_to_stop_rent);
            });
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (User.getInstance().inRentVendorCode == null)
        {
            ((TextView)inflatedView.findViewById(R.id.good_info)).setText("");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_rent, container, false);
        return inflatedView;
    }
}