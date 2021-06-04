package com.windry.contactbible.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.windry.contactbible.R;
import com.windry.contactbible.data.SideOuterMenuItem;

import java.util.ArrayList;

public class SideOuterMenuAdapter extends BaseAdapter {
    private ArrayList<SideOuterMenuItem> bmItems = new ArrayList<>();
    @Override
    public int getCount() {
        return bmItems.size();
    }
    @Override
    public SideOuterMenuItem getItem(int position) {
        return bmItems.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.side_outermenu_listview, parent, false);
        }
        ImageView img = convertView.findViewById(R.id.left_img);
        TextView main_title = convertView.findViewById(R.id.outer_main);
        SideOuterMenuItem myItem = bmItems.get(position);
        main_title.setText(myItem.getTitle());
        img.setImageDrawable(myItem.getBackColor());
        return convertView;
    }
    public void addItem(Drawable drawable,String title) {
        SideOuterMenuItem items = new SideOuterMenuItem();
        items.setTitle(title);
        items.setBackColor(drawable);
        bmItems.add(items);
    }
    public void setItem(int pos, Drawable drawable,String title) {
        SideOuterMenuItem item = new SideOuterMenuItem();
        item.setBackColor(drawable);
        item.setTitle(title);
        bmItems.set(pos, item);
    }
    public void removeItem(int pos) {
        bmItems.remove(pos);
    }
    public void clearItem() {
        bmItems.clear();

    }
}

