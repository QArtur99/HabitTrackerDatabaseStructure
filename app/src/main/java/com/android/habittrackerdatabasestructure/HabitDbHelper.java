package com.android.habittrackerdatabasestructure;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ART_F on 2017-05-29.
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "HabitTracker.db"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    public HabitDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HabitDbContract.Habits.SQL_CREATE_SETTINGS);
        setSettings(db, "practicing the saxophone", System.currentTimeMillis());
        setSettings(db, "taking any medications", System.currentTimeMillis());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(HabitDbContract.Habits.SQL_DELETE_SETTINGS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void setSettings(SQLiteDatabase db, String habit, long date) {
        ContentValues mainValues = new ContentValues();
        mainValues.put(HabitDbContract.Habits.HABIT, habit);
        mainValues.put(HabitDbContract.Habits.DATE, date);
        db.insert(HabitDbContract.Habits.TABLE_NAME, null, mainValues);
    }

}
