package com.android.habittrackerdatabasestructure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ART_F on 2017-05-29.
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "HabitTracker.db";
    private static final int DB_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HabitDbContract.Habits.SQL_CREATE_HABITS);
        insertHabit(db, "practicing the saxophone", System.currentTimeMillis());
        insertHabit(db, "taking any medications", System.currentTimeMillis());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(HabitDbContract.Habits.SQL_DELETE_HABITS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void insertHabit(SQLiteDatabase db, String habit, long date) {
        ContentValues mainValues = new ContentValues();
        mainValues.put(HabitDbContract.Habits.HABIT, habit);
        mainValues.put(HabitDbContract.Habits.DATE, date);
        db.insert(HabitDbContract.Habits.TABLE_NAME, null, mainValues);
    }

    public static Cursor loadCursor(Context context) {
        Cursor cursor = null;
        try {
            SQLiteOpenHelper mainDatabase = new HabitDbHelper(context);
            SQLiteDatabase db = mainDatabase.getReadableDatabase();
            cursor = db.query(HabitDbContract.Habits.TABLE_NAME, HabitDbContract.Habits.projection, null, null, null, null, null);

        } catch (SQLiteException e) {
            Toast.makeText(context, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }

}
