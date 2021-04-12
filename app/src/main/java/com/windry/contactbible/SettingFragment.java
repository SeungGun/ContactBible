package com.windry.contactbible;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

import java.util.Set;

import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SeekBarPreference;

public class SettingFragment extends PreferenceFragmentCompat {
    private SharedPreferences pref;
    private ListPreference themeColorPreference;
    private SeekBarPreference letterSpacePreference;
    private SeekBarPreference lineSpacePreference;
    private ListPreference backgroundColorPreference;
    private ListPreference textColorPreference;
    public String defaultThemeColor = "#BEDAFA";
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.setting,rootKey);

        themeColorPreference = findPreference("ThemeColor");
        letterSpacePreference = findPreference("letterSpace");
        backgroundColorPreference = findPreference("BackgroundColor");
        lineSpacePreference = findPreference("lineSpace");
        textColorPreference = findPreference("TextColor");

        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!pref.getString("ThemeColor",defaultThemeColor).equals(defaultThemeColor)){
//            themeColorPreference.setSummary(pref.getString("ThemeColor",defaultThemeColor));
            GradientDrawable left_box_color = (GradientDrawable) themeColorPreference.getIcon();
            left_box_color.setColor(Color.parseColor(pref.getString("ThemeColor",defaultThemeColor)));
        }
        if(pref.getInt("letterSpace",0) != 0){
            letterSpacePreference.setValue(pref.getInt("letterSpace",0));
        }
        if(pref.getInt("lineSpace",0) != 0){
            lineSpacePreference.setValue(pref.getInt("lineSpace",0));
        }
        if(!pref.getString("BackgroundColor",defaultThemeColor).equals(defaultThemeColor)){
            GradientDrawable left_box_color = (GradientDrawable) backgroundColorPreference.getIcon();
            left_box_color.setColor(Color.parseColor(pref.getString("BackgroundColor",defaultThemeColor)));
        }
        if(!pref.getString("TextColor",defaultThemeColor).equals(defaultThemeColor)){
            GradientDrawable left_box_color = (GradientDrawable) textColorPreference.getIcon();
            left_box_color.setColor(Color.parseColor(pref.getString("TextColor",defaultThemeColor)));
        }
        pref.registerOnSharedPreferenceChangeListener(listener);
    }

     SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if (s.equals("ThemeColor")) {
//                themeColorPreference.setSummary(pref.getString("ThemeColor",defaultThemeColor));
//                themeColorPreference.setSummary(themeColorPreference.getEntry());
                GradientDrawable left_box_color = (GradientDrawable) themeColorPreference.getIcon();
                left_box_color.setColor(Color.parseColor(pref.getString("ThemeColor",defaultThemeColor)));
            }
            else if(s.equals("letterSpace")){
                letterSpacePreference.setValue(pref.getInt("letterSpace",0));
//                Log.d("Val",pref.getInt("letterSpace",0)+"");
            }
            else if(s.equals("BackgroundColor")){
                GradientDrawable left_box_color = (GradientDrawable) backgroundColorPreference.getIcon();
                left_box_color.setColor(Color.parseColor(pref.getString("BackgroundColor",defaultThemeColor)));
            }
            else if(s.equals("lineSpace")){
                lineSpacePreference.setValue(pref.getInt("lineSpace",0));
            }
            else if(s.equals("TextColor")){
                GradientDrawable left_box_color = (GradientDrawable) textColorPreference.getIcon();
                left_box_color.setColor(Color.parseColor(pref.getString("TextColor",defaultThemeColor)));
            }
        }
    };
}
