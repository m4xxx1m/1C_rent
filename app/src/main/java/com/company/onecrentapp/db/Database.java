package com.company.onecrentapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.company.onecrentapp.models.History;
import com.company.onecrentapp.models.Nomenclature;
import com.company.onecrentapp.models.Station;
import com.company.onecrentapp.models.User;

public class Database {
    private static final String DATABASE_NAME = "com.company.onecrentapp.db";
    private static final int DATABASE_VERSION = 1;
    private static SQLiteDatabase mDataBase;

    public static void initialize(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public static void selectAll()
    {
        selectUsers();
        selectNomenclature();
        selectStations();
        selectHistory();
    }

    public static void selectUsers()
    {
        Cursor mCursor = mDataBase.query("users", null, null, null, null, null, null);
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                User user = new User(mCursor.getString(1), mCursor.getInt(2), mCursor.getInt(3),
                        mCursor.getString(4), mCursor.getString(5), mCursor.getString(6),
                        mCursor.getString(7), mCursor.getString(8), mCursor.getString(9),
                        mCursor.getString(10), mCursor.getString(11));
            } while (mCursor.moveToNext());
        }
        mCursor.close();
    }

    public static void selectNomenclature()
    {
        Cursor mCursor = mDataBase.query("nomenclature", null, null, null, null, null, null);
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                Nomenclature nomenclature = new Nomenclature(mCursor.getString(0),
                        mCursor.getString(1), mCursor.getString(2), mCursor.getInt(3),
                        mCursor.getString(4));
            } while (mCursor.moveToNext());
        }
        mCursor.close();
    }

    private static void selectStations()
    {
        Cursor mCursor = mDataBase.query("stations", null, null, null, null, null, null);
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                Station station = new Station(mCursor.getString(0), mCursor.getString(1),
                        mCursor.getString(2), mCursor.getString(3),
                        mCursor.getString(4), mCursor.getString(5));
            } while (mCursor.moveToNext());
        }
        mCursor.close();
    }

    private static void selectHistory()
    {
        Cursor mCursor = mDataBase.query("history", null, null, null, null, null, null);
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                History history = new History(mCursor.getString(1),
                        mCursor.getString(2), mCursor.getString(3), mCursor.getString(4),
                        mCursor.getString(5), (long)mCursor.getInt(6));
            } while (mCursor.moveToNext());
        }
        mCursor.close();
    }

    public static void updateUser()
    {
        User user = User.getInstance();
        ContentValues cv = new ContentValues();
        cv.put("_id", 1); cv.put("status", user.status); cv.put("discount", user.discount);
        cv.put("balance", user.balance); cv.put("last_name", user.lastName);
        cv.put("first_name", user.firstName); cv.put("middle_name", user.middleName);
        cv.put("sex", user.sex); cv.put("date_of_birth", user.dateOfBirth);
        cv.put("email", user.email); cv.put("phone", user.phone);
        cv.put("in_rent_vendor_code", user.inRentVendorCode);
        mDataBase.update("users", cv, "_id = ?",
                new String[] { "1" });
    }

    public static void updateHistory()
    {
        History history = History.getLastInstance();
        ContentValues cv = new ContentValues();
        cv.put("_id", History.getInstanceArraySize() - 1); cv.put("vendor_code", history.vendorCode);
        cv.put("station_take", history.stationTake); cv.put("datetime_take", history.datetimeTake);
        cv.put("station_put", history.stationPut); cv.put("datetime_put", history.datetimePut);
        cv.put("time", history.time);
        mDataBase.update("history", cv, "_id = ?",
                new String[] { String.valueOf(History.getInstanceArraySize() - 1)});
    }

    public static void insertHistory()
    {
        History history = History.getLastInstance();
        ContentValues cv = new ContentValues();
        cv.put("_id", History.getInstanceArraySize() - 1); cv.put("vendor_code", history.vendorCode);
        cv.put("station_take", history.stationTake); cv.put("datetime_take", history.datetimeTake);
        cv.put("station_put", history.stationPut); cv.put("station_put", history.stationPut);
        cv.put("time", history.time);
        mDataBase.insert("history", null, cv);
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Cursor c = db.query("users", null, null, null, null, null, null);
                c.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                String query_users = "CREATE TABLE users (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "status TEXT NOT NULL, " +
                        "discount INTEGER, " +
                        "balance INTEGER, " +
                        "last_name TEXT NOT NULL, " +
                        "first_name TEXT NOT NULL, " +
                        "middle_name TEXT, " +
                        "sex TEXT NOT NULL, " +
                        "date_of_birth TEXT NOT NULL, " +
                        "email TEXT NOT NULL, " +
                        "phone TEXT NOT NULL," +
                        "in_rent_vendor_code TEXT);";
                db.execSQL(query_users);

                String query_users_insert = "INSERT INTO users (status, discount, balance, " +
                        "last_name, first_name, middle_name, sex, date_of_birth, email, phone, " +
                        "in_rent_vendor_code) " +
                        "VALUES (\"VIP\", 10, 150, \"Усачева\", \"Дарья\", \"Игроревна\", \"Женский\", " +
                        "\"16 августа 1995 г,\", \"usad@1c.ru\", \"+7(999) 999-99-99\", null);";
                db.execSQL(query_users_insert);
            }

            String query_nomenclature = "CREATE TABLE IF NOT EXISTS nomenclature (" +
                    "vendor_code TEXT PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "type TEXT NOT NULL, " +
                    "price INTEGER, " +
                    "station TEXT NOT NULL);";
            db.execSQL(query_nomenclature);

            String query_stations = "CREATE TABLE IF NOT EXISTS stations (" +
                    "num TEXT PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "address TEXT NOT NULL, " +
                    "ind TEXT NOT NULL, " +
                    "latitude TEXT NOT NULL, " +
                    "longitude TEXT NOT NULL);";
            db.execSQL(query_stations);

            String query_history = "CREATE TABLE IF NOT EXISTS history (" +
                    "_id INTEGER PRIMARY KEY, " +
                    "vendor_code TEXT NOT NULL, " +
                    "station_take TEXT NOT NULL, " +
                    "datetime_take TEXT NOT NULL, " +
                    "station_put TEXT, " +
                    "datetime_put TEXT, " +
                    "time INTEGER);";
            db.execSQL(query_history);

            String query_nomenclature_insert = "INSERT INTO nomenclature (vendor_code, name, " +
                    "type, price, station) " +
                    "VALUES (\"ба1221\", \"Зайчик №221\", \"Аккумуляторная батарейка типа АА пальчиковая\", " +
                    "1, \"СЗ01\")," +
                    "(\"ак2004\", \"Повербанк  Альфа №400\", \"Внешний аккумулятор 2000 mAh\", " +
                    "5, \"Ю011\")," +
                    "(\"ак5005\", \"Повербанк Кот №501\", \"Внешний аккумулятор 5000 mAh\", " +
                    "6, \"СВ01\")," +
                    "(\"а10003\", \"Повербанк КОТЭ №30\", \"Внешний аккумулятор 1000 mAh\", " +
                    "8, \"СЗ01\");";
            db.execSQL(query_nomenclature_insert);

            String query_stations_insert = "INSERT INTO stations (num, name, address, ind, " +
                    "latitude, longitude) " +
                    "VALUES (\"СЗ01\", \"Авиамоторная 5\", \"Москва, Авиаморная улица, д. 5\", " +
                    "111020, 55.762520, 37.709784), " +
                    "(\"Ю011\", \"2 Автозаводская 1\", \"Москва, 2-й Автозаводской проезд, 1/9\", " +
                    "115280, 55.706959, 37.658536), " +
                    "(\"СВ01\", \"Народного Ополчения 45\", \"Москва, улица Народного Ополчения, 45\", " +
                    "123060, 55.792842, 37.491521), " +
                    "(\"СЗ05\", \"Дмитровское ш. 13\", \"Москва, Дмитровское шоссе, 13к1\", " +
                    "127434, 55.818129, 37.573214);";
            db.execSQL(query_stations_insert);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS users");
            db.execSQL("DROP TABLE IF EXISTS nomenclature");
            db.execSQL("DROP TABLE IF EXISTS stations");
            onCreate(db);
        }
    }
}
