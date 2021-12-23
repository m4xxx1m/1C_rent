package com.company.onecrentapp.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.provider.ContactsContract;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.company.onecrentapp.R;
import com.company.onecrentapp.db.Database;
import com.company.onecrentapp.models.User;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class EditPersonalInfoFragment extends Fragment {

    String phoneCode = "+7";

    public EditPersonalInfoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_edit_personal_info, container,
                false);
        ((EditText)inflatedView.findViewById(R.id.edit_phone_number))
                .addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        Spinner spinnerPhoneCode = inflatedView.findViewById(R.id.spinner_phone_code);
        spinnerPhoneCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                phoneCode = (String) adapterView.getItemAtPosition(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        inflatedView.findViewById(R.id.date_button).setOnClickListener(view -> {
            MaterialDatePicker<Long> datePicker =
                    MaterialDatePicker.Builder.datePicker().build();
            datePicker.show(getActivity().getSupportFragmentManager(), "tag");
            datePicker.addOnPositiveButtonClickListener(selection -> {
                ((TextView)inflatedView.findViewById(R.id.date_of_birth)).setText(
                        datePicker.getHeaderText());
            });
        });
        inflatedView.findViewById(R.id.save_button).setOnClickListener(view -> {
            EditText lastName = inflatedView.findViewById(R.id.edit_last_name);
            EditText firstName = inflatedView.findViewById(R.id.edit_first_name);
            EditText middleName = inflatedView.findViewById(R.id.edit_middle_name);
            EditText email = inflatedView.findViewById(R.id.edit_email);
            EditText phoneNumber = inflatedView.findViewById(R.id.edit_phone_number);
            TextView dateOfBirth = inflatedView.findViewById(R.id.date_of_birth);
            User user = User.getInstance();
            if (!lastName.getText().toString().equals(""))
                user.lastName = lastName.getText().toString();
            if (!firstName.getText().toString().equals(""))
                user.firstName = firstName.getText().toString();
            if (!middleName.getText().toString().equals(""))
                user.middleName = middleName.getText().toString();
            if (!email.getText().toString().equals(""))
                user.email = email.getText().toString();
            if (!phoneNumber.getText().toString().equals(""))
                user.phone = phoneCode + phoneNumber.getText().toString();
            if (!dateOfBirth.getText().toString().equals(""))
                user.dateOfBirth = dateOfBirth.getText().toString();
            Database.updateUser();
            Navigation.findNavController(view).popBackStack();
        });
        return inflatedView;
    }
}
