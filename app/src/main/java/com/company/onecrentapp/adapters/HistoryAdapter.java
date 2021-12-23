package com.company.onecrentapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.company.onecrentapp.R;
import com.company.onecrentapp.models.History;
import com.company.onecrentapp.models.Nomenclature;
import com.company.onecrentapp.models.Station;
import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<History> historyData;
    private int size;

    public HistoryAdapter(Context context)
    {
        this.context = context;
        historyData = History.getInstanceArray();
        size = historyData.size();
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public Object getItem(int i) {
        return historyData.get(size - i - 1);
    }

    @Override
    public long getItemId(int i) {
        return size - i - 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.history_item, null);
        }
        History history = historyData.get(size - i - 1);
        for (Nomenclature n : Nomenclature.getInstanceArray())
        {
            if (history.vendorCode.equals(n.vendorCode))
            {
                ((TextView) view.findViewById(R.id.history_title)).setText(n.vendorCode + " - " + n.name);
            }
        }
        String stationName = "";
        for (Station s : Station.getInstanceArray())
        {
            if (history.stationTake.equals(s.num))
            {
                stationName = s.name;
            }
        }
        TextView tvTaken = view.findViewById(R.id.history_taken);
        TextView tvPut = view.findViewById(R.id.history_put);
        TextView tvTime = view.findViewById(R.id.history_time);
        tvTaken.setText(context.getString(R.string.took) + stationName + " " + history.datetimeTake);
        if (history.stationPut == null || history.datetimePut == null || history.time == null)
        {
            tvPut.setText(context.getString(R.string.put) + context.getString(R.string.in_rent));
            tvTime.setText(context.getString(R.string.rent_time) + context.getString(R.string.in_rent));
        }
        else
        {
            for (Station s : Station.getInstanceArray())
            {
                if (history.stationTake.equals(s.num))
                {
                    stationName = s.name;
                }
            }
            tvPut.setText(context.getString(R.string.put) + stationName + " " + history.datetimePut);
            tvTime.setText(context.getString(R.string.rent_time) + history.time);
        }

        return view;
    }
}
