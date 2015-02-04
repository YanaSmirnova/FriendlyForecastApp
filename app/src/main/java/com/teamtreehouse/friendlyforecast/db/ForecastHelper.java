package com.teamtreehouse.friendlyforecast.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class that will extend a base class, and take care of the create operations.
 */

public class ForecastHelper extends SQLiteOpenHelper{

    public static final String TABLE_TEMPERATURES = "TEMPERATURES";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_TEMPERATURE = "TEMPERATURE";

    private static final String DB_NAME = "temperatures.db";
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE =
            "CREATE TABLE " + TABLE_TEMPERATURES + " (" + COLUMN_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ", " + COLUMN_TEMPERATURE + " REAL)";

    public ForecastHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    // to create the database
        sqLiteDatabase.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
    // to upgrade the database from one version to another
    }
}
