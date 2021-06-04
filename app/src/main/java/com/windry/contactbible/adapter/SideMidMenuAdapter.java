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
    public int getCount(){
        return bmItems.size();
    }
    @Override
    public SideMidMenuItem getItem(int position){
        return bmItems.get(position);
    }
    @Override
    public long getItemId(int position){
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Context context = parent.getContext();
        //demo_sheet = ((MainActivity)MainActivity.context_main).sheet; // 메인액티비티 클래스의 변수를 가져옴

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.side_midmenu_listview_custom,parent,false);
        }

        ImageView img = convertView.findViewById(R.id.color_back);
        TextView main_title = convertView.findViewById(R.id.mid_main);
        SideMidMenuItem myitem = bmItems.get(position);
        img.setImageDrawable(myitem.getBackColor());
        main_title.setText(myitem.getTitle());



        return convertView;
    }
    public void addItem(Drawable img, String title){
        SideMidMenuItem items = new SideMidMenuItem();
        items.setBackColor(img);
        items.setTitle(title);
        bmItems.add(items);

    }
    public void setItem(int pos,Drawable img,String title){
        SideMidMenuItem item = new SideMidMenuItem();
        item.setBackColor(img);
        item.setTitle(title);
        bmItems.set(pos,item);
    }
    public void removeItem(int pos){
        bmItems.remove(pos);
    }
    public void clearItem(){
        bmItems.clear();
    }
}
