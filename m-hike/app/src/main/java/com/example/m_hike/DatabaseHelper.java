package com.example.m_hike;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_LOCATION = "Location";
    public static final String COLUMN_LENGTH = "Length";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_PARKING = "Parking";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_LEVEL = "Level";
    public static final String COLUMN_OBSERVATION_ID = "Observation_ID";
    public static final String COLUMN_OBSERVATION_NAME = "Observation_Name";
    public static final String COLUMN_OBSERVATION_COMMENT = "Observation_Comment";
    public static final String COLUMN_OBSERVATION_TIME = "Observation_Time";
    Context context;

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, "hiking.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE hiking ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_LOCATION + " TEXT, "
                + COLUMN_LENGTH + " TEXT, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_LEVEL + " TEXT, "
                + COLUMN_PARKING + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT"
                + ");");

        DB.execSQL("CREATE TABLE Observation ("
                + COLUMN_OBSERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_OBSERVATION_NAME + " TEXT, "
                + COLUMN_OBSERVATION_TIME + " TEXT, "
                + COLUMN_OBSERVATION_COMMENT + " TEXT, "
                + "hike_ID INTEGER, "
                + "FOREIGN KEY (hike_ID) REFERENCES hiking(" + COLUMN_ID + ")"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS hiking");
    }

    //Handle Add New Hike
    public void handleCreate(HikeModal hikeModal) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(COLUMN_NAME, hikeModal.getName());
        content.put(COLUMN_LOCATION, hikeModal.getLocation());
        content.put(COLUMN_LENGTH,hikeModal.getLength());
        content.put(COLUMN_DATE, hikeModal.getDate());
        content.put(COLUMN_LEVEL, hikeModal.getLevel());
        content.put(COLUMN_PARKING, hikeModal.getParking());
        content.put(COLUMN_DESCRIPTION, hikeModal.getDescription());

        long checkIfQueryRuns = DB.insert("hiking", null, content);
        if(checkIfQueryRuns == -1 ) {
           Toast.makeText(context, "Create Fail", Toast.LENGTH_LONG).show();
        } else {
           Toast.makeText(context, "Create Success", Toast.LENGTH_LONG).show();
        }
    }

    //Retrieve Data From Database
    public ArrayList<HikeModal> handleRead() {

        String getAllQuery = "SELECT * FROM hiking";
        SQLiteDatabase DB = this.getReadableDatabase();
        ArrayList<HikeModal> storeHike = new ArrayList<>();
        Cursor cursor = DB.rawQuery(getAllQuery, null);
        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No Data To Read", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()){
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
                String location = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LOCATION));
                String length = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LENGTH));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DATE));
                String level = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LEVEL));
                String parking = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PARKING));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION));
                storeHike.add(new HikeModal(id, name, location, length, date, level, parking, description));
            }
        }
        cursor.close();
        return storeHike;
    }

    //Update Data
    public void handleUpdate(HikeModal hikeModal) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, hikeModal.getName());
        contentValues.put(COLUMN_LOCATION, hikeModal.getLocation());
        contentValues.put(COLUMN_LENGTH, hikeModal.getLength());
        contentValues.put(COLUMN_DATE, hikeModal.getDate());
        contentValues.put(COLUMN_LEVEL, hikeModal.getLevel());
        contentValues.put(COLUMN_PARKING, hikeModal.getParking());
        contentValues.put(COLUMN_DESCRIPTION, hikeModal.getDescription());

        long checkIfQueryRuns = DB.update("hiking", contentValues, "ID=?", new String[]{String.valueOf(hikeModal.getId())});
        if(checkIfQueryRuns == -1 ) {
            Toast.makeText(context, "Update Fail", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Update Success", Toast.LENGTH_LONG).show();
        }
        DB.close();
    }

    public void handleDelete(int id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        long checkIfQueryRuns = DB.delete("hiking", COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        if(checkIfQueryRuns == -1 ) {
            Toast.makeText(context, "Delete Fail", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Delete Successes", Toast.LENGTH_LONG).show();
        }
        DB.close();
    }

    public HikeModal handleSearch(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        String query = "SELECT * FROM hiking WHERE Name LIKE ?";
        HikeModal hikeModal = null;
        Cursor cursor = DB.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)));
            String hikeName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
            String hikeLocation = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LOCATION));
            String hikeLength = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LENGTH));
            String hikeDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DATE));
            String hikeLevel = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LEVEL));
            String hikeParking = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PARKING));
            String hikeDescription = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION));
            hikeModal = new HikeModal(id, hikeName, hikeLocation, hikeLength, hikeDate, hikeLevel, hikeParking, hikeDescription);
        }
        cursor.close();
        return hikeModal;
    }

    //Create New Observation
    public void handleObservationCreate(ObservationModal observationModal) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_OBSERVATION_NAME, observationModal.getObs_name());
        contentValues.put(COLUMN_OBSERVATION_TIME, observationModal.getObs_time());
        contentValues.put(COLUMN_OBSERVATION_COMMENT, observationModal.getObs_comment());
        long checkIfQueryRun = DB.insert("observation", null, contentValues);

        if(checkIfQueryRun == -1 ) {
            Toast.makeText(context, "Add Observation Fail", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Add Observation Success", Toast.LENGTH_LONG).show();
        }
        DB.close();
    }
}
