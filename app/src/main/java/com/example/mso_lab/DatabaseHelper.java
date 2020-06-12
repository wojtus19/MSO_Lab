package com.example.mso_lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                    + KEY_PHONE + " TEXT" + ") ";
            db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            onCreate(db);
    }

    void addRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, record.getTextRecord());
        values.put(KEY_PHONE, record.getPhone_number());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();

    }

    Record getRecord(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {KEY_ID, KEY_NAME, KEY_PHONE}, KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null,null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Record record = new Record(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));

        return record;
    }

    public List<Record> getAllContacts(){
        List<Record> recordList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                Record record = new Record();
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setTextRecord(cursor.getString(1));
                record.setPhone_number(cursor.getString(2));

                recordList.add(record);
            } while (cursor.moveToNext());
        }
        return recordList;

    }

    public  int updateRecord(Record record){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, record.getTextRecord());
        values.put(KEY_PHONE, record.getPhone_number());

        return db.update(TABLE_CONTACTS, values, KEY_ID + "=?", new String[]{String.valueOf(record.getId())});

    }

    public void deleteRecord(Record record){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=?", new String[]{String.valueOf(record.getId())});
        db.close();
    }

    public  int getRecordCount(){
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }

}
