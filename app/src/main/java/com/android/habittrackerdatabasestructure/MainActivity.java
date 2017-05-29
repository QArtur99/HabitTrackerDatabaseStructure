package com.android.habittrackerdatabasestructure;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = HabitDbHelper.loadCursor(getApplicationContext());

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long rowId = cursor.getLong(cursor.getColumnIndexOrThrow(HabitDbContract.Habits._ID));
                String habit = cursor.getString(cursor.getColumnIndexOrThrow(HabitDbContract.Habits.HABIT));
                long dateInMillis = cursor.getLong(cursor.getColumnIndexOrThrow(HabitDbContract.Habits.DATE));
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

    }
}
