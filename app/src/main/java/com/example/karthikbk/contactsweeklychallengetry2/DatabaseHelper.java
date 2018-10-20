package com.example.karthikbk.contactsweeklychallengetry2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="contacts.DB";
    public static final String TABLE_NAME="CONTACTS";
    public static final String COL2="NAME";
    public static final String COL3="PHONE";
    public static final String COL4="EMAIL";
    public static final String COL5="EXTRA";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS CONTACTS (NAME VARCHAR, PHONE VARCHAR, EMAIL VARCHAR, EXTRA VARCHAR)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP IF TABLE EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public Boolean addData(String name, String phone, String email, String extra){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, phone);
        contentValues.put(COL4, email);
        contentValues.put(COL5, extra);
        if(name.length()==0 || phone.length()==0 || email.length()==0 || extra.length()==0)
        {
            return false;
        }

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public Cursor showData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("SELECT * FROM CONTACTS",null);
        return data;
    }
}
