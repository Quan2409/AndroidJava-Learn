package com.example.demo_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "ustomer.db", null, 1);
    }

    //onCraete: called the first tiem a database is accessed.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + "(" + COLUMN_ID + " INTERGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_ACTIVE_CUSTOMER + " BOOL)";
        db.execSQL(createTableStatement);
    }

    //this is called if the database version changes.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(CustomerModel customerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE, customerModel.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER, customerModel.isActive());
        long insert = db.insert(CUSTOMER_TABLE, null, cv);

        if (insert == -1) {
            return false;
        } else  {
            return true;
        }
    }

    public List<CustomerModel> getAll() {
        List<CustomerModel> returnList = new ArrayList<>();
        //get data from the database
        String queryString = "SELECT * FROM" + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString);
        if(cursor.moveToFirst()) {
            //loop through the result
            do {
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean customerActive = cursor.getInt(3) == 1 ? true:false;
            } while (cursor.moveToFirst());
        } else {

        }
        return returnList;
    }
}
