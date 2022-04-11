package com.example.lap7_advance_custom_listtravel_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AddressDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "addressManager";
    private static final String TABLE_ADDRESS = "address";
    private static final String KEY_ID = "id";
    private static final String KEY_ADDRESS = "address";

    // Constructor
    public AddressDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // create table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_ADDRESS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ADDRESS + " TEXT" + ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    // save
    void saveAddress(Address address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ADDRESS, address.get_address());

        // Inserting Row
        db.insert(TABLE_ADDRESS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // load
    public List<Address> getAllAddress() {
        List<Address> list = new ArrayList<Address>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ADDRESS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Address address = new Address();
                address.set_id(Integer.parseInt(cursor.getString(0)));
                address.set_address(cursor.getString(1));

                // Adding people to list
                list.add(address);
            } while (cursor.moveToNext());
        }

        return list;
    }

    // find by id
    Address getAddress(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ADDRESS, new String[] {
                KEY_ID, KEY_ADDRESS
        }, KEY_ID + " =?", new String[] {String.valueOf(id)}, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        Address address = new Address(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        return address;
    }

    // delete
    public void deleteAddress(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_ADDRESS, KEY_ID + " =?", new String[] {String.valueOf(id)});
        db.close();
    }

    // update
    public int updateAddress(Address address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ADDRESS, address.get_address());

        return db.update(TABLE_ADDRESS, values, KEY_ID + " =?", new String[] {
                String.valueOf(address.get_id())
        });
    }

}
