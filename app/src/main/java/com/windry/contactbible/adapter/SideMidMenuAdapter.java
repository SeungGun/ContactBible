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
import com.windry.contactbible.data.SideMidMenuItem;

import java.util.ArrayList;

public class SideMidMenuAdapter extends BaseAdapter {
    private ArrayList<SideMidMenuItem> bmItems = new ArrayList<>();

    @Override
    public int getCount() {
        return bmItems.size();
    }

    @Override
    public SideMidMenuItem getItem(int position) {
        return bmItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.side_midmenu_listview_custom, parent, false);

            ImageView img = convertView.findViewById(R.id.color_back);
            TextView main_title = convertView.findViewById(R.id.mid_main);

            viewHolder.img = img;
            viewHolder.main_title = main_title;

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        SideMidMenuItem myItem = bmItems.get(position);
        viewHolder.img.setImageDrawable(myItem.getBackColor());
        viewHolder.main_title.setText(myItem.getTitle());

        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView main_title;
    }

    public void addItem(Drawable img, String title) {
        SideMidMenuItem items = new SideMidMenuItem();
        items.setBackColor(img);
        items.setTitle(title);
        bmItems.add(items);
    }

    public void setItem(int pos, Drawable img, String title) {
        SideMidMenuItem item = new SideMidMenuItem();
        item.setBackColor(img);
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
