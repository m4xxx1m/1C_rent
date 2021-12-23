package com.company.onecrentapp.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.company.onecrentapp.R;
import com.company.onecrentapp.adapters.FAQAdapter;

import java.util.ArrayList;
import java.util.List;

public class FAQFragment extends Fragment {

    private ListView faqLv;
    private FAQAdapter adapter;

    public FAQFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_faq, container, false);
        faqLv = inflatedView.findViewById(R.id.faq_lv);
        adapter = new FAQAdapter(getContext());
        faqLv.setAdapter(adapter);
        return inflatedView;
    }
}