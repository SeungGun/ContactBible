package com.windry.contactbible;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DateDBHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "date";
    public final static String TABLE_NAME = "date_table";
    public DateDBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(ID INTEGER,DATES TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldVersion, int newVersion){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }
    public void insertRecord(int id,String txt){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("INSERT INTO "+TABLE_NAME+"(ID,DATES) VALUES("+id+",'"+txt+"');");
    }
    public void updateRecord(int id,String txt){
        deleteRecord(id);
        insertRecord(id,txt);
    }
    public void deleteRecord(int id){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE ID="+id+";");
    }
    public Cursor readRecord(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DATES FROM "+TABLE_NAME+" WHERE ID = ?;",new String[]{String.valueOf(id)});
        return cursor;
    }

}
