package com.windry.contactbible.activities;

import androidx.appcompat.app.AppCompatActivity;
import jxl.Sheet;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
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
    private LinearLayout loadingScr;
    private LinearLayout mainScr;
    private ImageButton back_btn;
    private SharedPreferences languagePref;
    private SharedPreferences.Editor editor;
    private ImageButton copyTextButton;
    private ImageButton shareTextButton;
    private boolean isKorean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_verse);
        korSheet = ((MainActivity) MainActivity.context_main).korean_sheet;
        engSheet = ((MainActivity) MainActivity.context_main).eng_sheet;
        todayTitle = findViewById(R.id.today_title);
        todayList = findViewById(R.id.today_list);
        languageToggle = findViewById(R.id.language_toggle);
        loadingScr = findViewById(R.id.loading_scr);
        mainScr = findViewById(R.id.today_scr);
        back_btn = findViewById(R.id.back_menu);
        copyTextButton = findViewById(R.id.copy_txt_btn);
        shareTextButton = findViewById(R.id.share_btn);

        languagePref = getSharedPreferences("side", MODE_PRIVATE);
        editor = languagePref.edit();

        isKorean = languagePref.getBoolean("language",true);
        languageToggle.setChecked(isKorean);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        try {
            getTodayVerseFromJson();
        }catch (Exception e){
            showToast("비정상 접근 : 문제발생 재접속 바랍니다.");
            finish();
        }
        languageToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b) {
                    isKorean = !isKorean;
                    adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, engList);
                    todayTitle.setText(bibleList[0].createEntireTitleEng(bibleList));
                    editor.putBoolean("language",false);
                    editor.apply();
                }
                else {
                    isKorean = !isKorean;
                    adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, korList);
                    todayTitle.setText(bibleList[0].createEntireTitleKor(bibleList));
                    editor.putBoolean("language",true);
                    editor.apply();
                }
                todayList.setAdapter(adapter);
            }
        });

        copyTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                if(todayTitle.getText().toString().equals("") || todayTitle.getText() == null){
                    showToast("아직 값을 받아오지 못했습니다. 잠시 후 시도해주세요.");
                    return;
                }
                if(todayList == null || todayList.getAdapter() == null || todayList.getCount() == 0){
                    showToast("아직 값을 받아오지 못했습니다. 잠시 후 시도해주세요.");
                    return;
                }
                StringBuilder str = new StringBuilder("");
                str.append(todayTitle.getText().toString()).append("\n\n");
                for(int i=0; i<todayList.getAdapter().getCount(); ++i){
                    str.append(todayList.getAdapter().getItem(i));
                    if(i != todayList.getAdapter().getCount() - 1)
                        str.append("\n\n");
                }
                ClipData clipData = ClipData.newPlainText("label",str);
                clipboardManager.setPrimaryClip(clipData);
                showToast("오늘의 구절을 복사했습니다.");
            }
        });

        shareTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                if(todayTitle.getText().toString().equals("") || todayTitle.getText() == null){
                    showToast("아직 값을 받아오지 못했습니다. 잠시 후 시도해주세요.");
                    return;
                }
                if(todayList == null || todayList.getAdapter() == null || todayList.getCount() == 0){
                    showToast("아직 값을 받아오지 못했습니다. 잠시 후 시도해주세요.");
                    return;
                }
                StringBuilder str = new StringBuilder("");
                str.append(todayTitle.getText().toString()).append("\n\n");
                for(int i=0; i<todayList.getAdapter().getCount(); ++i){
                    str.append(todayList.getAdapter().getItem(i));
                    if(i != todayList.getAdapter().getCount() - 1)
                        str.append("\n\n");
                }
                shareIntent.putExtra(Intent.EXTRA_TEXT,str.toString());
                Intent sharing = Intent.createChooser(shareIntent,"공유하기");
                startActivity(sharing);
            }
        });
    }

    public void getTodayVerseFromJson() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://labs.bible.org/api/?passage=votd&type=json&formatting=plain");
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

    public void getTodayVerseFromJson(String str, final int index) {
        if(str.contains(" ")){
            str = str.replace(' ','+');
        }
        final String finalStr = str;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://labs.bible.org/api/?passage="+ finalStr +"&type=json&formatting=plain");
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
                                parseJsonAsFormat(receiveMsgFromJson, index);
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
    public void parseJsonAsFormat(String jsonString, int index){
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                engList[index] = jsonObject.optString("text");
            }
            if(index == indexList.length - 1) {
                if (!isKorean) {
                    todayTitle.setText(bibleList[0].createEntireTitleEng(bibleList));
                    adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, engList);
                }
                todayList.setAdapter(adapter);
                loadingScr.setVisibility(View.GONE);
                mainScr.setVisibility(View.VISIBLE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("a"+index,"ff");

    }
    public void parseJson(String jsonString) {
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            indexList = new int[jsonArray.length()];
            bibleList = new Bible[jsonArray.length()];
            korList = new String[indexList.length];
            engList = new String[indexList.length];
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String book = jsonObject.optString("bookname");
                String chapter = jsonObject.optString("chapter");
                String verse = jsonObject.optString("verse");
                bibleList[i] = new Bible(book, chapter, verse);

                indexList[i] = findIndex(bibleList[i].createFormatted());
                bibleList[i].korBook = korSheet.getCell(0, indexList[i]).getContents().split(" ")[0];
                getTodayVerseFromJson(bibleList[i].createFormatted(), i);

            }
            getVerseText();
            if(isKorean) {
                todayTitle.setText(bibleList[0].createEntireTitleKor(bibleList));
                adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, korList);
            }

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
                if(entire.length == 1){
                    return this.book + " " + this.chapter + ":" + this.verse;
                }else {
                    return this.book + " " + this.chapter + ":" + this.verse + "~" + entire[entire.length - 1].verse;
                }
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
                if(entire.length == 1){
                    return this.korBook + " " + this.chapter + ":" + this.verse;
                }
                else {
                    return this.korBook + " " + this.chapter + ":" + this.verse + "~" + entire[entire.length - 1].verse;
                }
            } else {
                String front = this.korBook + " " + this.chapter + ":" + this.verse + "~" + entire[tmp - 1].verse;
                String rear = entire[tmp].korBook + " " + entire[tmp].chapter + ":" + entire[tmp].verse
                        + ((tmp == entire.length - 1) ? "" : "~" + entire[entire.length - 1].verse);
                return front + ", " + rear;
            }
        }
    }
    public void showToast(String charSequence) {
        Toast.makeText(this, charSequence, Toast.LENGTH_SHORT).show();
    }
}