package com.windry.contactbible.data;

import android.graphics.drawable.Drawable;

public class SideOuterMenuItem {
    String title;
    Drawable backColor;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public Drawable getBackColor() {
        return this.backColor;
    }

    public void setBackColor(Drawable backColor) {
        this.backColor = backColor;
    }
}
