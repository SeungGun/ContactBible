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
import jxl.Sheet;

public class SideMenuAdapter extends BaseAdapter {
    private ArrayList<SideMenuItem> bmItems = new ArrayList<>();
    private Sheet demo_sheet;
    @Override
    public int getCount(){
        return bmItems.size();
    }
    @Override
    public SideMenuItem getItem(int position){
        return bmItems.get(position);
    }
    @Override
    public long getItemId(int position){
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Context context = parent.getContext();

        BookMarkDBHelper bmDBhelper = new BookMarkDBHelper(context.getApplicationContext());
        demo_sheet = ((MainActivity)MainActivity.context_main).sheet; // 메인액티비티 클래스의 변수를 가져옴


        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.side_menu_listview_custom,parent,false);
        }

        ImageView img = convertView.findViewById(R.id.point_dot);
        TextView main_title = convertView.findViewById(R.id.main_title);
        SideMenuItem myitem = bmItems.get(position);
        img.setImageDrawable(myitem.getPoint());
        main_title.setText(myitem.getTitle());



        return convertView;
    }
    public void addItem(Drawable img, String title){
            SideMenuItem items = new SideMenuItem();
            items.setPoint(img);
            items.setTitle(title);
            bmItems.add(items);

    }
    public void setItem(int pos,Drawable img,String title){
        SideMenuItem item = new SideMenuItem();
        item.setPoint(img);
        item.setTitle(title);
        bmItems.set(pos,item);
    }
    public void removeItem(int pos){
        bmItems.remove(pos);
    }

    private int findIndex(String str){
        for(int i = 1; i<=31102; i++){
            if(demo_sheet.getCell(0,i).getContents().equals(str)){
                return i;
            }
        }
        return 0;
    }
    public void clearItem(){
        bmItems.clear();
    }
}
