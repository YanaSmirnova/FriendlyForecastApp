package com.teamtreehouse.friendlyforecast.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class that will extend a base class, and take care of the create operations.
 */

public class ForecastHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "temperatures.db";
    private static final int DB_VERSION = 1;

    public ForecastHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    // to create the database
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
    // to upgrade the database from one version to another
    }
}
