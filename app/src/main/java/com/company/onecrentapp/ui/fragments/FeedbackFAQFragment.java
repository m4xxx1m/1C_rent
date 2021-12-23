package com.company.onecrentapp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.company.onecrentapp.R;
import com.company.onecrentapp.email.SendEmail;

public class FeedbackFAQFragment extends Fragment {

    public FeedbackFAQFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_feedback_faq, container, false);
        inflatedView.findViewById(R.id.button_to_feedback).setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SendEmail.class);
            startActivity(intent);
        });
        inflatedView.findViewById(R.id.button_to_faq).setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_to_faq));
        return inflatedView;
    }
}