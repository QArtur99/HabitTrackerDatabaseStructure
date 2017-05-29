package com.android.habittrackerdatabasestructure;

import android.provider.BaseColumns;

/**
 * Created by ART_F on 2017-05-29.
 */

public class HabitDbContract {

    private HabitDbContract() {
    }

    /* Inner class that defines the table contents */
    public static class Habits implements BaseColumns {

        public static final String TABLE_NAME = "habitTracker";
        public static final String HABIT = "habit";
        public static final String DATE = "date";

        public static final String[] projection = {
                _ID,
                HABIT,
                DATE
        };

        static final String SQL_CREATE_SETTINGS =
                "CREATE TABLE " + TABLE_NAME + " ("
                        + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + HABIT + " TEXT,"
                        + DATE + " INTEGER"
                        + ");";

        static final String SQL_DELETE_SETTINGS =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
