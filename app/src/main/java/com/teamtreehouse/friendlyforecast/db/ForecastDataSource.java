package com.teamtreehouse.friendlyforecast.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.teamtreehouse.friendlyforecast.services.Forecast;

import java.sql.SQLException;

/**
 * Class to act as a an interface on top of the ForecastHelper class and to do all of our operations.
 */
public class ForecastDataSource {
    private SQLiteDatabase mDatabase;
    private ForecastHelper mForecastHelper;
    private Context mContext;

    public ForecastDataSource (Context context) {
        mContext = context;
        mForecastHelper = new ForecastHelper(mContext);
    }

    // open
    public void open() throws SQLException {
        mDatabase = mForecastHelper.getWritableDatabase();
    }

    // close
    public void close() {
        mDatabase.close();
    }

    // select

    // insert
    public void insertForecast(Forecast forecast) {
        mDatabase.beginTransaction();

        try {
            for (Forecast.HourData hour: forecast.hourly.data) {
                ContentValues values = new ContentValues();
                values.put(mForecastHelper.COLUMN_TEMPERATURE, hour.temperature);
                mDatabase.insert(mForecastHelper.TABLE_TEMPERATURES, null, values);
            }
            mDatabase.setTransactionSuccessful();
        }
        finally {
            mDatabase.endTransaction();
        }

    }

    // update

    // delete
}
