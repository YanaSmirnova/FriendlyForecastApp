package com.teamtreehouse.friendlyforecast.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    // select
    public Cursor selectAllTemperatures() {
        Cursor cursor = mDatabase.query(
                ForecastHelper.TABLE_TEMPERATURES, // table
                new String[] {ForecastHelper.COLUMN_TEMPERATURE}, //column names
                null, // where clause
                null, // where params
                null, // group by
                null, // having
                null //ordered by
        );

        return cursor;
    }

    public Cursor selectTempsGreaterThan(String minTemp) {
        String whereClause = ForecastHelper.COLUMN_TEMPERATURE + " > ?";
        Cursor cursor = mDatabase.query(
                ForecastHelper.TABLE_TEMPERATURES, // table
                new String[] {ForecastHelper.COLUMN_TEMPERATURE}, //column names
                whereClause, // where clause
                new String[] { minTemp }, // where params
                null, // group by
                null, // having
                null //ordered by
        );

        return cursor;
    }

    // update
    public int updateTemperature(double newTemp) {
        ContentValues values = new ContentValues();
        values.put(ForecastHelper.COLUMN_TEMPERATURE, newTemp);
        int rowsUpdated = mDatabase.update(
                ForecastHelper.TABLE_TEMPERATURES, // table
                values, // content values
                null, // where clause
                null// where params
        );

        return rowsUpdated;
    }

    // delete
    public void deleteAll() {
        mDatabase.delete(
                ForecastHelper.TABLE_TEMPERATURES, //
                null, // where clause
                null// where params
        );
    }
}
