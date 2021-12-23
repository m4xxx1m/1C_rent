package com.company.onecrentapp.ui.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.company.onecrentapp.R;
import com.company.onecrentapp.db.Database;
import com.company.onecrentapp.models.Nomenclature;
import com.company.onecrentapp.models.Station;
import com.company.onecrentapp.models.User;

public class StartRentFragment extends Fragment {

    public StartRentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_start_rent, container, false);
        EditText vcEt = inflatedView.findViewById(R.id.vendor_code_et);
        inflatedView.findViewById(R.id.qr_code_button).setOnClickListener(view -> {

        });
        inflatedView.findViewById(R.id.enter_vendor_code_button).setOnClickListener(view -> {
            String vc = vcEt.getText().toString();
            showInfo(vc);
        });
        return inflatedView;
    }

    private void showInfo(String vc)
    {
        Nomenclature nomenclature = null;
        for (Nomenclature n : Nomenclature.getInstanceArray())
        {
            if (n.vendorCode.equals(vc))
            {
                nomenclature = n;
                break;
            }
        }
        if (nomenclature == null) {
            Toast.makeText(getContext(), R.string.vc_not_found, Toast.LENGTH_SHORT).show();
            return;
        }
        ConfirmDialog confirmDialog = new ConfirmDialog(nomenclature);
        confirmDialog.show(getParentFragmentManager(), getString(R.string.confirm_dialog_tag));
    }
}

