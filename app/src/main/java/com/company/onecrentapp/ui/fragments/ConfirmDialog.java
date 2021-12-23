package com.company.onecrentapp.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.fragment.NavHostFragment;
import com.company.onecrentapp.R;
import com.company.onecrentapp.db.Database;
import com.company.onecrentapp.models.History;
import com.company.onecrentapp.models.Nomenclature;
import com.company.onecrentapp.models.Station;
import com.company.onecrentapp.models.User;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConfirmDialog extends DialogFragment implements DialogInterface.OnClickListener {

    Nomenclature nomenclature;
    Station station = null;

    public ConfirmDialog(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
        for (Station s : Station.getInstanceArray()) {
            if (s.num.equals(nomenclature.station)) {
                station = s;
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.confirm_rent);
        builder.setMessage(nomenclature.name + "\nТариф:" + nomenclature.price + "\n" +
                station.name);
        builder.setPositiveButton(R.string.yes, this);
        builder.setNegativeButton(R.string.no, this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == DialogInterface.BUTTON_POSITIVE) {
            User.getInstance().inRentVendorCode = nomenclature.vendorCode;
            Database.updateUser();
            Date currentDate = Calendar.getInstance().getTime();
            String pattern = "dd/MM/yyyy HH:mm";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            History history = new History(nomenclature.vendorCode, station.num, format
                    .format(currentDate));
            Database.insertHistory();
            NavHostFragment.findNavController(this).popBackStack();
        }
    }
}
