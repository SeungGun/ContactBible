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
import com.windry.contactbible.data.SideMenuItem;

import java.util.ArrayList;

public class SideMenuAdapter extends BaseAdapter {
    private ArrayList<SideMenuItem> bmItems = new ArrayList<>();

    @Override
    public int getCount() {
        return bmItems.size();
    }

    @Override
    public SideMenuItem getItem(int position) {
        return bmItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.side_menu_listview_custom, parent, false);

            ImageView img = convertView.findViewById(R.id.point_dot);
            TextView main_title = convertView.findViewById(R.id.main_title);

            holder.img = img;
            holder.main_title = main_title;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SideMenuItem myItem = bmItems.get(position);
        holder.img.setImageDrawable(myItem.getPoint());
        holder.main_title.setText(myItem.getTitle());

        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView main_title;
    }

    public void addItem(Drawable img, String title) {
        SideMenuItem items = new SideMenuItem();
        items.setPoint(img);
        items.setTitle(title);
        bmItems.add(items);
    }

    public void setItem(int pos, Drawable img, String title) {
        SideMenuItem item = new SideMenuItem();
        item.setPoint(img);
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
