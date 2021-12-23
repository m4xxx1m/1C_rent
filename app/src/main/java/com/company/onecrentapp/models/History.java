package com.company.onecrentapp.models;

import java.util.ArrayList;

public class History {
    private static ArrayList<History> instanceArray = new ArrayList<>();

    public static ArrayList<History> getInstanceArray() {
        return instanceArray;
    }

    public static History getInstance(int index) {
        return instanceArray.get(index);
    }

    public static int getInstanceArraySize() {
        return instanceArray.size();
    }

    public static History getLastInstance() {
        return instanceArray.get(getInstanceArraySize() - 1);
    }

    public String vendorCode;
    public String stationTake;
    public String datetimeTake;
    public String stationPut;
    public String datetimePut;
    public Long time;

    public History(String vc, String st, String dt, String sp, String dp, Long t)
    {
        vendorCode = vc;
        stationTake = st;
        datetimeTake = dt;
        stationPut = sp;
        datetimePut = dp;
        time = t;
        instanceArray.add(this);
    }

    public History(String vc, String st, String dt)
    {
        this(vc, st, dt, null, null, null);
    }
}
