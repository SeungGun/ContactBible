package com.windry.contactbible.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class IndexDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "page";
    public static final String TABLE_NAME = "SEUNGGUN";
    public IndexDBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
        init();

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS SEUNGGUN (CURRENT INTEGER);");
    }

    public void init(){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("CURRENT",1);
        db.insert(TABLE_NAME,null,values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldVersion, int newVersion){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SEUNGGUN");
    }
    public void insertRecord(int cur_index){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("CURRENT",cur_index);
        db.insert(TABLE_NAME,null,values);
    }
    public void updateRecord(int cur_index){
        SQLiteDatabase db = getReadableDatabase();
        //db.execSQL("UPDATE SEUNGGUN SET CURRENT ="+cur_index+";");
        deleteRecord();
        insertRecord(cur_index);
    }
    public void deleteRecord(){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+";");
    }

    public Cursor readRecord(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {"CURRENT"};
        Cursor cursor = db.query(TABLE_NAME,projection,null,null,null,null,null);
        return cursor;
    }
}
