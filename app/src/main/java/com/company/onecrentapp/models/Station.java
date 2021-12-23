package com.company.onecrentapp.models;

import java.util.ArrayList;

public class Station {

    private static ArrayList<Station> instanceArray = new ArrayList<>();

    public static ArrayList<Station> getInstanceArray()
    {
        return instanceArray;
    }

    public static Station getInstance(int index)
    {
        return instanceArray.get(index);
    }

    public static int getInstanceArraySize()
    {
        return instanceArray.size();
    }

    public String num;
    public String name;
    public String address;
    public String index;
    public String latitude;
    public String longitude;

    public Station(String n, String na, String a, String i, String la, String lo)
    {
        num = n; name = na; address = a; index = i; latitude = la; longitude = lo;
        instanceArray.add(this);
    }

    @Override
    public String toString()
    {
        return num + "\n" + name + "\n" + address + "\n" +index + "\n" + latitude + ", " + longitude;
    }
}
