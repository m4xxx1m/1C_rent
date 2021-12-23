package com.company.onecrentapp.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.company.onecrentapp.R;
import com.company.onecrentapp.adapters.HistoryAdapter;

public class HistoryFragment extends Fragment {

    private ListView historyLv;
    private HistoryAdapter adapter;

    public HistoryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_history, container, false);
        historyLv = inflatedView.findViewById(R.id.history_lv);
        adapter = new HistoryAdapter(getContext());
        historyLv.setAdapter(adapter);
        return inflatedView;
    }
}
