package com.company.onecrentapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.company.onecrentapp.R;
import com.company.onecrentapp.models.Nomenclature;
import com.company.onecrentapp.models.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class StationsAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final HashMap<Integer, ArrayList<Integer>> data = new HashMap<>();
    ArrayList<Station> stations;
    ArrayList<Nomenclature> nomenclatures;

    public StationsAdapter(Context context)
    {
        this.context = context;
        stations = Station.getInstanceArray();
        nomenclatures = Nomenclature.getInstanceArray();
        for (int j = 0; j < stations.size(); ++j)
        {
            data.put(j, new ArrayList<>());
            for (int i = 0; i < nomenclatures.size(); ++i)
            {
                if (stations.get(j).num.equals(nomenclatures.get(i).station))
                {
                    Objects.requireNonNull(data.get(j)).add(i);
                }
            }
        }
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return data.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return stations.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return nomenclatures.get(data.get(i).get(i1));
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String listTitle = ((Station)getGroup(i)).toString();
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.station_group, null);
        }
        TextView listTitleTextView = (TextView) view
                .findViewById(R.id.st_lg);
        listTitleTextView.setText(listTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String expandedListText = ((Nomenclature)getChild(i, i1)).toString();
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.station_item, null);
        }
        TextView expandedListTextView = (TextView) view
                .findViewById(R.id.st_li);
        expandedListTextView.setText(expandedListText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
