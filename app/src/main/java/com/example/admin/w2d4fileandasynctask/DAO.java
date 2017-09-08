package com.example.admin.w2d4fileandasynctask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.EditText;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static android.content.ContentValues.TAG;

class DAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DATA";
    private static final String TABLE_NAME = "PERSON";
    private static final String FIELD_ID = "ID";
    private static final String FIELD_NAME = "NAME";
    private static final String FIELD_LAST_NAME = "LASTNAME";
    private static final String FIELD_AGE = "AGE";
    private static final String FIELD_PHONE = "PHONE";


    private static final int VERSION = 3;


    DAO(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Object[] values = {TABLE_NAME, FIELD_ID, FIELD_NAME, FIELD_LAST_NAME, FIELD_AGE,FIELD_PHONE};
        String CREATE_STATEMENT = "CREATE TABLE " +
                "{0} " +
                "(" +
                "{1} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "{2} TEXT, " +
                "{3} TEXT," +
                "{4} TEXT," +
                "{5} TEXT" +
                ")";
        String query = MessageFormat.format(CREATE_STATEMENT, values);

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long Create(String name, String lastName, String age, String phone) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_LAST_NAME, lastName);
        contentValues.put(FIELD_AGE, age);
        contentValues.put(FIELD_PHONE, phone);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }


    List<String> Read(String id) {
        Object[] values = {TABLE_NAME, FIELD_ID, FIELD_NAME, FIELD_LAST_NAME, FIELD_AGE, FIELD_PHONE,id};

        String SELECT_STATEMENT = "SELECT * FROM {0}";

        if (!id.isEmpty()) {
            String whereStatement = " WHERE {1} = {6}";
            SELECT_STATEMENT = SELECT_STATEMENT + whereStatement;
        }

        String query = MessageFormat.format(SELECT_STATEMENT, values);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        List<String> persons = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToNext()) {
            do {
                Log.d("TAG", "Read: " +
                        "ID: " + cursor.getString(0) +
                        "NAME: " + cursor.getString(1) +
                        "LASTNAME: " + cursor.getString(2) +
                        "AGE: " + cursor.getString(3) +
                        "PHONE: " + cursor.getString(4)
                );

                persons.add(cursor.getString(0) + "|" + cursor.getString(1) + "|" + cursor.getString(2)+ "|" + cursor.getString(3)+ "|" + cursor.getString(4));

            } while (cursor.moveToNext());

        }
        return persons;
    }

    int Update(String id, String name, String lastName, String age, String phone) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_LAST_NAME, lastName);
        contentValues.put(FIELD_AGE, age);
        contentValues.put(FIELD_PHONE, phone);

        Object[] values = {FIELD_ID, id};
        String CREATE_STATEMENT = "{0}={1}";
        String whereClause = MessageFormat.format(CREATE_STATEMENT, values);
        if (!id.isEmpty()) {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            return sqLiteDatabase.update(TABLE_NAME, contentValues, whereClause, null);
        }
    return -1;
    }

    int Drop(String id) {

        Object[] values = {FIELD_ID, id};
        String CREATE_STATEMENT = "{0}={1}";
        String whereClause = MessageFormat.format(CREATE_STATEMENT, values);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        if (!id.isEmpty()) {
            return sqLiteDatabase.delete(TABLE_NAME, whereClause, null);
        }
        return -1;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void ReadAll() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToNext()) {
            do {
                Log.d("TAG", "ReadAll: " +
                        "Name: " + cursor.getString(0) +
                        "Phone: " + cursor.getString(1));
            } while (cursor.moveToNext());

        }
    }
}
