package com.windry.contactbible;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class BookMarkAdapter extends BaseAdapter {
    private ArrayList<BookmarkItem> bmItems = new ArrayList<>();
    @Override
    public int getCount(){
        return bmItems.size();
    }
    @Override
    public BookmarkItem getItem(int position){
        return bmItems.get(position);
    }
    @Override
    public long getItemId(int position){
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom,parent,false);
        }
        ImageView img = convertView.findViewById(R.id.img);
        TextView title = convertView.findViewById(R.id.outer_title);
        TextView date = convertView.findViewById(R.id.outer_date);
        BookmarkItem myitem = getItem(position);
        img.setImageDrawable(myitem.getIcon());
        title.setText(myitem.getTitle());
        date.setText(myitem.getDate()+"에 저장 됨");
        return convertView;
    }
    public void addItem(Drawable img,String title,String date){
        BookmarkItem item = new BookmarkItem();
        item.setIcon(img);
        item.setTitle(title);
        item.setDate(date);
        bmItems.add(item);
    }
    public void setItem(int pos,Drawable img,String title,String date){
        BookmarkItem item = new BookmarkItem();
        item.setIcon(img);
        item.setTitle(title);
        item.setDate(date);
        bmItems.set(pos,item);
    }
    public void removeItem(int pos){
        bmItems.remove(pos);
    }
}
