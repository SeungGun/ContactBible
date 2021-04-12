package com.windry.contactbible;

import android.graphics.drawable.Drawable;

public class BookmarkItem {
    private Drawable icon;
    private String title;
    private String date;
    public Drawable getIcon(){
        return icon;
    }
    public void setIcon(Drawable icon){
        this.icon = icon;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

}
