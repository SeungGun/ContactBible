package com.windry.contactbible.activities;

import androidx.appcompat.app.AppCompatActivity;
import jxl.Sheet;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.windry.contactbible.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TodayVerseActivity extends AppCompatActivity {
    private int[] indexList;
    private Sheet korSheet;
    private Sheet engSheet;
    private Bible[] bibleList;
    private TextView todayTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_verse);
        korSheet = ((MainActivity)MainActivity.context_main).korean_sheet;
        engSheet = ((MainActivity)MainActivity.context_main).sheet;
        todayTitle = findViewById(R.id.today_title);
        getTodayVerseFromJson();
    }

    public void getTodayVerseFromJson(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://labs.bible.org/api/?passage=votd&type=json");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                    String tempString;
                    final String receiveMsgFromJson;
                    if (connection.getResponseCode() == connection.HTTP_OK) {
                        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
                        BufferedReader reader = new BufferedReader(inputStreamReader);
                        StringBuffer buffer = new StringBuffer();
                        while ((tempString = reader.readLine()) != null) {
                            buffer.append(tempString); // 읽어온 각 String 값을 임시 버퍼에 이어붙이기
                        }
                        receiveMsgFromJson = buffer.toString();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                parseJson(receiveMsgFromJson);
                            }
                        });
                        reader.close();

                    } else {
                        Log.e("통신 실패", connection.getResponseCode() + " 에러");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public int findIndex(String str){
        for(int i=1; i<=31102; ++i){
            if(engSheet.getCell(0,i).getContents().equals(str)){
                return i;
            }
        }
        return 0;
    }

    public void parseJson(String jsonString){
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            indexList = new int[jsonArray.length()];
            bibleList = new Bible[jsonArray.length()];
            for(int i=0; i< jsonArray.length(); ++i){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String book = jsonObject.optString("bookname");
                String chapter = jsonObject.optString("chapter");
                String verse = jsonObject.optString("verse");
                bibleList[i] = new Bible(book, chapter, verse);

                indexList[i] = findIndex(bibleList[i].createFormatted());
            }
            todayTitle.setText(bibleList[0].createEntireTitle(bibleList[bibleList.length - 1]));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static class Bible{
        String book;
        String chapter;
        String verse;
        public Bible(String book, String chapter, String verse){
            this.book = book;
            this.chapter =chapter;
            this.verse = verse;
        }
        public String createFormatted(){
            return book + " " + chapter + ":" + verse;
        }
        public String createEntireTitle(Bible another){
            return this.book +" "+ this.chapter +":" + this.verse +"~"+another.verse;
        }
    }
}