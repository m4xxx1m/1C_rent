package com.company.onecrentapp.models;

import java.util.ArrayList;

public class Nomenclature {

    private static ArrayList<Nomenclature> instanceArray = new ArrayList<>();

    public static Nomenclature getInstance(int index)
    {
        return instanceArray.get(index);
    }

    public static ArrayList<Nomenclature> getInstanceArray()
    {
        return instanceArray;
    }

    public static int getInstanceArraySize()
    {
        return instanceArray.size();
    }

    public String vendorCode;
    public String name;
    public String type;
    public int price;
    public String station;

    public Nomenclature(String vc, String n, String t, int p, String s)
    {
        vendorCode = vc; name = n; type = t; price = p; station = s;
        instanceArray.add(this);
    }

    @Override
    public String toString()
    {
        return vendorCode + "\n" + name + "\nТариф: " + price;
    }
}
