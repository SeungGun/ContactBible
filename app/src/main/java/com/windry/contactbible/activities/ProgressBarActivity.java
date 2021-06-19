package com.windry.contactbible.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.windry.contactbible.database.ReadDBHelper;
import com.windry.contactbible.R;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressBarActivity extends AppCompatActivity {
    private ProgressBar progress;
    private ProgressBar progress_old;
    private ProgressBar progress_new;

    private ReadDBHelper bmDBhelper;
    private TextView percentage;
    private TextView percentage_old;
    private TextView percentage_new;
    private ImageButton progress_back;
    private static int count = 0;
    private static int oldCount = 0;
    private static int newCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_layout);
        percentage = findViewById(R.id.percentage);
        percentage_new = findViewById(R.id.percentage_new);
        percentage_old = findViewById(R.id.percentage_old);
        progress_back = findViewById(R.id.progress_back);
        progress_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bmDBhelper = new ReadDBHelper(this);
        selectDB();

        double val = (count / 31102.0) * 100;
        percentage.setText(String.format("전체 : %.1f",val)+"% ("+count+"/ 31102)");
        progress = findViewById(R.id.progressbar);
        progress.setProgress((int)val);
        double val2 = (oldCount/23145.0) * 100;
        percentage_old.setText(String.format("구약성경 :%.1f",val2)+"% ("+oldCount+"/ 23145)");
        progress_old = findViewById(R.id.progressbar_old);
        progress_old.setProgress((int)val2);
        double val3 = (newCount/7957.0) * 100;
        percentage_new.setText(String.format("신약성경 : %.1f",val3)+"% ("+newCount+"/ 7957)");
        progress_new = findViewById(R.id.progressbar_new);
        progress_new.setProgress((int)val3);

    }
    public void selectDB(){// search db (select in java cursor)
        Cursor cur = bmDBhelper.readRecord("true");
        if(cur!= null && cur.getCount() > 0){
            if(cur.moveToFirst()){
                do{
                    int tmp = cur.getInt(0);
                    if( tmp<=23145 && tmp >= 1){
                        oldCount++;
                    }
                    else if(tmp > 23145 && tmp <=31102){
                        newCount++;
                    }
                    count++;
                }while(cur.moveToNext());
            }
        }
        //count = cur.getCount();
        cur.close();

    }
    private void showToast(String charSequence){
        Toast.makeText(this,charSequence,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy(){
        bmDBhelper.close();
        super.onDestroy();
        count = 0;
        newCount = 0;
        oldCount = 0;
    }
    @Override
    public void onStop(){
        bmDBhelper.close();
        super.onStop();
        count = 0;
        newCount = 0;
        oldCount = 0;
    }

}
