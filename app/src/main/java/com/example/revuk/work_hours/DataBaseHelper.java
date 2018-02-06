package com.example.revuk.work_hours;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Revuk on 6/11/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    public DataBaseHelper(Context context) {
        super(context, "2017.db", null, 1);
    }

    /**
     * Called if the database named DATABASE_NAME doesn't exist in order to create it.
     */

    private static final String DATABASE_CREATE = "CREATE TABLE" + " seven "
            + "(" + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + "MONTHS" + " INTEGER," +
            "DAYS" + " INTEGER," + "HOURS" + " TEXT," + "DESCRIBE" + " TEXT);";


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);

        Log.i("DataBaseHelper", "Creating database [2017.db v.1]...");
        //TODO: Create the Database

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DataBaseHelper", "Upgrading database [2017.db v.1] to [2017.db v.1 2]...");



    }
}
