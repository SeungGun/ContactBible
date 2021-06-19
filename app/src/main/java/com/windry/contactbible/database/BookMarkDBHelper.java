package com.windry.contactbible.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookMarkDBHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "favorites";
    public final static String TABLE_NAME = "mark";
    public BookMarkDBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(ID INTEGER);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldVersion, int newVersion){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }
    public void insertRecord(int id){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("INSERT INTO "+TABLE_NAME+"(ID) VALUES("+id+");");
    }
    public void updateRecord(int id){
        SQLiteDatabase db = getReadableDatabase();
        deleteRecord(id);
        insertRecord(id);
        // db.execSQL("UPDATE "+TABLE_NAME+" SET SENTENCE ='"+text+"' WHERE ID ="+id+";");
    }
    public void deleteRecord(int id){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE ID="+id+";");
    }
    public Cursor readRecord(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+";",null);
        return cursor;
    }
    public Cursor readIDRecord(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE ID="+id+";",null);
        return cursor;
    }
}
