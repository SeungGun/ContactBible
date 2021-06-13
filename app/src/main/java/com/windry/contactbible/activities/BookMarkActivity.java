package com.windry.contactbible.activities;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.windry.contactbible.adapter.BookMarkAdapter;
import com.windry.contactbible.database.DateDBHelper;
import com.windry.contactbible.R;
import com.windry.contactbible.database.RealBMDBHelper;

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
import java.util.Arrays;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import jxl.Sheet;

public class BookMarkActivity extends AppCompatActivity {
    private Sheet demo_sheet = null;
    private Sheet korean_demo_sheet = null;
    private RealBMDBHelper bmdbHelper;
    private ListView listView;
    private boolean[] opened;
    private BookMarkAdapter bookMarkAdapter;
    private ImageButton back_btn;
    private boolean isLeak = false;
    private DateDBHelper dateDBHelper;
    private boolean isKorean;
    private String result;
    private Thread parseThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark_layout);

        listView = findViewById(R.id.bookmark_list);
        bmdbHelper = new RealBMDBHelper(this);
        dateDBHelper = new DateDBHelper(this);

        demo_sheet = ((MainActivity) MainActivity.context_main).eng_sheet; // 메인액티비티 클래스의 변수를 가져옴
        korean_demo_sheet = ((MainActivity) MainActivity.context_main).korean_sheet;
        isKorean = ((MainActivity) MainActivity.context_main).isKorean;

        back_btn = findViewById(R.id.bookmark_back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listViewSetting(); // reads data from db and sets custom list view through adapter

        opened = new boolean[listView.getCount()];
        Arrays.fill(opened, false);
        /*목록 클릭시 제목에 대한 내용을 보여주는 리스트뷰 클릭 이벤트 */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!opened[i]) {
                    Cursor cur2;
                    if (!isKorean) {
                        cur2 = dateDBHelper.readRecord(findIndex(bookMarkAdapter.getItem(i).getTitle()));
                    } else {
                        cur2 = dateDBHelper.readRecord(findKoreanIndex(bookMarkAdapter.getItem(i).getTitle()));
                    }
                    String t = "";
                    if (cur2 != null && cur2.getCount() > 0) {
                        if (cur2.moveToFirst()) {
                            do {
                                t = cur2.getString(cur2.getColumnIndex("DATES"));
                            } while (cur2.moveToNext());
                        }
                    }
                    if (!isKorean) {
                        getParsedJson(demo_sheet.getCell(0, findIndex(bookMarkAdapter.getItem(i).getTitle())).getContents(), i, t);
                        bookMarkAdapter.setItem(i, ContextCompat.getDrawable(getApplicationContext(), R.drawable.filled_bookmark), bookMarkAdapter
                                .getItem(i).getTitle() + "\n" + "불러오는 중입니다.", t);

                    } else {
                        bookMarkAdapter.setItem(i, ContextCompat.getDrawable(getApplicationContext(), R.drawable.filled_bookmark), bookMarkAdapter
                                .getItem(i).getTitle() + "\n" + korean_demo_sheet.getCell(1, findKoreanIndex(bookMarkAdapter.getItem(i).getTitle())).getContents(), t);
                    }
                    bookMarkAdapter.notifyDataSetChanged();

                    opened[i] = true;
                } else {
                    Cursor cur2;
                    if (!isKorean) {
                        cur2 = dateDBHelper.readRecord(findIndex2(bookMarkAdapter.getItem(i).getTitle()));
                    } else {
                        cur2 = dateDBHelper.readRecord(findKoreanIndex2(bookMarkAdapter.getItem(i).getTitle()));
                    }
                    String t = "";
                    if (cur2 != null && cur2.getCount() > 0) {
                        if (cur2.moveToFirst()) {
                            do {
                                t = cur2.getString(cur2.getColumnIndex("DATES"));
                            } while (cur2.moveToNext());
                        }
                    }
                    opened[i] = false;
                    if (!isKorean) {
                        bookMarkAdapter.setItem(i, ContextCompat.getDrawable(getApplicationContext(), R.drawable.filled_bookmark), /*demo_sheet
                                .getCell(0, findIndex2(*/bookMarkAdapter.getItem(i).getTitle().split("\n")[0]/*)).getContents()*/, bookMarkAdapter.getItem(i).getDate());
                    } else {
                        bookMarkAdapter.setItem(i, ContextCompat.getDrawable(getApplicationContext(), R.drawable.filled_bookmark), korean_demo_sheet
                                .getCell(0, findKoreanIndex2(bookMarkAdapter.getItem(i).getTitle())).getContents(), t);
                    }
                    bookMarkAdapter.notifyDataSetChanged();
                }
            }
        });
        /* -----------------------------------------------*/

        /* 리스트뷰 각 목록을 꾹 길게 눌렀을 시 삭제요청 하는 Long클릭 이벤트 */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int index = i;
                if (bookMarkAdapter.getCount() == 1) isLeak = true;
                else isLeak = false;

                if (!bookMarkAdapter.getItem(index).getTitle().equals("저장된 즐겨찾기가 없습니다.")) {
                    new AlertDialog.Builder(BookMarkActivity.this)
                            .setMessage(bookMarkAdapter.getItem(i).getTitle() + " 즐겨찾기를 삭제하시겠습니까?")
                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    try {
                                        if (!isKorean) {
                                            bmdbHelper.deleteRecord(findIndex(bookMarkAdapter.getItem(index).getTitle()));
                                            dateDBHelper.deleteRecord(findIndex(bookMarkAdapter.getItem(index).getDate()));
                                        } else {
                                            bmdbHelper.deleteRecord(findIndex(ChangeLan(bookMarkAdapter.getItem(index).getTitle())));
                                            dateDBHelper.deleteRecord(findIndex(ChangeLan(bookMarkAdapter.getItem(index).getTitle())));
                                        }

                                        //ArrayList 사이즈 문제 마지막 인덱스값 삭제가 안됨 -> db 먼저 삭제
                                        bookMarkAdapter.removeItem(index);
                                        bookMarkAdapter.notifyDataSetChanged();
                                        if (isLeak) {
                                            bookMarkAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.filled_bookmark), "저장된 즐겨찾기가 없습니다.", "---- -- -- --:--");
                                            isLeak = false;
                                        }
                                        showToast("삭제되었습니다.");
                                    } catch (Exception e) {
                                        Log.e("Error", e.getMessage());
                                        e.printStackTrace();
                                    }
                                }
                            })
                            .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    showToast("취소되었습니다.");
                                }
                            }).show();
                }
                return true;
            }
        });
        /*------------------------------------------------------------*/
    }

    private void showToast(String charSequence) {
        Toast.makeText(this, charSequence, Toast.LENGTH_SHORT).show();
    }

    public void listViewSetting() {
        bookMarkAdapter = new BookMarkAdapter();
        Cursor cur = bmdbHelper.readRecord();//read from database
        String t = "----";
        if (cur != null && cur.getCount() > 0) {
            if (cur.moveToFirst()) {
                do {
                    Cursor cur2 = dateDBHelper.readRecord(cur.getInt(0));
                    if (cur2 != null && cur2.getCount() > 0) {
                        if (cur2.moveToFirst()) {
                            do {
                                t = cur2.getString(cur2.getColumnIndex("DATES"));
                            } while (cur2.moveToNext());
                        }
                    }
                    if (!isKorean) {
                        bookMarkAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.filled_bookmark), demo_sheet
                                .getCell(0, cur.getInt(0)).getContents(), t);
                    } else {
                        bookMarkAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.filled_bookmark), korean_demo_sheet
                                .getCell(0, cur.getInt(0)).getContents(), t);
                    }
                } while (cur.moveToNext());
            }
        } else {
            bookMarkAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.filled_bookmark), "저장된 즐겨찾기가 없습니다.", "---- -- -- --:--");
        }
        listView.setAdapter(bookMarkAdapter);
    }

    public int findIndex(String str) {
        for (int i = 1; i <= 31102; ++i) {
            if (demo_sheet.getCell(0, i).getContents().equals(str)) {
                return i;
            }
        }
        return 0;
    }

    public int findIndex2(String str) {
        for (int i = 1; i <= 31102; ++i) {
            if (demo_sheet.getCell(1, i).getContents().equals(str.substring(str.indexOf("\n") + 1))) {
                return i;
            }
        }
        return 0;
    }

    public int findKoreanIndex(String str) {
        for (int i = 1; i <= 31102; ++i) {
            if (korean_demo_sheet.getCell(0, i).getContents().equals(str)) {
                return i;
            }
        }
        return 0;
    }

    public int findKoreanIndex2(String str) {
        for (int i = 1; i <= 31102; ++i) {
            if (korean_demo_sheet.getCell(1, i).getContents().equals(str.substring(str.indexOf("\n") + 1))) {
                return i;
            }
        }
        return 0;
    }

    @Override
    protected void onDestroy() {
        bmdbHelper.close();
        dateDBHelper.close();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        bmdbHelper.close();
        dateDBHelper.close();
        super.onStop();
    }

    public String ChangeLan(String str) {
        String[] splited = str.split(" ");
        switch (splited[0]) {
            case "창세기":
                return "Genesis " + splited[1];
            case "출애굽기":
                return "Exodus " + splited[1];
            case "레위기":
                return "Leviticus " + splited[1];
            case "민수기":
                return "Numbers " + splited[1];
            case "신명기":
                return "Deuteronomy " + splited[1];
            case "여호수아":
                return "Joshua " + splited[1];
            case "사사기":
                return "Judges " + splited[1];
            case "룻기":
                return "Ruth " + splited[1];
            case "사무엘상":
                return "1 Samuel " + splited[1];
            case "사무엘하":
                return "2 Samuel " + splited[1];
            case "열왕기상":
                return "1 Kings " + splited[1];
            case "열왕기하":
                return "2 Kings " + splited[1];
            case "역대상":
                return "1 Chronicles " + splited[1];
            case "역대하":
                return "2 Chronicles " + splited[1];
            case "에스라":
                return "Ezra " + splited[1];
            case "느헤미야":
                return "Nehemiah " + splited[1];
            case "에스더":
                return "Esther " + splited[1];
            case "욥기":
                return "Job " + splited[1];
            case "시편":
                return "Psalms " + splited[1];
            case "잠언":
                return "Proverbs " + splited[1];
            case "전도서":
                return "Ecclesiastes " + splited[1];
            case "아가":
                return "Song of Solomon " + splited[1];
            case "이사야":
                return "Isaiah " + splited[1];
            case "예레미야":
                return "Jeremiah " + splited[1];
            case "예레미야애가":
                return "Lamentations " + splited[1];
            case "에스겔":
                return "Ezekiel " + splited[1];
            case "다니엘":
                return "Daniel " + splited[1];
            case "호세아":
                return "Hosea " + splited[1];
            case "요엘":
                return "Joel " + splited[1];
            case "아모스":
                return "Amos " + splited[1];
            case "오바댜":
                return "Obadiah " + splited[1];
            case "요나":
                return "Jonah " + splited[1];
            case "미가":
                return "Micah " + splited[1];
            case "나훔":
                return "Nahum " + splited[1];
            case "하박국":
                return "Habakkuk " + splited[1];
            case "스바냐":
                return "Zephaniah " + splited[1];
            case "학개":
                return "Haggai " + splited[1];
            case "스가랴":
                return "Zechariah " + splited[1];
            case "말라기":
                return "Malachi " + splited[1];
            case "마태복음":
                return "Matthew " + splited[1];
            case "마가복음":
                return "Mark " + splited[1];
            case "누가복음":
                return "Luke " + splited[1];
            case "요한복음":
                return "John " + splited[1];
            case "사도행전":
                return "Acts " + splited[1];
            case "로마서":
                return "Romans " + splited[1];
            case "고린도전서":
                return "1 Corinthians " + splited[1];
            case "고린도후서":
                return "2 Corinthians " + splited[1];
            case "갈라디아서":
                return "Galatians " + splited[1];
            case "에베소서":
                return "Ephesians " + splited[1];
            case "빌립보서":
                return "Philippians " + splited[1];
            case "골로새서":
                return "Colossians " + splited[1];
            case "데살로니가전서":
                return "1 Thessalonians " + splited[1];
            case "데살로니가후서":
                return "2 Thessalonians " + splited[1];
            case "디모데전서":
                return "1 Timothy " + splited[1];
            case "디모데후서":
                return "2 Timothy " + splited[1];
            case "디도서":
                return "Titus " + splited[1];
            case "빌레몬서":
                return "Philemon " + splited[1];
            case "히브리서":
                return "Hebrews " + splited[1];
            case "야고보서":
                return "James " + splited[1];
            case "베드로전서":
                return "1 Peter " + splited[1];
            case "베드로후서":
                return "2 Peter " + splited[1];
            case "요한일서":
                return "1 John " + splited[1];
            case "요한이서":
                return "2 John " + splited[1];
            case "요한삼서":
                return "3 John " + splited[1];
            case "유다서":
                return "Jude " + splited[1];
            case "요한계시록":
                return "Revelation " + splited[1];
            default:
                return "Unexpected";
        }
    }

    public void getParsedJson(String recv, final int index, final String dates) {
        if (recv.contains(" ")) {
            recv = recv.replace(' ', '+');
        }
        final String finalReceive = recv;

        parseThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String receiveMsgFromJson;
                    String tempString;
                    URL url = new URL("https://labs.bible.org/api/?passage=" + finalReceive + "&type=json");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

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
                                parseJson(receiveMsgFromJson, index, dates);
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
        });
        parseThread.start();

    }

    public void parseJson(String jsonString, int index, String t) {
        String res = "";
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                res = jsonObject.optString("text");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bookMarkAdapter.setItem(index, ContextCompat.getDrawable(getApplicationContext(), R.drawable.filled_bookmark), bookMarkAdapter
                .getItem(index).getTitle().split("\n")[0] + "\n" + res, t);
        bookMarkAdapter.notifyDataSetChanged();
    }
}
