package com.windry.contactbible;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.windry.contactbible.database.ReadDBHelper;

import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.SeekBarPreference;

public class SettingFragment extends PreferenceFragmentCompat {
    private SharedPreferences pref;
    private ListPreference themeColorPreference;
    private SeekBarPreference letterSpacePreference;
    private SeekBarPreference lineSpacePreference;
    private ListPreference backgroundColorPreference;
    private ListPreference textColorPreference;
    private PreferenceScreen resetProgressPreference;
    public String defaultThemeColor = "#BEDAFA";
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.setting,rootKey);

        themeColorPreference = findPreference("ThemeColor");
        letterSpacePreference = findPreference("letterSpace");
        backgroundColorPreference = findPreference("BackgroundColor");
        lineSpacePreference = findPreference("lineSpace");
        textColorPreference = findPreference("TextColor");
        resetProgressPreference = findPreference("ResetProgress");

        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!pref.getString("ThemeColor",defaultThemeColor).equals(defaultThemeColor)){
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
            switch (s) {
                case "ThemeColor": {
                    GradientDrawable left_box_color = (GradientDrawable) themeColorPreference.getIcon();
                    left_box_color.setColor(Color.parseColor(pref.getString("ThemeColor", defaultThemeColor)));
                    break;
                }
                case "letterSpace":
                    letterSpacePreference.setValue(pref.getInt("letterSpace", 0));
                    break;
                case "BackgroundColor": {
                    GradientDrawable left_box_color = (GradientDrawable) backgroundColorPreference.getIcon();
                    left_box_color.setColor(Color.parseColor(pref.getString("BackgroundColor", defaultThemeColor)));
                    break;
                }
                case "lineSpace":
                    lineSpacePreference.setValue(pref.getInt("lineSpace", 0));
                    break;
                case "TextColor": {
                    GradientDrawable left_box_color = (GradientDrawable) textColorPreference.getIcon();
                    left_box_color.setColor(Color.parseColor(pref.getString("TextColor", defaultThemeColor)));
                    break;
                }
            }
        }
    };

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {// preferene click event through key-value
        String key = preference.getKey();
        if(key.equals("ResetProgress")){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("※ 주의 ※").setMessage("정말로 초기화 하시겠습니까? 지금까지의 모든 읽은 기록을 초기화합니다.");
            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ReadDBHelper readDBHelper = new ReadDBHelper(getContext());
                    readDBHelper.deleteAllData();
                    showToast("진도율이 초기화 되었습니다.");
                }
            });
            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return true;
    }
    private void showToast(String charSequence) {
        Toast.makeText(getContext(), charSequence, Toast.LENGTH_SHORT).show();
    }
}
