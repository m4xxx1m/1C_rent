package com.company.onecrentapp.models;

public class User {

    private static User instance;

    public static User getInstance()
    {
        return instance;
    }

    public String status;
    public int discount;
    public int balance;
    public String lastName;
    public String firstName;
    public String middleName;
    public String sex;
    public String dateOfBirth;
    public String email;
    public String phone;
    public String inRentVendorCode;

    public User(String s, int d, int b, String ln, String fn, String mn, String se, String dob,
                String e, String p, String rvc)
    {
        status = s; discount = d; balance = b; lastName = ln; firstName = fn; middleName = mn;
        sex = se; dateOfBirth = dob; email = e; phone = p; inRentVendorCode = rvc;
        instance = this;
    }

    public void minusBalance(long minutes, int price)
    {
        if (minutes >= 0)
        {
            balance -= minutes * price;
            if (balance < 0)
                balance = 0;
        }
    }
}
