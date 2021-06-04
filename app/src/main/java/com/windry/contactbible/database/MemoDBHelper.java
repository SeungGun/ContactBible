package com.windry.contactbible.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemoDBHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "memo";
    public final static String TABLE_NAME = "memo_table";
    public MemoDBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(ID INTEGER, SENTENCE TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldVersion, int newVersion){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }
    public void insertRecord(int id, String text){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("INSERT INTO "+TABLE_NAME+"(ID,SENTENCE) VALUES("+id+",'"+text+"');");
    }
    public void updateRecord(int id, String text){
        SQLiteDatabase db = getReadableDatabase();
        deleteRecord(id);
        insertRecord(id,text);
    }
    public void deleteRecord(int id){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE ID="+id+";");
    }
    public Cursor readRecord(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SENTENCE FROM "+TABLE_NAME+" WHERE ID = ?;",new String[]{String.valueOf(id)});
        return cursor;
    }
}
