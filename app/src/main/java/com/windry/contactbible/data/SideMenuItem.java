package com.windry.contactbible.data;

import android.graphics.drawable.Drawable;

public class SideMenuItem {
    String title;
    Drawable point;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public Drawable getPoint() {
        return this.point;
    }

    public void setPoint(Drawable point) {
        this.point = point;
    }

}
