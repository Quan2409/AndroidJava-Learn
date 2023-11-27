package com.example.m_hike;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
    //Hike Table Column
    public static final String HIKE_ID = "hike_id";
    public static final String HIKE_NAME = "hike_name";
    public static final String HIKE_LOCATION = "hike_location";
    public static final String HIKE_LENGTH = "hike_length";
    public static final String HIKE_DATE = "hike_date";
    public static final String HIKE_PARKING = "hike_parking";
    public static final String HIKE_DESCRIPTION = "hike_description";
    public static final String HIKE_LEVEL = "hike_level";

    //Observation Table Column
    public static final String OBSERVATION_ID = "observation_id";
    public static final String OBSERVATION_NAME = "observation_name";
    public static final String OBSERVATION_COMMENT = "observation_comment";
    public static final String OBSERVATION_TIME = "observation_time";
    public static final String HIKE_FOREIGN_KEY = "hike_id";
    public static final int VERSION = 1;
    Context context;

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, "hiking.db", null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        //Create Table hiking
        DB.execSQL("CREATE TABLE hiking ("
                + HIKE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HIKE_NAME + " TEXT, "
                + HIKE_LOCATION + " TEXT, "
                + HIKE_LENGTH + " TEXT, "
                + HIKE_DATE + " TEXT, "
                + HIKE_LEVEL + " TEXT, "
                + HIKE_PARKING + " TEXT, "
                + HIKE_DESCRIPTION + " TEXT"
                + ");");

        DB.execSQL("CREATE TABLE observation ("
                + OBSERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + OBSERVATION_NAME + " TEXT, "
                + OBSERVATION_TIME + " TEXT, "
                + OBSERVATION_COMMENT + " TEXT, "
                + HIKE_ID + " INTEGER, "
                + "FOREIGN KEY (" + HIKE_FOREIGN_KEY + ") REFERENCES hiking(" + HIKE_ID + ")"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS hiking");
        DB.setVersion(newVersion);
    }

    //Handle Create New Hike
    public void handleCreateNewHike(HikeModal hikeModal) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(HIKE_NAME, hikeModal.getHikeName());
        content.put(HIKE_LOCATION, hikeModal.getHikeLocation());
        content.put(HIKE_LENGTH,hikeModal.getHikeLength());
        content.put(HIKE_DATE, hikeModal.getHikeDate());
        content.put(HIKE_LEVEL, hikeModal.getHikeLevel());
        content.put(HIKE_PARKING, hikeModal.getHikeParking());
        content.put(HIKE_DESCRIPTION, hikeModal.getHikeDescription());

        long checkIfQueryRuns = DB.insert("hiking", null, content);
        if(checkIfQueryRuns == -VERSION ) {
            showToastMessage("Create Hike Fail");
        } else {
           showToastMessage("Create Hike Success");
        }
    }

    //Handle Read All Hike
    public ArrayList<HikeModal> handleReadAllHike() {
        String getAllQuery = "SELECT * FROM hiking";
        SQLiteDatabase DB = this.getReadableDatabase();
        ArrayList<HikeModal> hikeList = new ArrayList<>();
        Cursor cursor = DB.rawQuery(getAllQuery, null);
        if (cursor.getCount() == 0) {
            showToastMessage("No data to read");
        } else {
            while (cursor.moveToNext()){
                HikeModal hike  = getHikeFromCursor(cursor);
                hikeList.add(hike);
            }
        }
        cursor.close();
        return hikeList;
    }

    //Update Exist Hike
    public void handleUpdateHike(HikeModal hikeModal) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(HIKE_NAME, hikeModal.getHikeName());
        contentValues.put(HIKE_LOCATION, hikeModal.getHikeLocation());
        contentValues.put(HIKE_LENGTH, hikeModal.getHikeLength());
        contentValues.put(HIKE_DATE, hikeModal.getHikeDate());
        contentValues.put(HIKE_LEVEL, hikeModal.getHikeLevel());
        contentValues.put(HIKE_PARKING, hikeModal.getHikeParking());
        contentValues.put(HIKE_DESCRIPTION, hikeModal.getHikeDescription());

        long checkIfQueryRuns = DB.update("hiking", contentValues, "hike_id=?", new String[]{String.valueOf(hikeModal.getHikeID())});
        if(checkIfQueryRuns == -VERSION ) {
           showToastMessage("Update Hike Fail");
        } else {
            showToastMessage("Update Hike Success");
        }
        DB.close();
    }

    public void handleDeleteHike(int id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        boolean isDeleteSuccess = false;

        String deleteHikingQuery = "DELETE FROM hiking WHERE hike_id = ?";
        DB.execSQL(deleteHikingQuery, new String[]{String.valueOf(id)});

        String deleteObservationQuery = "DELETE FROM observation WHERE hike_id = ?";
        DB.execSQL(deleteObservationQuery, new String[]{String.valueOf(id)});

        if (DB.compileStatement(deleteHikingQuery).executeUpdateDelete() > 0 || DB.compileStatement(deleteObservationQuery).executeUpdateDelete() > 0){
            isDeleteSuccess = true;
        }
        DB.close();
        if (isDeleteSuccess) {
            showToastMessage("Delete Fail");
        } else {
            showToastMessage("Delete Success");
        }
    }

    //Create New Observation
    public void handleCreateObservation(ObservationModal observationModal) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(OBSERVATION_NAME, observationModal.getObservationName());
        contentValues.put(OBSERVATION_TIME, observationModal.getObservationTime());
        contentValues.put(OBSERVATION_COMMENT, observationModal.getObservationComment());
        contentValues.put(HIKE_FOREIGN_KEY, observationModal.getHikeIDForeign());
        long checkIfQueryRun = DB.insert("observation", null, contentValues);

        if(checkIfQueryRun == -VERSION ) {
            showToastMessage("Create Observation Fail ");
        } else {
            showToastMessage("Create Observation Success");
        }
        DB.close();
    }

    //Get ID from hiking
    public int getHikeID() {
        SQLiteDatabase DB = this.getReadableDatabase();
        String query = "SELECT hike_id FROM hiking ORDER BY hike_id DESC LIMIT 1";
        Cursor cursor = DB.rawQuery(query, null);
        int hikeID = -1; //Default Value
        if (cursor.moveToFirst()) {
            hikeID = cursor.getInt(cursor.getColumnIndexOrThrow(HIKE_ID));
        }
        cursor.close();
        return hikeID;
    }

    //Read All Observation
    public ArrayList<ObservationModal> handleReadObservation() {
        String selectAllQuery = "SELECT * FROM observation";
        SQLiteDatabase DB = this.getReadableDatabase();
        ArrayList<ObservationModal> listObservation = new ArrayList<>();

        Cursor cursor = DB.rawQuery(selectAllQuery, null);
        if (cursor.getCount() == 0) {
            showToastMessage("No Observation To Read");
        } else {
            while (cursor.moveToNext()) {
                ObservationModal observation = getObservationCursor(cursor);
                listObservation.add(observation);
            }
        }
        cursor.close();
        return listObservation;
    }

    //Update Observation
    public void handleUpdateObservation(ObservationModal observationModal){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(OBSERVATION_NAME, observationModal.getObservationName());
        contentValues.put(OBSERVATION_TIME, observationModal.getObservationTime());
        contentValues.put(OBSERVATION_COMMENT, observationModal.getObservationComment());

        long checkIfQueryRuns = DB.update("observation", contentValues, "observation_id=?", new String[]{String.valueOf(observationModal.getObservationID())});
        if(checkIfQueryRuns == -VERSION ) {
           showToastMessage("Update Observation Fail");
        } else {
           showToastMessage("Update Observation Success");
        }
        DB.close();
    }

    //Delete Observation
    public void handleDeleteObservation(int id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        long checkIfQueryRuns = DB.delete("observation", "observation_id=?", new String[]{String.valueOf(id)});
        if (checkIfQueryRuns == -VERSION) {
            showToastMessage("Delete Observation Fail");
        } else {
            showToastMessage("Delete Observation Success");
        }
    }

    //Function to get Hike from Cursor
    private HikeModal getHikeFromCursor(Cursor cursor) {
        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(HIKE_ID)));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(HIKE_NAME));
        String location = cursor.getString(cursor.getColumnIndexOrThrow(HIKE_LOCATION));
        String length = cursor.getString(cursor.getColumnIndexOrThrow(HIKE_LENGTH));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(HIKE_DATE));
        String level = cursor.getString(cursor.getColumnIndexOrThrow(HIKE_LEVEL));
        String parking = cursor.getString(cursor.getColumnIndexOrThrow(HIKE_PARKING));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(HIKE_DESCRIPTION));
        return new HikeModal(id, name, location, length, date, level, parking, description);
    }

    //Function to get Observation from Cursor
    private ObservationModal getObservationCursor(Cursor cursor) {
        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(OBSERVATION_ID)));
        int hike_id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(HIKE_FOREIGN_KEY)));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.OBSERVATION_NAME));
        String time = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.OBSERVATION_TIME));
        String comment = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.OBSERVATION_COMMENT));
        return new ObservationModal(id, hike_id, name, time, comment);
    }

    //Function to show Toast Message
    private void showToastMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
