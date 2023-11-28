package com.example.contact_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;

    public DatabaseHelper(Context context) {
        super( context, "people.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE people (name TEXT, dob TEXT, email TEXT, image BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int olbVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS people");
    }

    //store data to database
    public long handleCreate(PersonModal personModal) {
        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //Store Image
        Bitmap imageToStore = personModal.getImage();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] imageInBytes = byteArrayOutputStream.toByteArray();

        contentValues.put("name", personModal.getName());
        contentValues.put("dob", personModal.getDob());
        contentValues.put("email", personModal.getEmail());
        contentValues.put("image", imageInBytes);

        long checkIfQueryRuns = sqliteDatabase.insert("people", null, contentValues);
        if (checkIfQueryRuns != 0) {
            sqliteDatabase.close();
        } else {
            Toast.makeText(context, "Fail to add data", Toast.LENGTH_LONG).show();
        }
        return checkIfQueryRuns;
    }

    //get all data
    public ArrayList<PersonModal> handleRead() {
        ArrayList<PersonModal> listPeople = new ArrayList<>();
        String getAllQuery = "SELECT * FROM people";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(getAllQuery, null);
        while (cursor.moveToNext()) {
            String person_name = cursor.getString(0);
            String person_DOB = cursor.getString(1);
            String person_email = cursor.getString(2);
            byte[] person_image = cursor.getBlob(3);
            Bitmap bitmap = BitmapFactory.decodeByteArray(person_image, 0,person_image.length);
            listPeople.add(new PersonModal(person_name, person_DOB, person_email, bitmap));
        }
        return listPeople;
    }
}


