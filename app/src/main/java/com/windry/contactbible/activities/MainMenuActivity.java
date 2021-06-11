package com.windry.contactbible.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.windry.contactbible.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {
    private Button go_read;
    private Button go_progress;
    private Button go_bookmark;
    private Button go_setting;
    private Button go_helper;
    private Button go_today;
    private MoveThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        go_bookmark = findViewById(R.id.bookmark_check);
        go_read= findViewById(R.id.read_continuous);
        go_progress = findViewById(R.id.progress_check);
        go_setting = findViewById(R.id.setting);
        go_helper = findViewById(R.id.app_helper);
        go_today = findViewById(R.id.today_verse);
        thread = new MoveThread();
        thread.start();
    }


    private class MoveThread extends Thread{
        private Intent intent;
        public void run(){
            go_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(view.getContext(), BookMarkActivity.class);
                    startActivity(intent);
                }
            });
            go_read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            go_progress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(view.getContext(), ProgressBarActivity.class);
                    startActivity(intent);
                }
            });
            go_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(view.getContext(), SettingActivity.class);
                    startActivity(intent);
                }
            });
            go_helper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(view.getContext(), AppHelperActivity.class);
                    startActivity(intent);
                }
            });
            go_today.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(view.getContext(), TodayVerseActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
