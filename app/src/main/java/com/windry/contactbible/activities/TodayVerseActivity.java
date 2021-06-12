package com.windry.contactbible.activities;

import androidx.appcompat.app.AppCompatActivity;
import jxl.Sheet;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

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
    private ListView todayList;
    private ToggleButton languageToggle;
    private ArrayAdapter<String> adapter;
    private String[] korList;
    private String[] engList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_verse);
        korSheet = ((MainActivity) MainActivity.context_main).korean_sheet;
        engSheet = ((MainActivity) MainActivity.context_main).sheet;
        todayTitle = findViewById(R.id.today_title);
        todayList = findViewById(R.id.today_list);
        languageToggle = findViewById(R.id.language_toggle);
        languageToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, korList);
            }
        });
        getTodayVerseFromJson();
    }

    public void getTodayVerseFromJson() {
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

    public void getVerseText() {
        for (int i = 0; i < indexList.length; ++i) {
            korList[i] = korSheet.getCell(1, indexList[i]).getContents();
            engList[i] = engSheet.getCell(1, indexList[i]).getContents();
        }
    }

    public int findIndex(String str) {
        for (int i = 1; i <= 31102; ++i) {
            if (engSheet.getCell(0, i).getContents().equals(str)) {
                return i;
            }
        }
        return 0;
    }

    public void parseJson(String jsonString) {
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            indexList = new int[jsonArray.length()];
            bibleList = new Bible[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String book = jsonObject.optString("bookname");
                String chapter = jsonObject.optString("chapter");
                String verse = jsonObject.optString("verse");
                bibleList[i] = new Bible(book, chapter, verse);

                indexList[i] = findIndex(bibleList[i].createFormatted());
                bibleList[i].korBook = korSheet.getCell(0, indexList[i]).getContents().split(" ")[0];
            }
            getVerseText();
            todayTitle.setText(bibleList[0].createEntireTitleEng(bibleList) + ", " + bibleList[0].createEntireTitleKor(bibleList));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static class Bible {
        String book;
        String chapter;
        String verse;
        String korBook;

        public Bible(String book, String chapter, String verse) {
            this.book = book;
            this.chapter = chapter;
            this.verse = verse;
        }

        public String createFormatted() {
            return book + " " + chapter + ":" + verse;
        }

        public String createEntireTitleEng(Bible[] entire) {
            boolean flag = true;
            int tmp = 0;
            for (int i = 0; i < entire.length; ++i) {
                if (!this.book.equals(entire[i].book)) {
                    flag = false;
                    tmp = i;
                    break;
                }
            }
            if (flag) {
                return this.book + " " + this.chapter + ":" + this.verse + "~" + entire[entire.length - 1].verse;
            } else {
                String front = this.book + " " + this.chapter + ":" + this.verse + "~" + entire[tmp - 1].verse;
                String rear = entire[tmp].book + " " + entire[tmp].chapter + ":" + entire[tmp].verse
                        + ((tmp == entire.length - 1) ? "" : "~" + entire[entire.length - 1].verse);
                return front + ", " + rear;
            }
        }

        public String createEntireTitleKor(Bible[] entire) {
            boolean flag = true;
            int tmp = 0;
            for (int i = 0; i < entire.length; ++i) {
                if (!this.book.equals(entire[i].book)) {
                    flag = false;
                    tmp = i;
                    break;
                }
            }
            if (flag) {
                return this.korBook + " " + this.chapter + ":" + this.verse + "~" + entire[entire.length - 1].verse;
            } else {
                String front = this.korBook + " " + this.chapter + ":" + this.verse + "~" + entire[tmp - 1].verse;
                String rear = entire[tmp].korBook + " " + entire[tmp].chapter + ":" + entire[tmp].verse
                        + ((tmp == entire.length - 1) ? "" : "~" + entire[entire.length - 1].verse);
                return front + ", " + rear;
            }
        }
    }
}