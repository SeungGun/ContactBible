package com.windry.contactbible.activities;
//Copyright by Seunggun sin

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.PreferenceManager;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.windry.contactbible.database.ReadDBHelper;
import com.windry.contactbible.database.DateDBHelper;
import com.windry.contactbible.database.IndexDBHelper;
import com.windry.contactbible.database.MemoDBHelper;
import com.windry.contactbible.R;
import com.windry.contactbible.database.BookMarkDBHelper;
import com.windry.contactbible.data.SheetNumList;
import com.windry.contactbible.adapter.SideMenuAdapter;
import com.windry.contactbible.adapter.SideMidMenuAdapter;
import com.windry.contactbible.adapter.SideOuterMenuAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*NET Bible Web Service (API)
https://labs.bible.org/api_web_service */

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    public static Context context_main; // 현재 액티비티

    private GestureDetector gestureDetector;
    private NaviBarThread thread; // 사이드바 움직임 쓰레드
    private ListClickThread list_thread; // 사이드바 in/out 쓰레드
    private ReadExcelThread excel_thread; // 엑셀 읽기 쓰레드
    private ReadKoreanExcelThread koreanExcelThread;

    private DrawerLayout drawerLayout; // 사이드바 움직임 화면 layout
    private View drawerView;
    private Workbook workbook; // 파일 읽기
    private Workbook korean_workbook;
    public Sheet eng_sheet; // 엑셀 객체 - 영어
    public Sheet korean_sheet; // 액셀 객체 - 한글

    private ListView outerListView; // 사이드바 Outer 리스트뷰
    private ListView innerListView; // 사이드바 Inner 리스트뷰
    private ListView midListView; // 사이드바 Mid 리스트뷰

    private ScrollView side1_layout; // 1절 단위 화면 layout
    private ScrollView side2_layout; // 2절 단위 화면 layout
    private ScrollView side3_layout; // 3절 단위 화면 layout

    private LinearLayout side1_entire; //1절 단위 터치 화면
    private LinearLayout side2_entire;// 2절 단위 터치 화면
    private LinearLayout side3_entire;// 3절 단위 터치 화면
    private LinearLayout bottom_menu; // 하단 메뉴 화면 layout
    private LinearLayout top_menu; // 상단 메뉴 화면 layout
    private LinearLayout bottom_center_menu;
    private LinearLayout bottom_right_menu;
    private LinearLayout above_content_layout;
    private LinearLayout loadingMenu; // drawer 메뉴에서 mid 리스트뷰로 이동 시 보여주는 로딩 화면
    private LinearLayout backgroundGoMain;

    private TextView title; // 상단바에 보여지는 내용 공간
    private TextView side1_Content; // 1절 단위의 내용 공간
    private TextView side2_Content; // 2절 단위의 내용 공간
    private TextView side3_Content; // 3절 단위의 내용 공간
    private EditText memo_content; // 메모 입력하는 공간

    private IndexDBHelper dbHelper; //현재 성경위치 대한 인덱스 데이터베이스
    private MemoDBHelper memoDBHelper; // 각 절마다의 적은 메모 데이터베이스
    private ReadDBHelper bmDBHelper; // 읽었는지 확인하는 데이터베이스
    private BookMarkDBHelper realBMDBHelper; // 즐겨찾기한 항목 데이터베이스
    private DateDBHelper dateDBHelper; // 즐겨찾기한 날짜 데이터베이스

    private Button memo_store; // 메모 저장 버튼
    private Button goto_main; // 메인메뉴 이동 버튼
    private ImageButton outer_btn; // 사이드바에서 inner list view일 때 outer로 이동하는 버튼
    private ImageButton bookmarking; // 북마크 설정/해제 버튼
    private Button memo_OnOff; // 메모장 공간 on/off 버튼
    private SeekBar textSize_control;
    private Button quit_memo_btn; // 메모장 열렸을 시 메모장 공간에서 메모장 끄는 버튼
    private ImageButton play_music;
    private Button control_volume;
    private Switch language_switch;
    private Button side1Button;
    private Button side2Button;
    private Button side3Button;

    private static int title_textSize = 14; // 상단바 제목 기본 글자 크기(단위 : pt)
    private static int index = 1; // 현재 성경 index(영어)
    private static int current_side_num = 1; // 현재 절 단위 값
    private long lastTimeBackPressed; // 뒤로 가기 버튼 눌른 시간
    private static final int SWIPE_MIN_DISTANCE = 50;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private int mid_index = 0;
    public final static int MAX_ROW_EXCEL = 31102;

    private boolean isOpen = false; // 메모장 열렸는지 판단
    private boolean isInnerSheet = false; // 사이드메뉴 Inner인지 아닌지 판단
    private boolean isOuterSheet = true; // 사이드메뉴 Outer인지 아닌지 판단 
    private boolean isMidSheet = false; // 사이드메뉴 Mid인지 아닌지 판단
    public boolean isKorean = true; // 현재 한국어인지 판단
    private boolean isPlayed = false; // 음악이 재생되고 있는지 판단

    private ArrayList<Boolean> isAll = new ArrayList<>();
    private SideMenuAdapter sideMenuAdapter;
    private SideMidMenuAdapter sideMidMenuAdapter;
    private SideOuterMenuAdapter sideOuterMenuAdapter;
    private SheetNumList sheetNumList;
    private MediaPlayer mediaPlayer;
    private SharedPreferences pref;
    private SharedPreferences side_pref;
    private SharedPreferences.Editor editor;
    private String outer_str; // 성경배열을 받는 임시 변수
    private String mid_str;
    private String mid_reverse_str;
    public static final String defaultThemeColor = "#BEDAFA";
    public static final String strongThemeColor = "#5E92C4";
    public static final String[] bible_titleList = {"Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy",
            "Joshua", "Judges", "Ruth", "1 Samuel", "2 Samuel", "1 Kings", "2 Kings", "1 Chronicles",
            "2 Chronicles", "Ezra", "Nehemiah", "Esther", "Job", "Psalms", "Proverbs", "Ecclesiastes",
            "Song of Solomon", "Isaiah", "Jeremiah", "Lamentations", "Ezekiel", "Daniel", "Hosea",
            "Joel", "Amos", "Obadiah", "Jonah", "Micah", "Nahum", "Habakkuk", "Zephaniah", "Haggai", "Zechariah", "Malachi",
            "Matthew", "Mark", "Luke", "John", "Acts", "Romans",
            "1 Corinthians", "2 Corinthians", "Galatians", "Ephesians", "Philippians", "Colossians",
            "1 Thessalonians", "2 Thessalonians", "1 Timothy", "2 Timothy", "Titus", "Philemon", "Hebrews",
            "James", "1 Peter", "2 Peter", "1 John", "2 John", "3 John", "Jude", "Revelation"};// 성경 제목 리스트 배열
    public static final String[] korean_bible_titleList = {"창세기", "출애굽기", "레위기", "민수기", "신명기", "여호수아", "사사기", "룻기", "사무엘상", "사무엘하",
            "열왕기상", "열왕기하", "역대상", "역대하", "에스라", "느헤미야", "에스더", "욥기", "시편", "잠언", "전도서", "아가", "이사야", "예레미야", "예레미야애가", "에스겔",
            "다니엘", "호세아", "요엘", "아모스", "오바댜", "요나", "미가", "나훔", "하박국", "스바냐", "학개", "스가랴", "말라기",
            "마태복음", "마가복음", "누가복음", "요한복음", "사도행전", "로마서", "고린도전서", "고린도후서", "갈라디아서", "에베소서", "빌립보서", "골로새서", "데살로니가전서", "데살로니가후서",
            "디모데전서", "디모데후서", "디도서", "빌레몬서", "히브리서", "야고보서", "베드로전서", "베드로후서", "요한일서", "요한이서", "요한삼서", "유다서", "요한계시록"};

    /*-------------------------------------------------------------------------------------------------------  Attributes */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context_main = this;

        /* 데이터베이스 객체 초기화 */
        initializeDatabaseObj();

        /* 인스턴스 멤버 변수 초기화 findViewById() */
        initializeMemberVar();

        /* 데이터베이스에 저장한 Index 값 가져와서 초기 Index 설정 */
        getInitialIndex();

        /* 엑셀 파일 읽는 Thread 시작 */
        initialExcelFile();

        /* 엑셀 index 위치 데이터 객체 초기화 */
        sheetNumList = new SheetNumList();

        /* 어댑터 객체 초기화 */
        initializeAdapterObj();

        /* 초기 설정된 index의 memo 내용 불러오기 */
        selectMEMODB();

        /* 초기 북마크 값 가져오기 */
        getInitialBookmark();

        /*초기 화면 환경설정 setting - SharedPreference*/
        initialEVSetting();

        gestureDetector = new GestureDetector(this);
        mediaPlayer = MediaPlayer.create(this, R.raw.calm_bgm);

        /* 절 단위 모드 초기 설정 및 글씨 크기 조절 bar 초기 설정*/
        side_pref = getSharedPreferences("side", MODE_PRIVATE);
        editor = side_pref.edit();

        int initial_text_size = side_pref.getInt("text_size", 17); //chk
        textSize_control.setProgress(initial_text_size); //chk

        /* 초기 제목과 내용 설정 */
        isKorean = side_pref.getBoolean("language", true);
        if (isKorean) {
            for (String each : korean_bible_titleList) {
                sideOuterMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_menu_book_24), each);
            }
        } else {
            for (String each : bible_titleList) {
                sideOuterMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_menu_book_24), each);
            }
        }
        outerListView.setAdapter(sideOuterMenuAdapter);

        list_thread = new ListClickThread();
        list_thread.start();

        /*------------------------Initializes attributes and initial screen(basic view)--------------------------------------*/

        /* drawer 메뉴 상단 뒤로 가기 버튼 이벤트 */
        menuBackEvent();

        /* 메모창 열고 닫는 이벤트 */
        memoOnOffEvent();

        /* 메모창 닫는 버튼에 대한 이벤트 */
        memoQuitEvent();

        /* 음악 재생 이벤트 */
        playMusicEvent();

        /* 북마크 버튼 눌렀을 때 이벤트 */
        bookmarkingInDB();

        /* 메모내용 저장하는 이벤트 */
        storeMemoInDB();

        /* 언어 변경 스위치 버튼 이벤트 */
        switchLanguage();

        /*---------------------- Button Events extracted ---------------------------*/

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        control_volume.setText(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) + ""); //chk
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewInDialog = inflater.inflate(R.layout.alert_seekbar, null);
        SeekBar vol = viewInDialog.findViewById(R.id.sound_control);
        vol.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        control_volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                View viewInDialog = inflater.inflate(R.layout.alert_seekbar, null);
                final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setView(viewInDialog).create();
                dialog.setTitle("음악 음량 조절");

                dialog.show();

                SeekBar soundControl = viewInDialog.findViewById(R.id.sound_control);
                soundControl.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
                soundControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                                i, 0);
                        control_volume.setText(i + "");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        });

        textSize_control.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (current_side_num == 1) {
                    side1_Content.setTextSize(TypedValue.COMPLEX_UNIT_PT, seekBar.getProgress());
                } else if (current_side_num == 2) {
                    side2_Content.setTextSize(TypedValue.COMPLEX_UNIT_PT, seekBar.getProgress());
                } else if (current_side_num == 3) {
                    side3_Content.setTextSize(TypedValue.COMPLEX_UNIT_PT, seekBar.getProgress());
                }
                editor.putInt("text_size", seekBar.getProgress());
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        /* -----------------  Seek Bar Setting(절 단위 변경 bar) -------------------*/
        current_side_num = side_pref.getInt("side_mode", 1); //chk
        switch (current_side_num) {
            case 1:
                memo_OnOff.setVisibility(View.VISIBLE);
                bookmarking.setVisibility(View.VISIBLE);
                side1_layout.setVisibility(View.VISIBLE);
                side2_layout.setVisibility(View.GONE);
                side3_layout.setVisibility(View.GONE);

                if (!isKorean) {
                    setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 1, "left", index);
                    title.setText(eng_sheet.getCell(0, index).getContents());
                } else {
                    side1_Content.setText(korean_sheet.getCell(1, index).getContents());
                    title.setText(korean_sheet.getCell(0, index).getContents());
                }
                title_textSize = 14;
                title.setTextSize(TypedValue.COMPLEX_UNIT_PT, title_textSize);
                side1_Content.setTextSize(TypedValue.COMPLEX_UNIT_PT, textSize_control.getProgress());
                getInitialBookmark();

                side1Button.setBackgroundColor(Color.parseColor(strongThemeColor));
                side2Button.setBackgroundColor(Color.parseColor(defaultThemeColor));
                side3Button.setBackgroundColor(Color.parseColor(defaultThemeColor));
                break;
            case 2:
                if (!isKorean) {
                    memo_OnOff.setVisibility(View.INVISIBLE);
                    bookmarking.setVisibility(View.INVISIBLE);
                    side1_layout.setVisibility(View.GONE);
                    side2_layout.setVisibility(View.VISIBLE);
                    side3_layout.setVisibility(View.GONE);
                    title_textSize = 13;

                    title.setTextSize(TypedValue.COMPLEX_UNIT_PT, title_textSize);
                    String splitStr1 = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1).getContents());
                    title.setText(splitStr1);

                    setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 2, "left", index);
                    side2_Content.setTextSize(TypedValue.COMPLEX_UNIT_PT, textSize_control.getProgress());
                } else {
                    memo_OnOff.setVisibility(View.INVISIBLE);
                    bookmarking.setVisibility(View.INVISIBLE);
                    side1_layout.setVisibility(View.GONE);
                    side2_layout.setVisibility(View.VISIBLE);
                    side3_layout.setVisibility(View.GONE);

                    title_textSize = 13;

                    title.setTextSize(TypedValue.COMPLEX_UNIT_PT, title_textSize);
                    title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1]);

                    side2_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents());
                    side2_Content.setTextSize(TypedValue.COMPLEX_UNIT_PT, textSize_control.getProgress());
                }
                side1Button.setBackgroundColor(Color.parseColor(defaultThemeColor));
                side2Button.setBackgroundColor(Color.parseColor(strongThemeColor));
                side3Button.setBackgroundColor(Color.parseColor(defaultThemeColor));
                break;
            case 3:
                if (!isKorean) {
                    memo_OnOff.setVisibility(View.INVISIBLE);
                    bookmarking.setVisibility(View.INVISIBLE);
                    side1_layout.setVisibility(View.GONE);
                    side2_layout.setVisibility(View.GONE);
                    side3_layout.setVisibility(View.VISIBLE);
                    title_textSize = 11;

                    title.setTextSize(TypedValue.COMPLEX_UNIT_PT, title_textSize);
                    String splitStr2 = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                            .getContents()) + "," + splitTitle(eng_sheet.getCell(0, index + 2).getContents());
                    title.setText(splitStr2);

                    setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 3, "left", index);
                    side3_Content.setTextSize(TypedValue.COMPLEX_UNIT_PT, textSize_control.getProgress());
                } else {
                    memo_OnOff.setVisibility(View.INVISIBLE);
                    bookmarking.setVisibility(View.INVISIBLE);
                    side1_layout.setVisibility(View.GONE);
                    side2_layout.setVisibility(View.GONE);
                    side3_layout.setVisibility(View.VISIBLE);

                    title_textSize = 11;
                    title.setTextSize(TypedValue.COMPLEX_UNIT_PT, title_textSize);
                    title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1] + "," +
                            korean_sheet.getCell(0, index + 2).getContents().split(" ")[1]);

                    side3_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents()
                            + "\n\n" + korean_sheet.getCell(1, index + 2).getContents());
                    side3_Content.setTextSize(TypedValue.COMPLEX_UNIT_PT, textSize_control.getProgress());

                }
                side1Button.setBackgroundColor(Color.parseColor(defaultThemeColor));
                side2Button.setBackgroundColor(Color.parseColor(defaultThemeColor));
                side3Button.setBackgroundColor(Color.parseColor(strongThemeColor));
                break;
        }
        /*----------------------------------------------------------------------------------------------*/

        side1Button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                memo_OnOff.setVisibility(View.VISIBLE);
                bookmarking.setVisibility(View.VISIBLE);
                side1_layout.setVisibility(View.VISIBLE);
                side2_layout.setVisibility(View.GONE);
                side3_layout.setVisibility(View.GONE);

                if (!isKorean) {
                    setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 1, "left", index);
                    title.setText(eng_sheet.getCell(0, index).getContents());
                } else {
                    side1_Content.setText(korean_sheet.getCell(1, index).getContents());
                    title.setText(korean_sheet.getCell(0, index).getContents());
                }
                title_textSize = 14;
                title.setTextSize(TypedValue.COMPLEX_UNIT_PT, title_textSize);
                textSize_control.setProgress(17, true);
                side1_Content.setTextSize(TypedValue.COMPLEX_UNIT_PT, textSize_control.getProgress());

                current_side_num = 1;
                editor.putInt("side_mode", current_side_num);
                editor.apply();
                side1Button.setBackgroundColor(Color.parseColor(strongThemeColor));
                side2Button.setBackgroundColor(Color.parseColor(defaultThemeColor));
                side3Button.setBackgroundColor(Color.parseColor(defaultThemeColor));
                getInitialBookmark();
            }
        });

        side2Button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if (!isKorean) {
                    if (index + 1 <= MAX_ROW_EXCEL) {
                        memo_OnOff.setVisibility(View.INVISIBLE);
                        bookmarking.setVisibility(View.INVISIBLE);
                        side1_layout.setVisibility(View.GONE);
                        side2_layout.setVisibility(View.VISIBLE);
                        side3_layout.setVisibility(View.GONE);
                        textSize_control.setProgress(15, true);
                        title_textSize = 13;
                        title.setTextSize(TypedValue.COMPLEX_UNIT_PT, title_textSize);

                        String splitStr1 = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1).getContents());
                        title.setText(splitStr1);

                        setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 2, "left", index);
                        side2_Content.setTextSize(TypedValue.COMPLEX_UNIT_PT, textSize_control.getProgress());

                        current_side_num = 2;
                        editor.putInt("side_mode", current_side_num);
                        editor.apply();
                    } else {
                        showToast("마지막 절입니다!");
                    }
                } else {
                    if (index + 1 <= MAX_ROW_EXCEL) {
                        memo_OnOff.setVisibility(View.INVISIBLE);
                        bookmarking.setVisibility(View.INVISIBLE);
                        side1_layout.setVisibility(View.GONE);
                        side2_layout.setVisibility(View.VISIBLE);
                        side3_layout.setVisibility(View.GONE);
                        textSize_control.setProgress(15, true);
                        title_textSize = 13;

                        title.setTextSize(TypedValue.COMPLEX_UNIT_PT, title_textSize);
                        title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1]);

                        side2_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents());
                        side2_Content.setTextSize(TypedValue.COMPLEX_UNIT_PT, textSize_control.getProgress());

                        current_side_num = 2;

                        editor.putInt("side_mode", current_side_num);
                        editor.apply();
                    } else {
                        showToast("마지막 절입니다!");
                    }
                }
                side1Button.setBackgroundColor(Color.parseColor(defaultThemeColor));
                side2Button.setBackgroundColor(Color.parseColor(strongThemeColor));
                side3Button.setBackgroundColor(Color.parseColor(defaultThemeColor));
            }
        });

        side3Button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if (!isKorean) {
                    if (index + 2 <= MAX_ROW_EXCEL) {
                        memo_OnOff.setVisibility(View.INVISIBLE);
                        bookmarking.setVisibility(View.INVISIBLE);
                        side1_layout.setVisibility(View.GONE);
                        side2_layout.setVisibility(View.GONE);
                        side3_layout.setVisibility(View.VISIBLE);
                        textSize_control.setProgress(13, true);

                        current_side_num = 3;
                        editor.putInt("side_mode", current_side_num);
                        editor.apply();

                        title_textSize = 11;
                        title.setTextSize(TypedValue.COMPLEX_UNIT_PT, title_textSize);
                        String splitStr2 = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                                .getContents()) + "," + splitTitle(eng_sheet.getCell(0, index + 2).getContents());
                        title.setText(splitStr2);

                        setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 3, "left", index);

                    } else {
                        showToast("마지막 절입니다!");
                    }
                } else {
                    if (index + 2 <= MAX_ROW_EXCEL) {
                        memo_OnOff.setVisibility(View.INVISIBLE);
                        bookmarking.setVisibility(View.INVISIBLE);
                        side1_layout.setVisibility(View.GONE);
                        side2_layout.setVisibility(View.GONE);
                        side3_layout.setVisibility(View.VISIBLE);
                        textSize_control.setProgress(13, true);

                        title_textSize = 11;

                        title.setTextSize(TypedValue.COMPLEX_UNIT_PT, title_textSize);
                        title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1] + "," +
                                korean_sheet.getCell(0, index + 2).getContents().split(" ")[1]);

                        side3_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents()
                                + "\n\n" + korean_sheet.getCell(1, index + 2).getContents());
                        side3_Content.setTextSize(TypedValue.COMPLEX_UNIT_PT, textSize_control.getProgress());

                        current_side_num = 3;
                        editor.putInt("side_mode", current_side_num);
                        editor.apply();
                    } else {
                        showToast("마지막 절입니다!");
                    }
                }
                side1Button.setBackgroundColor(Color.parseColor(defaultThemeColor));
                side2Button.setBackgroundColor(Color.parseColor(defaultThemeColor));
                side3Button.setBackgroundColor(Color.parseColor(strongThemeColor));
            }
        });

        /*-----------------------------------------------------------------------------------*/
        side1_entire.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

        side2_entire.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

        side3_entire.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

        /* moving to MainMenu */
        goto_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainMenuActivity.class);
                startActivity(intent);
            }
        });

        /*-------------------------------------------------------- Side Bar Thread */
        thread = new NaviBarThread();
        thread.start();
    }

    public void menuBackEvent() {
        outer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInnerSheet) {
                    if (isKorean) {
                        getString_mid_ListView(korean_bible_titleList[mid_index]);
                        midListView.setAdapter(sideMidMenuAdapter);
                    } else {
                        getString_mid_ListView(bible_titleList[mid_index]);
                        midListView.setAdapter(sideMidMenuAdapter);
                    }
                    isInnerSheet = false;
                    isMidSheet = true;
                    isOuterSheet = false;
                    loadingMenu.setVisibility(View.VISIBLE);
                    innerListView.setVisibility(View.GONE);
                    outerListView.setVisibility(View.GONE);
                    language_switch.setVisibility(View.VISIBLE);
                } else if (isMidSheet) {
                    if (isKorean) {
                        setOuterAdapter(korean_bible_titleList);
                        outerListView.setAdapter(sideOuterMenuAdapter);
                    } else {
                        setOuterAdapter(bible_titleList);
                        outerListView.setAdapter(sideOuterMenuAdapter);
                    }
                    isOuterSheet = true;
                    isInnerSheet = false;
                    isMidSheet = false;
                    outerListView.setVisibility(View.VISIBLE);
                    innerListView.setVisibility(View.GONE);
                    midListView.setVisibility(View.GONE);
                    outer_btn.setVisibility(View.INVISIBLE);
                    language_switch.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    public void memoQuitEvent() {
        quit_memo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout memo_layout = findViewById(R.id.memo_layout);
                if (memo_layout.getVisibility() == View.VISIBLE) {
                    memo_layout.setVisibility(View.GONE);
                    isOpen = false;
                    bottom_menu.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void memoOnOffEvent() {
        memo_OnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout memo_layout = findViewById(R.id.memo_layout);
                if (!isOpen) {
                    memo_layout.setVisibility(View.VISIBLE);
                    bottom_menu.setVisibility(View.GONE);
                    isOpen = true;
                } else {
                    memo_layout.setVisibility(View.GONE);
                    bottom_menu.setVisibility(View.VISIBLE);
                    isOpen = false;
                }
            }
        });
    }

    public void playMusicEvent() {
        play_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPlayed) {
                    play_music.setBackgroundResource(R.drawable.pause_icon);
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    isPlayed = true;
                } else {
                    play_music.setBackgroundResource(R.drawable.play_icon);
                    mediaPlayer.pause();
                    isPlayed = false;
                }
            }
        });
    }

    public void getInitialBookmark() {
        Cursor curs = realBMDBHelper.readIDRecord(index);
        if (curs.getCount() > 0) {
            bookmarking.setBackgroundResource(R.drawable.filled_bookmark); //chk
        } else {
            bookmarking.setBackgroundResource(R.drawable.bookmark_icon); //chk
        }
    }

    public void initializeAdapterObj() {
        sideMenuAdapter = new SideMenuAdapter();
        sideMidMenuAdapter = new SideMidMenuAdapter();
        sideOuterMenuAdapter = new SideOuterMenuAdapter();
    }

    public void initialExcelFile() {
        excel_thread = new ReadExcelThread();
        excel_thread.start();
        try {
            excel_thread.join();
        } catch (InterruptedException e) {
            Log.e("Error", e.getMessage());
        }
        koreanExcelThread = new ReadKoreanExcelThread();
        koreanExcelThread.start();
        try {
            koreanExcelThread.join();
        } catch (InterruptedException e) {
            Log.e("Error", e.getMessage());
        }

    }

    public void getInitialIndex() {
        try {
            Cursor cursor = dbHelper.readRecord();
            cursor.moveToFirst();
            index = cursor.getInt(cursor.getColumnIndex("CURRENT"));
        } catch (Exception e) {
            index = 1;  // When occur index problem, reinitialize line
        }
        if (index > MAX_ROW_EXCEL) index = 1; // when index exceed limit
    }

    public void initializeDatabaseObj() {
        dbHelper = new IndexDBHelper(this); // 현재 성경위치 대한 인덱스 데이터베이스
        memoDBHelper = new MemoDBHelper(this); // 각 절마다의 적은 메모 데이터베이스
        bmDBHelper = new ReadDBHelper(this); // 읽었는지 확인하는 데이터베이스
        realBMDBHelper = new BookMarkDBHelper(this); // 즐겨찾기한 항목 데이터베이스
        dateDBHelper = new DateDBHelper(this); // 즐겨찾기한 날짜 데이터베이스
    }

    public void initializeMemberVar() {
        goto_main = findViewById(R.id.go_mainmenu);
        memo_store = findViewById(R.id.memo_store);
        memo_content = findViewById(R.id.memo_space);//Edit Text

        language_switch = findViewById(R.id.language_switch);

        side1_entire = findViewById(R.id.side1_entire);
        side2_entire = findViewById(R.id.side2_entire);
        side3_entire = findViewById(R.id.side3_entire);

        bookmarking = findViewById(R.id.bookmark);
        side1_Content = findViewById(R.id.side1_text);
        title = findViewById(R.id.title_content);
        bottom_menu = findViewById(R.id.bottom_menu);
        top_menu = findViewById(R.id.top_menu_layout);
        bottom_center_menu = findViewById(R.id.bottom_center_menu);
        bottom_right_menu = findViewById(R.id.bottom_right_menu);
        memo_OnOff = findViewById(R.id.btn1); // turn on/off the "MEMO space" through button
        quit_memo_btn = findViewById(R.id.quit_memo);
        backgroundGoMain = findViewById(R.id.background_go_main);

        outerListView = findViewById(R.id.list_excel);

        outer_btn = findViewById(R.id.outer_btn);
        side1_layout = findViewById(R.id.side1_layout);

        innerListView = findViewById(R.id.inner_excel);
        midListView = findViewById(R.id.mid_excel);

        side2_layout = findViewById(R.id.side2_layout);
        side2_Content = findViewById(R.id.side2_text);

        side3_layout = findViewById(R.id.side3_layout);
        side3_Content = findViewById(R.id.side3_text);
        above_content_layout = findViewById(R.id.above_content_layout);
        loadingMenu = findViewById(R.id.loading_scr_inMenu);

        textSize_control = findViewById(R.id.seekbar_textsize);

        play_music = findViewById(R.id.play_music);
        control_volume = findViewById(R.id.control_volume);

        side1Button = findViewById(R.id.side1_btn);
        side2Button = findViewById(R.id.side2_btn);
        side3Button = findViewById(R.id.side3_btn);
    }

    public void switchLanguage() {
        language_switch.setChecked(isKorean); //chk
        language_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isKorean = true;
                    showToast("한글 모드");
                    editor.putBoolean("language", true);
                    editor.apply();
                    ChangeLanguage(isKorean);
                    if (current_side_num == 1) {
                        side1_Content.setText(korean_sheet.getCell(1, index).getContents());
                        title.setText(korean_sheet.getCell(0, index).getContents());
                    } else if (current_side_num == 2) {
                        side2_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents());
                        title.setText(korean_sheet.getCell(0, index).getContents() + ", " + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1]);
                    } else if (current_side_num == 3) {
                        title.setText(korean_sheet.getCell(0, index).getContents() + ", " + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1] + ", " +
                                korean_sheet.getCell(0, index + 2).getContents().split(" ")[1]);
                        side3_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents()
                                + "\n\n" + korean_sheet.getCell(1, index + 2).getContents());
                    }
                } else {
                    isKorean = false;
                    showToast("영어 모드");
                    editor.putBoolean("language", false);
                    editor.apply();
                    ChangeLanguage(isKorean);
                    if (current_side_num == 1) {
                        setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 1, "left", index);
                        title.setText(eng_sheet.getCell(0, index).getContents());
                    } else if (current_side_num == 2) {
                        String splitStr2 = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                                .getContents());
                        title.setText(splitStr2);
                        setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 2, "left", index);
                    } else if (current_side_num == 3) {
                        String splitStr = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                                .getContents()) + "," + splitTitle(eng_sheet.getCell(0, index + 2).getContents());
                        title.setText(splitStr);
                        setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 3, "left", index);
                    }
                }
            }
        });
    }

    public void bookmarkingInDB() {
        bookmarking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor curr = realBMDBHelper.readIDRecord(index);
                if (curr.getCount() > 0) {//북마크 존재 o
                    showToast("즐겨찾기가 해제되었습니다.");
                    bookmarking.setBackgroundResource(R.drawable.bookmark_icon);
                    realBMDBHelper.deleteRecord(index);
                    dateDBHelper.deleteRecord(index);
                } else {//북마크 존재 x
                    bookmarking.setBackgroundResource(R.drawable.filled_bookmark);
                    realBMDBHelper.updateRecord(index);
                    showToast("즐겨찾기가 설정되었습니다.");
                    long now = System.currentTimeMillis();
                    Date mDate = new Date(now);
                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    String getTime = simpleDate.format(mDate);
                    dateDBHelper.insertRecord(index, getTime);
                }
            }
        });
    }

    public void storeMemoInDB() {
        /* 메모 저장 버튼 이벤트 part */
        memo_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    memoDBHelper.updateRecord(index, memo_content.getText().toString());
                    showToast("메모 저장성공");
                } catch (Exception e) {
                    memoDBHelper.insertRecord(index, memo_content.getText().toString());
                    showToast("메모 저장성공");
                }
            }
        });
    }
    /*----------------------------------------------------------------------------------------------------- onCreate */

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initialEVSetting() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        if (!pref.getString("ThemeColor", defaultThemeColor).equals(defaultThemeColor)) {
            top_menu.setBackgroundColor(Color.parseColor(pref.getString("ThemeColor", defaultThemeColor)));
            bottom_menu.setBackgroundColor(Color.parseColor(pref.getString("ThemeColor", defaultThemeColor)));

            GradientDrawable bottom_center = (GradientDrawable) bottom_center_menu.getBackground();
            bottom_center.setColor(Color.parseColor(pref.getString("ThemeColor", defaultThemeColor)));

            GradientDrawable bottom_right = (GradientDrawable) bottom_right_menu.getBackground();
            bottom_right.setColor(Color.parseColor(pref.getString("ThemeColor", defaultThemeColor)));

            GradientDrawable bottom_left = (GradientDrawable) backgroundGoMain.getBackground();
            bottom_left.setColor(Color.parseColor(pref.getString("ThemeColor", defaultThemeColor)));
        }
        if (pref.getInt("letterSpace", 0) != 0) {
            side1_Content.setLetterSpacing((float) pref.getInt("letterSpace", 0) / 10);
        }
        if (pref.getInt("lineSpace", 0) != 0) {
            side1_Content.setLineSpacing(0, (float) pref.getInt("lineSpace", 0) / 10 + 1);
            side2_Content.setLineSpacing(0, (float) pref.getInt("lineSpace", 0) / 10 + 1);
            side3_Content.setLineSpacing(0, (float) pref.getInt("lineSpace", 0) / 10 + 1);
        }
        if (!pref.getString("BackgroundColor", defaultThemeColor).equals(defaultThemeColor)) {
            above_content_layout.setBackgroundColor(Color.parseColor(pref.getString("BackgroundColor", defaultThemeColor)));
            side1_layout.setBackgroundColor(Color.parseColor(pref.getString("BackgroundColor", defaultThemeColor)));
            side2_layout.setBackgroundColor(Color.parseColor(pref.getString("BackgroundColor", defaultThemeColor)));
            side3_layout.setBackgroundColor(Color.parseColor(pref.getString("BackgroundColor", defaultThemeColor)));
        }
        if (!pref.getString("TextColor", defaultThemeColor).equals(defaultThemeColor)) {
            side1_Content.setTextColor(Color.parseColor(pref.getString("TextColor", defaultThemeColor)));
            side2_Content.setTextColor(Color.parseColor(pref.getString("TextColor", defaultThemeColor)));
            side3_Content.setTextColor(Color.parseColor(pref.getString("TextColor", defaultThemeColor)));
        }
        pref.registerOnSharedPreferenceChangeListener(listener);
    }

    /*---------------------------------------------------------------------------------------------------화면 터치 이벤트*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (side1_layout.getVisibility() == View.VISIBLE) {
                    bmDBHelper.updateRecord(index, "true");
                    if (!isKorean) {
                        if (index < MAX_ROW_EXCEL) {
                            if (extractSheetNum(eng_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(eng_sheet.getCell(0, index + 1).getContents())) {
                                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage(extractSheetTitle(eng_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(eng_sheet.getCell(0, index + 1).getContents()) + "장 입니다.");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.cancel();
                                    }
                                }, 1500);
                            }
                            index++;

                            dbHelper.updateRecord(index);
                            getInitialBookmark();
                            selectMEMODB();

                            setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 1, "left", index);
                            title.setText(eng_sheet.getCell(0, index).getContents());

                        } else {
                            showToast("마지막 절입니다!");
                        }
                    } else {
                        if (index < MAX_ROW_EXCEL) {
                            if (extractSheetNum(korean_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(korean_sheet.getCell(0, index + 1).getContents())) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage(extractSheetTitle(korean_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(korean_sheet.getCell(0, index + 1).getContents()) + "장 입니다.");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.dismiss();
                                    }
                                }, 1600);
                            }
                            index++;
                            dbHelper.updateRecord(index);
                            getInitialBookmark();
                            selectMEMODB();
                            side1_Content.setText(korean_sheet.getCell(1, index).getContents());
                            title.setText(korean_sheet.getCell(0, index).getContents());
                        } else {
                            showToast("마지막 절입니다!");
                        }
                    }
                } else if (side2_layout.getVisibility() == View.VISIBLE) {
                    bmDBHelper.updateRecord(index, "true");
                    bmDBHelper.updateRecord(index + 1, "true");
                    if (!isKorean) {
                        if (index < MAX_ROW_EXCEL) {
                            if (extractSheetNum(eng_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(eng_sheet.getCell(0, index + 2).getContents())) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage(extractSheetTitle(eng_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(eng_sheet.getCell(0, index + 2).getContents()) + "장 입니다.");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.dismiss();
                                    }
                                }, 1600);
                            }
                            index += 2;
                            String splitStr2 = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                                    .getContents());
                            title.setText(splitStr2);
                            setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 2, "left", index);
                        }
                    } else {
                        if (index < MAX_ROW_EXCEL) {
                            if (extractSheetNum(korean_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(korean_sheet.getCell(0, index + 2).getContents())) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage(extractSheetTitle(korean_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(korean_sheet.getCell(0, index + 2).getContents()) + "장 입니다.");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.dismiss();
                                    }
                                }, 1600);
                            }
                            index += 2;
                            title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1]);
                            side2_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents());
                        }
                    }
                } else if (side3_layout.getVisibility() == View.VISIBLE) {
                    bmDBHelper.updateRecord(index, "true");
                    bmDBHelper.updateRecord(index + 1, "true");
                    bmDBHelper.updateRecord(index + 2, "true");
                    if (!isKorean) {
                        if (index + 3 < MAX_ROW_EXCEL) {
                            if (extractSheetNum(eng_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(eng_sheet.getCell(0, index + 3).getContents())) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage(extractSheetTitle(eng_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(eng_sheet.getCell(0, index + 2).getContents()) + "장 입니다.");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.dismiss();
                                    }
                                }, 1600);
                            }
                            index += 3;
                            dbHelper.updateRecord(index);

                            String splitStr = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                                    .getContents()) + "," + splitTitle(eng_sheet.getCell(0, index + 2).getContents());
                            title.setText(splitStr);
                            setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 3, "left", index);
                        } else {
                            showToast("마지막 절입니다!");
                        }
                    } else {
                        if (index + 3 < MAX_ROW_EXCEL) {
                            if (extractSheetNum(korean_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(korean_sheet.getCell(0, index + 3).getContents())) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage(extractSheetTitle(korean_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(korean_sheet.getCell(0, index + 2).getContents()) + "장 입니다.");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.dismiss();
                                    }
                                }, 1600);
                            }
                            index += 3;
                            dbHelper.updateRecord(index);
                            title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1] + "," +
                                    korean_sheet.getCell(0, index + 2).getContents().split(" ")[1]);
                            side3_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents()
                                    + "\n\n" + korean_sheet.getCell(1, index + 2).getContents());
                        } else {
                            showToast("마지막 절입니다!");
                        }
                    }
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (side1_layout.getVisibility() == View.VISIBLE) {
                    if (index > 1) {
                        if (!isKorean) {
                            index -= 1; // 위의 src에서 gesture.onTouch 이벤트와 일반 터치 이벤트가 겹쳐서 인덱스가 하나 줄으면 다시 늘어서 하나 줄이는 효과로 2를 줄임
                            title.setText(eng_sheet.getCell(0, index).getContents());
                            setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 1, "left", index);
                            dbHelper.updateRecord(index);
                            getInitialBookmark();
                            selectMEMODB();
                            Cursor cursor = bmDBHelper.readIDRecord(index + 1);
                            if (cursor.getCount() > 0) {
                                bmDBHelper.deleteRecord(index + 1);
                            }
                        } else {
                            index -= 1; // 위의 src에서 gesture.onTouch 이벤트와 일반 터치 이벤트가 겹쳐서 인덱스가 하나 줄으면 다시 늘어서 하나 줄이는 효과로 2를 줄임
                            title.setText(korean_sheet.getCell(0, index).getContents());
                            side1_Content.setText(korean_sheet.getCell(1, index).getContents());
                            dbHelper.updateRecord(index);
                            getInitialBookmark();
                            selectMEMODB();
                            Cursor cursor = bmDBHelper.readIDRecord(index + 1);
                            if (cursor.getCount() > 0) {
                                bmDBHelper.deleteRecord(index + 1);
                            }
                        }
                    } else if (index == 1) {
                        showToast("제일 첫 구절입니다.");
                        Cursor cursor = bmDBHelper.readIDRecord(index);
                        if (cursor.getCount() > 0) {
                            bmDBHelper.deleteRecord(index);
                        }
                        selectMEMODB();
                    }
                } else if (side2_layout.getVisibility() == View.VISIBLE) {
                    if (index > 1) {
                        index -= 2;
                        dbHelper.updateRecord(index);
                        Cursor cursor = bmDBHelper.readIDRecord(index + 2);
                        if (cursor.getCount() > 0) {
                            bmDBHelper.deleteRecord(index + 2);
                        }
                        cursor = bmDBHelper.readIDRecord(index + 1);
                        if (cursor.getCount() > 0) {
                            bmDBHelper.deleteRecord(index + 1);
                        }
                        if (!isKorean) {
                            String splitStr2 = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                                    .getContents());
                            title.setText(splitStr2);
                            setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 2, "left", index);
                        } else {
                            title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1]);
                            side2_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents());
                        }
                    } else if (index == 1) {
                        showToast("제일 첫 구절입니다.");
                        Cursor cursor = bmDBHelper.readIDRecord(index + 1);
                        if (cursor.getCount() > 0) {
                            bmDBHelper.deleteRecord(index + 1);
                        }
                        cursor = bmDBHelper.readIDRecord(index);
                        if (cursor.getCount() > 0) {
                            bmDBHelper.deleteRecord(index);
                        }
                    }
                } else if (side3_layout.getVisibility() == View.VISIBLE) {
                    if (index > 1) {
                        index -= 3;
                        dbHelper.updateRecord(index);
                        Cursor cursor;
                        for (int i = 3; i > 0; i--) {
                            cursor = bmDBHelper.readIDRecord(index + i);
                            if (cursor.getCount() > 0) {
                                bmDBHelper.deleteRecord(index + i);
                            }
                            if (!isKorean) {
                                String splitStr = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                                        .getContents()) + "," + splitTitle(eng_sheet.getCell(0, index + 2).getContents());
                                title.setText(splitStr);
                                setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 3, "left", index);
                            } else {
                                title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1] + "," +
                                        korean_sheet.getCell(0, index + 2).getContents().split(" ")[1]);
                                side3_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents()
                                        + "\n\n" + korean_sheet.getCell(1, index + 2).getContents());
                            }
                        }
                    } else if (index == 1) {
                        showToast("제일 첫 구절입니다.");
                        Cursor cursor;
                        for (int i = 2; i >= 0; i--) {
                            cursor = bmDBHelper.readIDRecord(index + i);
                            if (cursor.getCount() > 0) {
                                bmDBHelper.deleteRecord(index + i);
                            }
                        }
                    }
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
    onDown (터치),
    onShowPress (onDown 보다 길게 터치),
    onSingleTapUp (터치가 끝날 때),
    onLongPress (onShowPress보다 길게 터치),
    onScroll(스크롤),
    onFling (스크롤과 비슷하지만 손가락으로 튕길 때)
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (side1_layout.getVisibility() == View.VISIBLE) {
            bmDBHelper.updateRecord(index, "true");
            if (!isKorean) {
                if (index < MAX_ROW_EXCEL) {
                    if (extractSheetNum(eng_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(eng_sheet.getCell(0, index + 1).getContents())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage(extractSheetTitle(eng_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(eng_sheet.getCell(0, index + 1).getContents()) + "장 입니다.");
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                alertDialog.dismiss();
                            }
                        }, 1600);

                    }
                    index++;
                    dbHelper.updateRecord(index);
                    getInitialBookmark();
                    selectMEMODB();
                    setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 1, "left", index);
                    title.setText(eng_sheet.getCell(0, index).getContents());
                } else {
                    showToast("마지막 절입니다!");
                }
            } else {
                if (index < 31101) {
                    if (extractSheetNum(korean_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(korean_sheet.getCell(0, index + 1).getContents())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage(extractSheetTitle(korean_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(korean_sheet.getCell(0, index + 1).getContents()) + "장 입니다.");
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                alertDialog.dismiss();
                            }
                        }, 1600);
                    }
                    index++;
                    dbHelper.updateRecord(index);
                    getInitialBookmark();
                    selectMEMODB();
                    side1_Content.setText(korean_sheet.getCell(1, index).getContents());
                    title.setText(korean_sheet.getCell(0, index).getContents());
                } else {
                    showToast("마지막 절입니다!");
                }
            }
        } else if (side2_layout.getVisibility() == View.VISIBLE) {
            bmDBHelper.updateRecord(index, "true");
            bmDBHelper.updateRecord(index + 1, "true");
            if (!isKorean) {
                if (index + 2 < MAX_ROW_EXCEL) {
                    if (extractSheetNum(eng_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(eng_sheet.getCell(0, index + 2).getContents())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage(extractSheetTitle(eng_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(eng_sheet.getCell(0, index + 2).getContents()) + "장 입니다.");
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                alertDialog.dismiss();
                            }
                        }, 1600);
                    }
                    index += 2;
                    dbHelper.updateRecord(index);
                    String splitStr = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1).getContents());
                    title.setText(splitStr);
                    setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 2, "left", index);
                } else {
                    showToast("마지막 절입니다!");
                }
            } else {
                if (index + 2 < MAX_ROW_EXCEL) {
                    if (extractSheetNum(korean_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(korean_sheet.getCell(0, index + 2).getContents())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage(extractSheetTitle(korean_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(korean_sheet.getCell(0, index + 2).getContents()) + "장 입니다.");
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                alertDialog.dismiss();
                            }
                        }, 1600);
                    }
                    index += 2;
                    dbHelper.updateRecord(index);
                    title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1]);
                    side2_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents());
                } else {
                    showToast("마지막 절입니다!");
                }
            }
        } else if (side3_layout.getVisibility() == View.VISIBLE) {
            bmDBHelper.updateRecord(index, "true");
            bmDBHelper.updateRecord(index + 1, "true");
            bmDBHelper.updateRecord(index + 2, "true");
            if (!isKorean) {
                if (index + 3 < MAX_ROW_EXCEL) {
                    if (extractSheetNum(eng_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(eng_sheet.getCell(0, index + 3).getContents())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage(extractSheetTitle(eng_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(eng_sheet.getCell(0, index + 2).getContents()) + "장 입니다.");
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                alertDialog.dismiss();
                            }
                        }, 1600);
                    }
                    index += 3;
                    dbHelper.updateRecord(index);
                    String splitStr = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                            .getContents()) + "," + splitTitle(eng_sheet.getCell(0, index + 2).getContents());
                    title.setText(splitStr);
                    setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 3, "left", index);
                } else {
                    showToast("마지막 절입니다!");
                }
            } else {
                if (index + 3 < MAX_ROW_EXCEL) {
                    if (extractSheetNum(korean_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(korean_sheet.getCell(0, index + 3).getContents())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage(extractSheetTitle(korean_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(korean_sheet.getCell(0, index + 2).getContents()) + "장 입니다.");
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                alertDialog.dismiss();
                            }
                        }, 1600);
                    }
                    index += 3;
                    dbHelper.updateRecord(index);
                    title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1] + "," +
                            korean_sheet.getCell(0, index + 2).getContents().split(" ")[1]);
                    side3_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents()
                            + "\n\n" + korean_sheet.getCell(1, index + 2).getContents());
                } else {
                    showToast("마지막 절입니다!");
                }
            }
        }
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        try {
            if (Math.abs(motionEvent.getY() - motionEvent1.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            // right to left swipe   // 다음 화면으로 이동
            if (motionEvent.getX() - motionEvent1.getX() > SWIPE_MIN_DISTANCE && Math.abs(v) > SWIPE_THRESHOLD_VELOCITY) {
                if (side1_layout.getVisibility() == View.VISIBLE) {
                    bmDBHelper.updateRecord(index, "true");
                    if (!isKorean) {
                        if (index < MAX_ROW_EXCEL) {
                            if (extractSheetNum(eng_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(eng_sheet.getCell(0, index + 1).getContents())) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage(extractSheetTitle(eng_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(eng_sheet.getCell(0, index + 1).getContents()) + "장 입니다.");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.dismiss();
                                    }
                                }, 1600);
                            }
                            index++;
                            dbHelper.updateRecord(index);
                            getInitialBookmark();
                            selectMEMODB();
                            setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 1, "left", index);
                            title.setText(eng_sheet.getCell(0, index).getContents());
                        } else {
                            showToast("마지막 절입니다!");
                        }
                    } else {
                        if (index < MAX_ROW_EXCEL) {
                            if (extractSheetNum(korean_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(korean_sheet.getCell(0, index + 1).getContents())) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage(extractSheetTitle(korean_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(korean_sheet.getCell(0, index + 1).getContents()) + "장 입니다.");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.dismiss();
                                    }
                                }, 1600);
                            }
                            index++;
                            dbHelper.updateRecord(index);
                            getInitialBookmark();
                            selectMEMODB();
                            side1_Content.setText(korean_sheet.getCell(1, index).getContents());
                            title.setText(korean_sheet.getCell(0, index).getContents());
                        } else {
                            showToast("마지막 절입니다!");
                        }
                    }
                } else if (side2_layout.getVisibility() == View.VISIBLE) {

                    bmDBHelper.updateRecord(index, "true");
                    bmDBHelper.updateRecord(index + 1, "true");
                    if (!isKorean) {
                        if (index < MAX_ROW_EXCEL) {
                            if (extractSheetNum(eng_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(eng_sheet.getCell(0, index + 2).getContents())) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage(extractSheetTitle(eng_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(eng_sheet.getCell(0, index + 2).getContents()) + "장 입니다.");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.dismiss();
                                    }
                                }, 1600);
                            }
                            index += 2;
                            String splitStr2 = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                                    .getContents());
                            title.setText(splitStr2);
                            setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 2, "left", index);
                        }
                    } else {
                        if (index < MAX_ROW_EXCEL) {
                            if (extractSheetNum(korean_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(korean_sheet.getCell(0, index + 2).getContents())) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage(extractSheetTitle(korean_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(korean_sheet.getCell(0, index + 2).getContents()) + "장 입니다.");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.dismiss();
                                    }
                                }, 1600);
                            }
                            index += 2;
                            title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1]);
                            side2_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents());
                        }
                    }
                } else if (side3_layout.getVisibility() == View.VISIBLE) {
                    bmDBHelper.updateRecord(index, "true");
                    bmDBHelper.updateRecord(index + 1, "true");
                    bmDBHelper.updateRecord(index + 2, "true");
                    if (!isKorean) {
                        if (index + 3 < MAX_ROW_EXCEL) {
                            if (extractSheetNum(eng_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(eng_sheet.getCell(0, index + 3).getContents())) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage(extractSheetTitle(eng_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(eng_sheet.getCell(0, index + 2).getContents()) + "장 입니다.");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.dismiss();
                                    }
                                }, 1600);
                            }
                            index += 3;
                            dbHelper.updateRecord(index);

                            String splitStr = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                                    .getContents()) + "," + splitTitle(eng_sheet.getCell(0, index + 2).getContents());
                            title.setText(splitStr);

                            setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 3, "left", index);
                        } else {
                            showToast("마지막 절입니다!");
                        }
                    } else {
                        if (index + 3 < MAX_ROW_EXCEL) {
                            if (extractSheetNum(korean_sheet.getCell(0, index).getContents()) + 1 == extractSheetNum(korean_sheet.getCell(0, index + 3).getContents())) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage(extractSheetTitle(korean_sheet.getCell(0, index + 1).getContents()) + " " + extractSheetNum(korean_sheet.getCell(0, index + 2).getContents()) + "장 입니다.");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertDialog.dismiss();
                                    }
                                }, 1600);
                            }
                            index += 3;
                            dbHelper.updateRecord(index);
                            title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1] + "," +
                                    korean_sheet.getCell(0, index + 2).getContents().split(" ")[1]);
                            side3_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents()
                                    + "\n\n" + korean_sheet.getCell(1, index + 2).getContents());
                        } else {
                            showToast("마지막 절입니다!");
                        }
                    }
                }
            }
            /*--------------------------------------------------------------------------------------*/
            // ★left to right swipe  // 이전 화면으로 이동
            else if (motionEvent1.getX() - motionEvent.getX() > SWIPE_MIN_DISTANCE && Math.abs(v) > SWIPE_THRESHOLD_VELOCITY) {
                if (side1_layout.getVisibility() == View.VISIBLE) {
                    if (index > 1) {
                        if (!isKorean) {
                            index -= 1; // 위의 src에서 gesture.onTouch 이벤트와 일반 터치 이벤트가 겹쳐서 인덱스가 하나 줄으면 다시 늘어서 하나 줄이는 효과로 2를 줄임
                            title.setText(eng_sheet.getCell(0, index).getContents());
                            setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 1, "left", index);
                            dbHelper.updateRecord(index);
                            getInitialBookmark();
                            Cursor cursor = bmDBHelper.readIDRecord(index + 1);
                            if (cursor.getCount() > 0) {
                                bmDBHelper.deleteRecord(index + 1);
                            }
                            selectMEMODB();
                        } else {
                            index -= 1; // 위의 src에서 gesture.onTouch 이벤트와 일반 터치 이벤트가 겹쳐서 인덱스가 하나 줄으면 다시 늘어서 하나 줄이는 효과로 2를 줄임
                            title.setText(korean_sheet.getCell(0, index).getContents());
                            side1_Content.setText(korean_sheet.getCell(1, index).getContents());
                            dbHelper.updateRecord(index);
                            getInitialBookmark();
                            Cursor cursor = bmDBHelper.readIDRecord(index + 1);
                            if (cursor.getCount() > 0) {
                                bmDBHelper.deleteRecord(index + 1);
                            }
                            selectMEMODB();
                        }
                    } else if (index == 1) {
                        showToast("제일 첫 구절입니다.");
                        Cursor cursor = bmDBHelper.readIDRecord(index);
                        if (cursor.getCount() > 0) {
                            bmDBHelper.deleteRecord(index);
                        }
                        selectMEMODB();
                    }
                } else if (side2_layout.getVisibility() == View.VISIBLE) {
                    if (index > 1) {
                        index -= 2;
                        dbHelper.updateRecord(index);
                        Cursor cursor = bmDBHelper.readIDRecord(index + 2);
                        if (cursor.getCount() > 0) {
                            bmDBHelper.deleteRecord(index + 2);
                        }
                        cursor = bmDBHelper.readIDRecord(index + 1);
                        if (cursor.getCount() > 0) {
                            bmDBHelper.deleteRecord(index + 1);
                        }
                        if (!isKorean) {
                            String splitStr2 = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                                    .getContents());
                            title.setText(splitStr2);
                            setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 2, "left", index);
                        } else {
                            title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1]);
                            side2_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents());
                        }
                    } else if (index == 1) {
                        showToast("제일 첫 구절입니다.");
                        Cursor cursor = bmDBHelper.readIDRecord(index + 1);
                        if (cursor.getCount() > 0) {
                            bmDBHelper.deleteRecord(index + 1);
                        }
                        cursor = bmDBHelper.readIDRecord(index);
                        if (cursor.getCount() > 0) {
                            bmDBHelper.deleteRecord(index);
                        }
                    }
                } else if (side3_layout.getVisibility() == View.VISIBLE) {
                    if (index > 1) {
                        index -= 3;
                        dbHelper.updateRecord(index);
                        Cursor cursor;
                        for (int i = 3; i > 0; i--) {
                            cursor = bmDBHelper.readIDRecord(index + i);
                            if (cursor.getCount() > 0) {
                                bmDBHelper.deleteRecord(index + i);
                            }
                        }
                        if (!isKorean) {
                            String splitStr = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                                    .getContents()) + "," + splitTitle(eng_sheet.getCell(0, index + 2).getContents());
                            title.setText(splitStr);
                            setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 3, "left", index);
                        } else {
                            title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1] + "," +
                                    korean_sheet.getCell(0, index + 2).getContents().split(" ")[1]);
                            side3_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents()
                                    + "\n\n" + korean_sheet.getCell(1, index + 2).getContents());
                        }

                    } else if (index == 1) {
                        showToast("제일 첫 구절입니다.");
                        Cursor cursor;
                        for (int i = 2; i >= 0; i--) {
                            cursor = bmDBHelper.readIDRecord(index + i);
                            if (cursor.getCount() > 0) {
                                bmDBHelper.deleteRecord(index + i);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return true;
    }

    /*------------------------------------------------------------------------------------------------------------- onCreate()*/
    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            switch (s) {
                case "ThemeColor":
                    top_menu.setBackgroundColor(Color.parseColor(pref.getString("ThemeColor", defaultThemeColor)));
                    bottom_menu.setBackgroundColor(Color.parseColor(pref.getString("ThemeColor", defaultThemeColor)));

                    GradientDrawable bottom_center = (GradientDrawable) bottom_center_menu.getBackground();
                    bottom_center.setColor(Color.parseColor(pref.getString("ThemeColor", defaultThemeColor)));

                    GradientDrawable bottom_right = (GradientDrawable) bottom_right_menu.getBackground();
                    bottom_right.setColor(Color.parseColor(pref.getString("ThemeColor", defaultThemeColor)));

                    GradientDrawable bottom_left = (GradientDrawable) backgroundGoMain.getBackground();
                    bottom_left.setColor(Color.parseColor(pref.getString("ThemeColor", defaultThemeColor)));
                    break;
                case "letterSpace": {
                    side1_Content.setLetterSpacing((float) pref.getInt("letterSpace", 0) / 10);
                    break;
                }
                case "lineSpace": {
                    side1_Content.setLineSpacing(0, (float) pref.getInt("lineSpace", 0) / 10 + 1);
                    side2_Content.setLineSpacing(0, (float) pref.getInt("lineSpace", 0) / 10 + 1);
                    side3_Content.setLineSpacing(0, (float) pref.getInt("lineSpace", 0) / 10 + 1);
                    break;
                }
                case "BackgroundColor":
                    above_content_layout.setBackgroundColor(Color.parseColor(pref.getString("BackgroundColor", defaultThemeColor)));
                    side1_layout.setBackgroundColor(Color.parseColor(pref.getString("BackgroundColor", defaultThemeColor)));
                    side2_layout.setBackgroundColor(Color.parseColor(pref.getString("BackgroundColor", defaultThemeColor)));
                    side3_layout.setBackgroundColor(Color.parseColor(pref.getString("BackgroundColor", defaultThemeColor)));
                    break;
                case "TextColor":
                    side1_Content.setTextColor(Color.parseColor(pref.getString("TextColor", defaultThemeColor)));
                    side2_Content.setTextColor(Color.parseColor(pref.getString("TextColor", defaultThemeColor)));
                    side3_Content.setTextColor(Color.parseColor(pref.getString("TextColor", defaultThemeColor)));
                    break;
            }
        }
    };

    /*-------------------------------------------------------------------------------------------------SharedPreference Listener*/
    private class ReadExcelThread extends Thread {
        @Override
        public void run() {
            try {
                InputStream inputStream = getBaseContext().getResources().getAssets().open("bibles.xls");
                workbook = Workbook.getWorkbook(inputStream);
                eng_sheet = workbook.getSheet(0);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BiffException e) {
                e.printStackTrace();
            }
            // ... workbook.close(); 때문에 다 끊겼었나보다 뭐든지간에 close()는 모든 동작 끝난후에 하자
        }
    }

    /*----------------------------------------------------------------------------------------------------- ReadExcelThread() */
    private class ReadKoreanExcelThread extends Thread {
        @Override
        public void run() {
            try {
                InputStream inputStream = getBaseContext().getResources().getAssets().open("korean_bibles.xls");
                korean_workbook = Workbook.getWorkbook(inputStream);
                korean_sheet = korean_workbook.getSheet(0);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BiffException e) {
                e.printStackTrace();
            }
        }
    }

    /*--------------------------------------------------------------------------------------------------------- ReadKoreanExcelThread()*/
    public void showToast(String charSequence) {
        Toast.makeText(this, charSequence, Toast.LENGTH_SHORT).show();
    }

    /*----------------------------------------------------------------------------------------------------------------showToast()*/
    private class ListClickThread extends Thread {
        @Override
        public void run() {
            if (isOuterSheet) {
                outerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        isOuterSheet = false;
                        isInnerSheet = false;
                        isMidSheet = true;
                        if (isKorean) {
                            outer_str = korean_bible_titleList[i];
                        } else {
                            outer_str = bible_titleList[i];
                        }
                        mid_index = i;
                        outerListView.setVisibility(View.GONE);
                        innerListView.setVisibility(View.GONE);
                        loadingMenu.setVisibility(View.VISIBLE);
                        getString_mid_ListView(outer_str);
                        midListView.setAdapter(sideMidMenuAdapter);


                        outer_btn.setVisibility(View.VISIBLE);
                        if (isMidSheet) {
                            midListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    midListView.setVisibility(View.GONE);
                                    innerListView.setVisibility(View.VISIBLE);
                                    getString_Inner_ListView(sideMidMenuAdapter.getItem(i).getTitle());
                                    innerListView.setAdapter(sideMenuAdapter);
                                    mid_str = sideMidMenuAdapter.getItem(i).getTitle();
                                    isKorean = !isKorean;
                                    mid_reverse_str = reverse_Language(mid_str);
                                    isKorean = !isKorean;
                                    isInnerSheet = true;
                                    isMidSheet = false;
                                    isOuterSheet = false;
                                    outer_btn.setVisibility(View.VISIBLE);
                                    if (isInnerSheet) {
                                        innerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                memo_OnOff.setVisibility(View.VISIBLE);
                                                bookmarking.setVisibility(View.VISIBLE);
                                                if (current_side_num == 1) {
                                                    title_textSize = 14;
                                                } else if (current_side_num == 2) {
                                                    title_textSize = 13;
                                                } else if (current_side_num == 3) {
                                                    title_textSize = 11;
                                                }
                                                title.setTextSize(TypedValue.COMPLEX_UNIT_PT, title_textSize);
                                                if (!isKorean)
                                                    index = findIndex(sideMenuAdapter.getItem(i).getTitle());
                                                else
                                                    index = findKoreanIndex(sideMenuAdapter.getItem(i).getTitle());
                                                getInitialBookmark();
                                                dbHelper.updateRecord(index);
                                                selectMEMODB();
                                                drawerLayout.closeDrawers();
                                                if (current_side_num == 1) {
                                                    side1_layout.setVisibility(View.VISIBLE);
                                                    side2_layout.setVisibility(View.GONE);
                                                    side3_layout.setVisibility(View.GONE);
                                                    if (isKorean) {
                                                        side1_Content.setText(korean_sheet.getCell(1, index).getContents());
                                                        title.setText(korean_sheet.getCell(0, index).getContents());
                                                    } else {
                                                        setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 1, "left", index);
                                                        title.setText(eng_sheet.getCell(0, index).getContents());
                                                    }
                                                } else if (current_side_num == 2) {
                                                    side1_layout.setVisibility(View.GONE);
                                                    side2_layout.setVisibility(View.VISIBLE);
                                                    side3_layout.setVisibility(View.GONE);
                                                    if (isKorean) {
                                                        title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1]);
                                                        side2_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents());
                                                    } else {
                                                        String splitStr2 = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                                                                .getContents()) + "," + splitTitle(eng_sheet.getCell(0, index + 2).getContents());
                                                        title.setText(splitStr2);
                                                        setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 2, "left", index);
                                                    }
                                                } else if (current_side_num == 3) {
                                                    side1_layout.setVisibility(View.GONE);
                                                    side2_layout.setVisibility(View.GONE);
                                                    side3_layout.setVisibility(View.VISIBLE);
                                                    if (isKorean) {
                                                        title.setText(korean_sheet.getCell(0, index).getContents() + "," + korean_sheet.getCell(0, index + 1).getContents().split(" ")[1] + "," +
                                                                korean_sheet.getCell(0, index + 2).getContents().split(" ")[1]);
                                                        side3_Content.setText(korean_sheet.getCell(1, index).getContents() + "\n\n" + korean_sheet.getCell(1, index + 1).getContents()
                                                                + "\n\n" + korean_sheet.getCell(1, index + 2).getContents());
                                                    } else {
                                                        String splitStr = eng_sheet.getCell(0, index).getContents() + "," + splitTitle(eng_sheet.getCell(0, index + 1)
                                                                .getContents()) + "," + splitTitle(eng_sheet.getCell(0, index + 2).getContents());
                                                        title.setText(splitStr);
                                                        setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, index).getContents(), 3, "left", index);
                                                    }
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    /*---------------------------------------------------------------------------------------------------------- ListClickThread */
    public int findIndex(String str) {
        for (int i = 1; i <= MAX_ROW_EXCEL; ++i) {
            if (eng_sheet.getCell(0, i).getContents().equals(str)) {
                return i;
            }
        }
        return 0;
    }

    /*---------------------------------------------------------------------------------------------------------- findIndex() */
    public int findKoreanIndex(String str) {
        for (int i = 1; i <= MAX_ROW_EXCEL; ++i) {
            if (korean_sheet.getCell(0, i).getContents().equals(str)) {
                return i;
            }
        }
        return 0;
    }
    /*---------------------------------------------------------------------------------------------------------- findKoreanIndex() */

    private class NaviBarThread extends Thread {
        public NaviBarThread() {
            drawerLayout = findViewById(R.id.drawer_layout);
            drawerView = findViewById(R.id.drawer);
        }

        public void run() {
            ImageButton btn_open = findViewById(R.id.btn_open); //상단바만들고 ImageButton으로 바꿔도 됨
            btn_open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(drawerView);
                }
            });
            ImageButton btn_close = findViewById(R.id.btn_cancel);
            btn_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.closeDrawers();
                }
            });
        }
    }

    /*------------------------------------------------------------------------------------------------------- NaviBarThread() */
    public void selectMEMODB() {// search db (select in java cursor)
        Cursor cur = memoDBHelper.readRecord(index);
        String tmp = "";
        if (cur != null && cur.getCount() > 0) {
            if (cur.moveToFirst()) {
                do {
                    tmp = cur.getString(cur.getColumnIndex("SENTENCE"));
                } while (cur.moveToNext());
            }
        }
        memo_content.setText(tmp);
        cur.close();
    }

    /*------------------------------------------------------------------------------------------------------- selectMEMODB() */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTimeBackPressed < 1000) {
            finish();
            return;
        }
        if (drawerLayout.isDrawerOpen(drawerView)) {
            if (isInnerSheet) {
                if (isKorean) {
                    getString_mid_ListView(korean_bible_titleList[mid_index]);
                    midListView.setAdapter(sideMidMenuAdapter);
                } else {
                    getString_mid_ListView(bible_titleList[mid_index]);
                    midListView.setAdapter(sideMidMenuAdapter);
                }
                isInnerSheet = false;
                isMidSheet = true;
                isOuterSheet = false;
                loadingMenu.setVisibility(View.VISIBLE);
                innerListView.setVisibility(View.GONE);
                midListView.setVisibility(View.VISIBLE);
                outerListView.setVisibility(View.GONE);
            } else if (isMidSheet) {
                if (isKorean) {
                    setOuterAdapter(korean_bible_titleList);
                    outerListView.setAdapter(sideOuterMenuAdapter);
                } else {
                    setOuterAdapter(bible_titleList);
                    outerListView.setAdapter(sideOuterMenuAdapter);
                }
                isOuterSheet = true;
                isInnerSheet = false;
                isMidSheet = false;
                outerListView.setVisibility(View.VISIBLE);
                innerListView.setVisibility(View.GONE);
                midListView.setVisibility(View.GONE);
                outer_btn.setVisibility(View.INVISIBLE);
            } else if (isOuterSheet)
                drawerLayout.closeDrawers();
        } else {
            showToast("'뒤로'버튼을 한번 더 누르면 종료됩니다.");
            lastTimeBackPressed = System.currentTimeMillis();
        }
    }

    /*------------------------------------------------------------------------------------------------------- onBackPressed() */
    @Override
    protected void onDestroy() {
        dbHelper.close();
        memoDBHelper.close();
        bmDBHelper.close();
        realBMDBHelper.close();
        mediaPlayer.release();
        super.onDestroy();
    }

    /*-------------------------------------------------------------------------------------------------------- GoMainmenuThread */
    public String splitTitle(String whole) {
        if (whole.indexOf(" ") < 2) {
            String[] splString = whole.split(" ");
            return " " + splString[2];
        } else {
            return whole.substring(whole.indexOf(" "));
        }
    }

    /*-------------------------------------------------------------------------------------------------------- splitTile() */
    @Override
    protected void onStart() {
        super.onStart();
        getInitialBookmark();
    }

    /*------------------------------------------------------------------------------------------------------- onStart() */
    public void getString_mid_ListView(final String titleName) {
        sideMidMenuAdapter.clearItem();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!isKorean) {
                    switch (titleName) {
                        case "Genesis":
                            for (int i = 1; i <= 50; ++i) {
                                sheetNumList.GetGenesisVerse("Genesis " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Genesis " + i)) {
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Genesis " + i);
                                } else {
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Genesis " + i);
                                }
                            }
                            break;
                        case "Exodus":
                            for (int i = 1; i <= 40; ++i) {
                                sheetNumList.GetExodusVerse("Exodus " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Exodus " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Exodus " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Exodus " + i);
                            }
                            break;
                        case "Leviticus":
                            for (int i = 1; i <= 27; ++i) {
                                sheetNumList.GetLeviticusVerse("Leviticus " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Leviticus " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Leviticus " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Leviticus " + i);
                            }
                            break;
                        case "Numbers":
                            for (int i = 1; i <= 36; ++i) {
                                sheetNumList.GetNumbersVerse("Numbers " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Numbers " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Numbers " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Numbers " + i);
                            }
                            break;
                        case "Deuteronomy":
                            for (int i = 1; i <= 34; ++i) {
                                sheetNumList.GetDeuteronomyVerse("Deuteronomy " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Deuteronomy " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Deuteronomy " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Deuteronomy " + i);
                            }
                            break;
                        case "Joshua":
                            for (int i = 1; i <= 24; ++i) {
                                sheetNumList.GetJoshuaVerse("Joshua " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Joshua " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Joshua " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Joshua " + i);
                            }
                            break;
                        case "Judges":
                            for (int i = 1; i <= 21; ++i) {
                                sheetNumList.GetJudgesVerse("Judges " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Judges " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Judges " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Judges " + i);
                            }
                            break;
                        case "Ruth":
                            for (int i = 1; i <= 4; ++i) {
                                sheetNumList.GetRuthVerse("Ruth " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Ruth " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Ruth " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Ruth " + i);
                            }
                            break;
                        case "1 Samuel":
                            for (int i = 1; i <= 31; ++i) {
                                sheetNumList.GetSamuel1Verse("1 Samuel " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("1 Samuel " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "1 Samuel " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "1 Samuel " + i);
                            }
                            break;
                        case "2 Samuel":
                            for (int i = 1; i <= 24; ++i) {
                                sheetNumList.GetSamuel2Verse("2 Samuel " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("2 Samuel " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "2 Samuel " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "2 Samuel " + i);
                            }
                            break;
                        case "1 Kings":
                            for (int i = 1; i <= 22; ++i) {
                                sheetNumList.GetKing1Verse("1 Kings " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("1 Kings " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "1 Kings " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "1 Kings " + i);
                            }
                            break;
                        case "2 Kings":
                            for (int i = 1; i <= 25; ++i) {
                                sheetNumList.GetKing2Verse("2 Kings " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("2 Kings " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "2 Kings " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "2 Kings " + i);
                            }
                            break;
                        case "1 Chronicles":
                            for (int i = 1; i <= 29; ++i) {
                                sheetNumList.GetChronicles1Verse("1 Chronicles " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("1 Chronicles " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "1 Chronicles " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "1 Chronicles " + i);
                            }
                            break;
                        case "2 Chronicles":
                            for (int i = 1; i <= 36; ++i) {
                                sheetNumList.GetChronicles2Verse("2 Chronicles " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("2 Chronicles " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "2 Chronicles " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "2 Chronicles " + i);
                            }
                            break;
                        case "Ezra":
                            for (int i = 1; i <= 10; ++i) {
                                sheetNumList.GetEzraVerse("Ezra " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Ezra " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Ezra " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Ezra " + i);
                            }
                            break;
                        case "Nehemiah":
                            for (int i = 1; i <= 13; ++i) {
                                sheetNumList.GetNehemiahVerse("Nehemiah " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Nehemiah " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Nehemiah " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Nehemiah " + i);
                            }
                            break;
                        case "Esther":
                            for (int i = 1; i <= 10; ++i) {
                                sheetNumList.GetEstherVerse("Esther " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Esther " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Esther " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Esther " + i);
                            }
                            break;
                        case "Job":
                            for (int i = 1; i <= 42; ++i) {
                                sheetNumList.GetJobVerse("Job " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Job " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Job " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Job " + i);
                            }
                            break;
                        case "Psalms":
                            for (int i = 1; i <= 150; ++i) {
                                sheetNumList.GetPsalmsVerse("Psalms " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Psalms " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Psalms " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Psalms " + i);
                            }
                            break;
                        case "Proverbs":
                            for (int i = 1; i <= 31; ++i) {
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Proverbs " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Proverbs " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Proverbs " + i);
                            }
                            break;
                        case "Ecclesiastes":
                            for (int i = 1; i <= 12; ++i) {
                                sheetNumList.GetEcclesiastesVerse("Ecclesiastes " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Ecclesiastes " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Ecclesiastes " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Ecclesiastes " + i);
                            }
                            break;
                        case "Song of Solomon":
                            for (int i = 1; i <= 8; ++i) {
                                sheetNumList.GetSolomonVerse("Song of Solomon " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Song of Solomon " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Song of Solomon " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Song of Solomon " + i);
                            }
                            break;
                        case "Isaiah":
                            for (int i = 1; i <= 66; ++i) {
                                sheetNumList.GetIsaiahVerse("Isaiah " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Isaiah " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Isaiah " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Isaiah " + i);
                            }
                            break;
                        case "Jeremiah":
                            for (int i = 1; i <= 52; ++i) {
                                sheetNumList.GetJeremiahVerse("Jeremiah " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Jeremiah " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Jeremiah " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Jeremiah " + i);
                            }
                            break;
                        case "Lamentations":
                            for (int i = 1; i <= 5; ++i) {
                                sheetNumList.GetLamentationsVerse("Lamentations " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Lamentations " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Lamentations " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Lamentations " + i);
                            }
                            break;
                        case "Ezekiel":
                            for (int i = 1; i <= 48; ++i) {
                                sheetNumList.GetEzekielVerse("Ezekiel " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Ezekiel " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Ezekiel " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Ezekiel " + i);
                            }
                            break;
                        case "Daniel":
                            for (int i = 1; i <= 12; ++i) {
                                sheetNumList.GetDanielVerse("Daniel " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Daniel " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Daniel " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Daniel " + i);
                            }
                            break;
                        case "Hosea":
                            for (int i = 1; i <= 14; ++i) {
                                sheetNumList.GetHoseaVerse("Hosea " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Hosea " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Hosea " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Hosea " + i);
                            }
                            break;
                        case "Joel":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetJoelVerse("Joel " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Joel " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Joel " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Joel " + i);
                            }
                            break;
                        case "Amos":
                            for (int i = 1; i <= 9; ++i) {
                                sheetNumList.GetAmosVerse("Amos " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Amos " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Amos " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Amos " + i);
                            }
                            break;
                        case "Obadiah":
                            for (int i = 1; i <= 1; ++i) {
                                sheetNumList.GetObadiahVerse("Obadiah " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Obadiah " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Obadiah " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Obadiah " + i);
                            }
                            break;
                        case "Jonah":
                            for (int i = 1; i <= 4; ++i) {
                                sheetNumList.GetObadiahVerse("Jonah " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Jonah " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Jonah " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Jonah " + i);
                            }
                            break;
                        case "Micah":
                            for (int i = 1; i <= 7; ++i) {
                                sheetNumList.GetMicahVerse("Micah " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Micah " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Micah " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Micah " + i);
                            }
                            break;
                        case "Nahum":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetNahumVerse("Nahum " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Nahum " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Nahum " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Nahum " + i);
                            }
                            break;
                        case "Habakkuk":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetHabakkukVerse("Habakkuk " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Habakkuk " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Habakkuk " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Habakkuk " + i);
                            }
                            break;
                        case "Zephaniah":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetZephaniahVerse("Zephaniah " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Zephaniah " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Zephaniah " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Zephaniah " + i);
                            }
                            break;
                        case "Haggai":
                            for (int i = 1; i <= 2; ++i) {
                                sheetNumList.GetHaggaiVerse("Haggai " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Haggai " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Haggai " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Haggai " + i);
                            }
                            break;
                        case "Zechariah":
                            for (int i = 1; i <= 14; ++i) {
                                sheetNumList.GetZechariahVerse("Zechariah " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Zechariah " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Zechariah " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Zechariah " + i);
                            }
                            break;
                        case "Malachi":
                            for (int i = 1; i <= 4; ++i) {
                                sheetNumList.GetMalachiVerse("Malachi " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Malachi " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Malachi " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Malachi " + i);
                            }
                            break;
                        case "Matthew":
                            for (int i = 1; i <= 28; ++i) {
                                sheetNumList.GetMatthewVerse("Matthew " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Matthew " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Matthew " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Matthew " + i);
                            }
                            break;
                        case "Mark":
                            for (int i = 1; i <= 16; ++i) {
                                sheetNumList.GetMarkVerse("Mark " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Mark " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Mark " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Mark " + i);
                            }
                            break;
                        case "Luke":
                            for (int i = 1; i <= 24; ++i) {
                                sheetNumList.GetLukeVerse("Luke " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Luke " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Luke " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Luke " + i);
                            }
                            break;
                        case "John":
                            for (int i = 1; i <= 21; ++i) {
                                sheetNumList.GetJohnVerse("John " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("John " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "John " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "John " + i);
                            }
                            break;
                        case "Acts":
                            for (int i = 1; i <= 28; ++i) {
                                sheetNumList.GetActsVerse("Acts " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Acts " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Acts " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Acts " + i);
                            }
                            break;
                        case "Romans":
                            for (int i = 1; i <= 16; ++i) {
                                sheetNumList.GetRomansVerse("Romans " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Romans " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Romans " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Romans " + i);
                            }
                            break;
                        case "1 Corinthians":
                            for (int i = 1; i <= 16; ++i) {
                                sheetNumList.GetCorinthians1Verse("1 Corinthians " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("1 Corinthians " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "1 Corinthians " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "1 Corinthians " + i);
                            }
                            break;
                        case "2 Corinthians":
                            for (int i = 1; i <= 13; ++i) {
                                sheetNumList.GetCorinthians2Verse("2 Corinthians " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("2 Corinthians " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "2 Corinthians " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "2 Corinthians " + i);
                            }
                            break;
                        case "Galatians":
                            for (int i = 1; i <= 6; ++i) {
                                sheetNumList.GetGalatiansVerse("Galatians " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Galatians " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Galatians " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Galatians " + i);
                            }
                            break;
                        case "Ephesians":
                            for (int i = 1; i <= 6; ++i) {
                                sheetNumList.GetEphesiansVerse("Ephesians " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Ephesians " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Ephesians " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Ephesians " + i);
                            }
                            break;
                        case "Philippians":
                            for (int i = 1; i <= 4; ++i) {
                                sheetNumList.GetPhilippiansVerse("Philippians " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Philippians " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Philippians " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Philippians " + i);
                            }
                            break;
                        case "Colossians":
                            for (int i = 1; i <= 4; ++i) {
                                sheetNumList.GetColossiansVerse("Colossians " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Colossians " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Colossians " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Colossians " + i);
                            }
                            break;
                        case "1 Thessalonians":
                            for (int i = 1; i <= 5; ++i) {
                                sheetNumList.GetThessalonians1Verse("1 Thessalonians " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("1 Thessalonians " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "1 Thessalonians " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "1 Thessalonians " + i);
                            }
                            break;
                        case "2 Thessalonians":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetThessalonians2Verse("2 Thessalonians " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("2 Thessalonians " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "2 Thessalonians " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "2 Thessalonians " + i);
                            }
                            break;
                        case "1 Timothy":
                            for (int i = 1; i <= 6; ++i) {
                                sheetNumList.GetTimothy1Verse("1 Timothy " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("1 Timothy " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "1 Timothy " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "1 Timothy " + i);
                            }
                            break;
                        case "2 Timothy":
                            for (int i = 1; i <= 4; ++i) {
                                sheetNumList.GetTimothy2Verse("2 Timothy " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("2 Timothy " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "2 Timothy " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "2 Timothy " + i);
                            }
                            break;
                        case "Titus":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetTitusVerse("Titus " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Titus " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Titus " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Titus " + i);
                            }
                            break;
                        case "Philemon":
                            for (int i = 1; i <= 1; ++i) {
                                sheetNumList.GetPhilemonVerse("Philemon " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Philemon " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Philemon " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Philemon " + i);
                            }
                            break;
                        case "Hebrews":
                            for (int i = 1; i <= 13; ++i) {
                                sheetNumList.GetHebrewsVerse("Hebrews " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Hebrews " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Hebrews " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Hebrews " + i);
                            }
                            break;
                        case "James":
                            for (int i = 1; i <= 5; ++i) {
                                sheetNumList.GetJamesVerse("James " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("James " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "James " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "James " + i);
                            }
                            break;
                        case "1 Peter":
                            for (int i = 1; i <= 5; ++i) {
                                sheetNumList.GetPeter1Verse("1 Peter " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("1 Peter " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "1 Peter " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "1 Peter " + i);
                            }
                            break;
                        case "2 Peter":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetPeter2Verse("2 Peter " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("2 Peter " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "2 Peter " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "2 Peter " + i);
                            }
                            break;
                        case "1 John":
                            for (int i = 1; i <= 5; ++i) {
                                sheetNumList.GetJohn1Verse("1 John " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Genesis " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Genesis " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Genesis " + i);
                            }
                            break;
                        case "2 John":
                            for (int i = 1; i <= 1; ++i) {
                                sheetNumList.GetJohn2Verse("2 John " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("2 John " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "2 John " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "2 John " + i);
                            }
                            break;
                        case "3 John":
                            for (int i = 1; i <= 1; ++i) {
                                sheetNumList.GetJohn3Verse("3 John " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("3 John " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "3 John " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "3 John " + i);
                            }
                            break;
                        case "Jude":
                            for (int i = 1; i <= 1; ++i) {
                                sheetNumList.GetJudeVerse("Jude " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Jude " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Jude " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Jude " + i);
                            }
                            break;
                        case "Revelation":
                            for (int i = 1; i <= 22; ++i) {
                                sheetNumList.GetRevelationVerse("Revelation " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("Revelation " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "Revelation " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "Revelation " + i);
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + title);
                    }
                } else {
                    switch (titleName) {
                        case "창세기":
                            for (int i = 1; i <= 50; ++i) {
                                sheetNumList.GetGenesisVerse("창세기 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("창세기 " + i)) {
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "창세기 " + i);
                                } else {
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "창세기 " + i);
                                }
                            }
                            break;
                        case "출애굽기":
                            for (int i = 1; i <= 40; ++i) {
                                sheetNumList.GetExodusVerse("출애굽기 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("출애굽기 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "출애굽기 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "출애굽기 " + i);
                            }
                            break;
                        case "레위기":
                            for (int i = 1; i <= 27; ++i) {
                                sheetNumList.GetLeviticusVerse("레위기 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("레위기 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "레위기 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "레위기 " + i);
                            }
                            break;
                        case "민수기":
                            for (int i = 1; i <= 36; ++i) {
                                sheetNumList.GetNumbersVerse("민수기 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("민수기 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "민수기 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "민수기 " + i);
                            }
                            break;
                        case "신명기":
                            for (int i = 1; i <= 34; ++i) {
                                sheetNumList.GetDeuteronomyVerse("신명기 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("신명기 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "신명기 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "신명기 " + i);
                            }
                            break;
                        case "여호수아":
                            for (int i = 1; i <= 24; ++i) {
                                sheetNumList.GetJoshuaVerse("여호수아 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("여호수아 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "여호수아 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "여호수아 " + i);
                            }
                            break;
                        case "사사기":
                            for (int i = 1; i <= 21; ++i) {
                                sheetNumList.GetJudgesVerse("사사기 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("사사기 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "사사기 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "사사기 " + i);
                            }
                            break;
                        case "룻기":
                            for (int i = 1; i <= 4; ++i) {
                                sheetNumList.GetRuthVerse("룻기 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("룻기 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "룻기 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "룻기 " + i);
                            }
                            break;
                        case "사무엘상":
                            for (int i = 1; i <= 31; ++i) {
                                sheetNumList.GetSamuel1Verse("사무엘상 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("사무엘상 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "사무엘상 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "사무엘상 " + i);
                            }
                            break;
                        case "사무엘하":
                            for (int i = 1; i <= 24; ++i) {
                                sheetNumList.GetSamuel2Verse("사무엘하 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("사무엘하 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "사무엘하 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "사무엘하 " + i);
                            }
                            break;
                        case "열왕기상":
                            for (int i = 1; i <= 22; ++i) {
                                sheetNumList.GetKing1Verse("열왕기상 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("열왕기상 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "열왕기상 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "열왕기상 " + i);
                            }
                            break;
                        case "열왕기하":
                            for (int i = 1; i <= 25; ++i) {
                                sheetNumList.GetKing2Verse("열왕기하 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("열왕기하 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "열왕기하 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "열왕기하 " + i);
                            }
                            break;
                        case "역대상":
                            for (int i = 1; i <= 29; ++i) {
                                sheetNumList.GetChronicles1Verse("역대상 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("역대상 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "역대상 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "역대상 " + i);
                            }
                            break;
                        case "역대하":
                            for (int i = 1; i <= 36; ++i) {
                                sheetNumList.GetChronicles2Verse("역대하 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("역대하 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "역대하 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "역대하 " + i);
                            }
                            break;
                        case "에스라":
                            for (int i = 1; i <= 10; ++i) {
                                sheetNumList.GetEzraVerse("에스라 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("에스라 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "에스라 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "에스라 " + i);
                            }
                            break;
                        case "느헤미야":
                            for (int i = 1; i <= 13; ++i) {
                                sheetNumList.GetNehemiahVerse("느헤미야 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("느헤미야 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "느헤미야 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "느헤미야 " + i);
                            }
                            break;
                        case "에스더":
                            for (int i = 1; i <= 10; ++i) {
                                sheetNumList.GetEstherVerse("에스더 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("에스더 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "에스더 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "에스더 " + i);
                            }
                            break;
                        case "욥기":
                            for (int i = 1; i <= 42; ++i) {
                                sheetNumList.GetJobVerse("욥기 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("욥기 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "욥기 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "욥기 " + i);
                            }
                            break;
                        case "시편":
                            for (int i = 1; i <= 150; ++i) {
                                sheetNumList.GetPsalmsVerse("시편 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("시편 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "시편 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "시편 " + i);
                            }
                            break;
                        case "잠언":
                            for (int i = 1; i <= 31; ++i) {
                                sheetNumList.GetProverbsVerse("잠언 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("잠언 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "잠언 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "잠언 " + i);
                            }
                            break;
                        case "전도서":
                            for (int i = 1; i <= 12; ++i) {
                                sheetNumList.GetEcclesiastesVerse("전도서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("전도서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "전도서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "전도서 " + i);
                            }
                            break;
                        case "아가":
                            for (int i = 1; i <= 8; ++i) {
                                sheetNumList.GetSolomonVerse("아가 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("아가 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "아가 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "아가 " + i);
                            }
                            break;
                        case "이사야":
                            for (int i = 1; i <= 66; ++i) {
                                sheetNumList.GetIsaiahVerse("이사야 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("이사야 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "이사야 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "이사야 " + i);
                            }
                            break;
                        case "예레미야":
                            for (int i = 1; i <= 52; ++i) {
                                sheetNumList.GetJeremiahVerse("예레미야 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("예레미야 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "예레미야 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "예레미야 " + i);
                            }
                            break;
                        case "예레미야애가":
                            for (int i = 1; i <= 5; ++i) {
                                sheetNumList.GetLamentationsVerse("예레미야애가 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("예레미야애가 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "예레미야애가 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "예레미야애가 " + i);
                            }
                            break;
                        case "에스겔":
                            for (int i = 1; i <= 48; ++i) {
                                sheetNumList.GetEzekielVerse("에스겔 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("에스겔 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "에스겔 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "에스겔 " + i);
                            }
                            break;
                        case "다니엘":
                            for (int i = 1; i <= 12; ++i) {
                                sheetNumList.GetDanielVerse("다니엘 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("다니엘 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "다니엘 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "다니엘 " + i);
                            }
                            break;
                        case "호세아":
                            for (int i = 1; i <= 14; ++i) {
                                sheetNumList.GetHoseaVerse("호세아 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("호세아 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "호세아 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "호세아 " + i);
                            }
                            break;
                        case "요엘":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetJoelVerse("요엘 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("요엘 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "요엘 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "요엘 " + i);
                            }
                            break;
                        case "아모스":
                            for (int i = 1; i <= 9; ++i) {
                                sheetNumList.GetAmosVerse("아모스 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("아모스 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "아모스 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "아모스 " + i);
                            }
                            break;
                        case "오바댜":
                            for (int i = 1; i <= 1; ++i) {
                                sheetNumList.GetObadiahVerse("오바댜 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("오바댜 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "오바댜 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "오바댜 " + i);
                            }
                            break;
                        case "요나":
                            for (int i = 1; i <= 4; ++i) {
                                sheetNumList.GetJonahVerse("요나 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("요나 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "요나 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "요나 " + i);
                            }
                            break;
                        case "미가":
                            for (int i = 1; i <= 7; ++i) {
                                sheetNumList.GetMicahVerse("미가 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("미가 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "미가 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "미가 " + i);
                            }
                            break;
                        case "나훔":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetNahumVerse("나훔 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("나훔 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "나훔 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "나훔 " + i);
                            }
                            break;
                        case "하박국":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetHabakkukVerse("하박국 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("하박국 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "하박국 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "하박국 " + i);
                            }
                            break;
                        case "스바냐":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetZephaniahVerse("스바냐 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("스바냐 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "스바냐 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "스바냐 " + i);
                            }
                            break;
                        case "학개":
                            for (int i = 1; i <= 2; ++i) {
                                sheetNumList.GetHaggaiVerse("학개 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("학개 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "학개 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "학개 " + i);
                            }
                            break;
                        case "스가랴":
                            for (int i = 1; i <= 14; ++i) {
                                sheetNumList.GetZechariahVerse("스가랴 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("스가랴 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "스가랴 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "스가랴 " + i);
                            }
                            break;
                        case "말라기":
                            for (int i = 1; i <= 4; ++i) {
                                sheetNumList.GetMalachiVerse("말라기 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("말라기 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "말라기 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "말라기 " + i);
                            }
                            break;
                        case "마태복음":
                            for (int i = 1; i <= 28; ++i) {
                                sheetNumList.GetMatthewVerse("마태복음 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("마태복음 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "마태복음 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "마태복음 " + i);
                            }
                            break;
                        case "마가복음":
                            for (int i = 1; i <= 16; ++i) {
                                sheetNumList.GetMarkVerse("마가복음 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("마가복음 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "마가복음 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "마가복음 " + i);
                            }
                            break;
                        case "누가복음":
                            for (int i = 1; i <= 24; ++i) {
                                sheetNumList.GetLukeVerse("누가복음 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("누가복음 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "누가복음 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "누가복음 " + i);
                            }
                            break;
                        case "요한복음":
                            for (int i = 1; i <= 21; ++i) {
                                sheetNumList.GetJohnVerse("요한복음 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("요한복음 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "요한복음 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "요한복음 " + i);
                            }
                            break;
                        case "사도행전":
                            for (int i = 1; i <= 28; ++i) {
                                sheetNumList.GetActsVerse("사도행전 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("사도행전 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "사도행전 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "사도행전 " + i);
                            }
                            break;
                        case "로마서":
                            for (int i = 1; i <= 16; ++i) {
                                sheetNumList.GetRomansVerse("로마서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("로마서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "로마서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "로마서 " + i);
                            }
                            break;
                        case "고린도전서":
                            for (int i = 1; i <= 16; ++i) {
                                sheetNumList.GetCorinthians1Verse("고린도전서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("고린도전서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "고린도전서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "고린도전서 " + i);
                            }
                            break;
                        case "고린도후서":
                            for (int i = 1; i <= 13; ++i) {
                                sheetNumList.GetChronicles2Verse("고린도후서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("고린도후서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "고린도후서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "고린도후서 " + i);
                            }
                            break;
                        case "갈라디아서":
                            for (int i = 1; i <= 6; ++i) {
                                sheetNumList.GetGalatiansVerse("갈라디아서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("갈라디아서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "갈라디아서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "갈라디아서 " + i);
                            }
                            break;
                        case "에베소서":
                            for (int i = 1; i <= 6; ++i) {
                                sheetNumList.GetEphesiansVerse("에베소서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("에베소서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "에베소서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "에베소서 " + i);
                            }
                            break;
                        case "빌립보서":
                            for (int i = 1; i <= 4; ++i) {
                                sheetNumList.GetPhilippiansVerse("빌립보서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("빌립보서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "빌립보서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "빌립보서 " + i);
                            }
                            break;
                        case "골로새서":
                            for (int i = 1; i <= 4; ++i) {
                                sheetNumList.GetColossiansVerse("골로새서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("골로새서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "골로새서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "골로새서 " + i);
                            }
                            break;
                        case "데살로니가전서":
                            for (int i = 1; i <= 5; ++i) {
                                sheetNumList.GetThessalonians1Verse("데살로니가전서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("데살로니가전서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "데살로니가전서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "데살로니가전서 " + i);
                            }
                            break;
                        case "데살로니가후서":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetThessalonians2Verse("데살로니가후서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("데살로니가후서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "데살로니가후서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "데살로니가후서 " + i);
                            }
                            break;
                        case "디모데전서":
                            for (int i = 1; i <= 6; ++i) {
                                sheetNumList.GetTimothy1Verse("디모데전서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("디모데전서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "디모데전서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "디모데전서 " + i);
                            }
                            break;
                        case "디모데후서":
                            for (int i = 1; i <= 4; ++i) {
                                sheetNumList.GetTimothy2Verse("디모데전서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("디모데후서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "디모데후서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "디모데후서 " + i);
                            }
                            break;
                        case "디도서":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetTitusVerse("디도서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("디도서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "디도서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "디도서 " + i);
                            }
                            break;
                        case "빌레몬서":
                            for (int i = 1; i <= 1; ++i) {
                                sheetNumList.GetPhilemonVerse("빌레몬서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("빌레몬서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "빌레몬서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "빌레몬서 " + i);
                            }
                            break;
                        case "히브리서":
                            for (int i = 1; i <= 13; ++i) {
                                sheetNumList.GetHebrewsVerse("히브리서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("히브리서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "히브리서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "히브리서 " + i);
                            }
                            break;
                        case "야고보서":
                            for (int i = 1; i <= 5; ++i) {
                                sheetNumList.GetJamesVerse("야고보서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("야고보서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "야고보서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "야고보서 " + i);
                            }
                            break;
                        case "베드로전서":
                            for (int i = 1; i <= 5; ++i) {
                                sheetNumList.GetPeter1Verse("베드로전서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("베드로전서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "베드로전서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "베드로전서 " + i);
                            }
                            break;
                        case "베드로후서":
                            for (int i = 1; i <= 3; ++i) {
                                sheetNumList.GetPeter2Verse("베드로후서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("베드로후서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "베드로후서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "베드로후서 " + i);
                            }
                            break;
                        case "요한일서":
                            for (int i = 1; i <= 5; ++i) {
                                sheetNumList.GetJohn1Verse("요한일서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("요한일서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "요한일서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "요한일서 " + i);
                            }
                            break;
                        case "요한이서":
                            for (int i = 1; i <= 1; ++i) {
                                sheetNumList.GetJohn2Verse("요한이서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("요한이서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "요한이서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "요한이서 " + i);
                            }
                            break;
                        case "요한삼서":
                            for (int i = 1; i <= 1; ++i) {
                                sheetNumList.GetJohn3Verse("요한삼서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("요한삼서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "요한삼서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "요한삼서 " + i);
                            }
                            break;
                        case "유다서":
                            for (int i = 1; i <= 1; ++i) {
                                sheetNumList.GetJudeVerse("유다서 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("유다서 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "유다서 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "유다서 " + i);
                            }
                            break;
                        case "요한계시록":
                            for (int i = 1; i <= 22; ++i) {
                                sheetNumList.GetRevelationVerse("요한계시록 " + i);
                                estimateIsAll(sheetNumList.beginIdx, sheetNumList.endIdx);
                                if (isAllRead("요한계시록 " + i))
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), "요한계시록 " + i);
                                else
                                    sideMidMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), "요한계시록 " + i);
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + title);
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingMenu.setVisibility(View.GONE);
                        midListView.setVisibility(View.VISIBLE);
                    }
                });

            }
        }).start();

    }

    /*-------------------------------------------------------------------------------------------------getString_mid_ListView() */
    public void getString_Inner_ListView(String title) {
        sideMenuAdapter.clearItem();
        String splitStr = "";
        if (title.split(" ").length == 3) {
            splitStr = title.split(" ")[0] + " " + title.split(" ")[1];
        } else if (title.split(" ").length == 4) {
            splitStr = title.split(" ")[0] + " " + title.split(" ")[1] + " " + title.split(" ")[2];
        } else {
            splitStr = title.split(" ")[0];
        }
        switch (splitStr) {
            case "Genesis":
            case "창세기":
                sheetNumList.GetGenesisVerse(title);
                break;
            case "Exodus":
            case "출애굽기":
                sheetNumList.GetExodusVerse(title);
                break;
            case "Leviticus":
            case "레위기":
                sheetNumList.GetLeviticusVerse(title);
                break;
            case "Numbers":
            case "민수기":
                sheetNumList.GetNumbersVerse(title);
                break;
            case "Deuteronomy":
            case "신명기":
                sheetNumList.GetDeuteronomyVerse(title);
                break;
            case "Joshua":
            case "여호수아":
                sheetNumList.GetJoshuaVerse(title);
                break;
            case "Judges":
            case "사사기":
                sheetNumList.GetJudgesVerse(title);
                break;
            case "Ruth":
            case "룻기":
                sheetNumList.GetRuthVerse(title);
                break;
            case "1 Samuel":
            case "사무엘상":
                sheetNumList.GetSamuel1Verse(title);
                break;
            case "2 Samuel":
            case "사무엘하":
                sheetNumList.GetSamuel2Verse(title);
                break;
            case "1 Kings":
            case "열왕기상":
                sheetNumList.GetKing1Verse(title);
                break;
            case "2 Kings":
            case "열왕기하":
                sheetNumList.GetKing2Verse(title);
                break;
            case "1 Chronicles":
            case "역대상":
                sheetNumList.GetChronicles1Verse(title);
                break;
            case "2 Chronicles":
            case "역대하":
                sheetNumList.GetChronicles2Verse(title);
                break;
            case "Ezra":
            case "에스라":
                sheetNumList.GetEzraVerse(title);
                break;
            case "Nehemiah":
            case "느헤미야":
                sheetNumList.GetNehemiahVerse(title);
                break;
            case "Esther":
            case "에스더":
                sheetNumList.GetEstherVerse(title);
                break;
            case "Job":
            case "욥기":
                sheetNumList.GetJobVerse(title);
                break;
            case "Psalms":
            case "시편":
                sheetNumList.GetPsalmsVerse(title);
                break;
            case "Proverbs":
            case "잠언":
                sheetNumList.GetProverbsVerse(title);
                break;
            case "Ecclesiastes":
            case "전도서":
                sheetNumList.GetEcclesiastesVerse(title);
                break;
            case "Song of Solomon":
            case "아가":
                sheetNumList.GetSolomonVerse(title);
                break;
            case "Isaiah":
            case "이사야":
                sheetNumList.GetIsaiahVerse(title);
                break;
            case "Jeremiah":
            case "예레미야":
                sheetNumList.GetJeremiahVerse(title);
                break;
            case "Lamentations":
            case "예레미야애가":
                sheetNumList.GetLamentationsVerse(title);
                break;
            case "Ezekiel":
            case "에스겔":
                sheetNumList.GetEzekielVerse(title);
                break;
            case "Daniel":
            case "다니엘":
                sheetNumList.GetDanielVerse(title);
                break;
            case "Hosea":
            case "호세아":
                sheetNumList.GetHoseaVerse(title);
                break;
            case "Joel":
            case "요엘":
                sheetNumList.GetJoelVerse(title);
                break;
            case "Amos":
            case "아모스":
                sheetNumList.GetAmosVerse(title);
                break;
            case "Obadiah":
            case "오바댜":
                sheetNumList.GetObadiahVerse(title);
                break;
            case "Jonah":
            case "요나":
                sheetNumList.GetJonahVerse(title);
                break;
            case "Micah":
            case "미가":
                sheetNumList.GetMicahVerse(title);
                break;
            case "Nahum":
            case "나훔":
                sheetNumList.GetNahumVerse(title);
                break;
            case "Habakkuk":
            case "하박국":
                sheetNumList.GetHabakkukVerse(title);
                break;
            case "Zephaniah":
            case "스바냐":
                sheetNumList.GetZephaniahVerse(title);
                break;
            case "Haggai":
            case "학개":
                sheetNumList.GetHaggaiVerse(title);
                break;
            case "Zechariah":
            case "스가랴":
                sheetNumList.GetZechariahVerse(title);
                break;
            case "Malachi":
            case "말라기":
                sheetNumList.GetMalachiVerse(title);
                break;
            case "Matthew":
            case "마태복음":
                sheetNumList.GetMatthewVerse(title);
                break;
            case "Mark":
            case "마가복음":
                sheetNumList.GetMarkVerse(title);
                break;
            case "Luke":
            case "누가복음":
                sheetNumList.GetLukeVerse(title);
                break;
            case "John":
            case "요한복음":
                sheetNumList.GetJohnVerse(title);
                break;
            case "Acts":
            case "사도행전":
                sheetNumList.GetActsVerse(title);
                break;
            case "Romans":
            case "로마서":
                sheetNumList.GetRomansVerse(title);
                break;
            case "1 Corinthians":
            case "고린도전서":
                sheetNumList.GetCorinthians1Verse(title);
                break;
            case "2 Corinthians":
            case "고린도후서":
                sheetNumList.GetCorinthians2Verse(title);
                break;
            case "Galatians":
            case "갈라디아서":
                sheetNumList.GetGalatiansVerse(title);
                break;
            case "Ephesians":
            case "에베소서":
                sheetNumList.GetEphesiansVerse(title);
                break;
            case "Philippians":
            case "빌립보서":
                sheetNumList.GetPhilippiansVerse(title);
                break;
            case "Colossians":
            case "골로새서":
                sheetNumList.GetColossiansVerse(title);
                break;
            case "1 Thessalonians":
            case "데살로니가전서":
                sheetNumList.GetThessalonians1Verse(title);
                break;
            case "2 Thessalonians":
            case "데살로니가후서":
                sheetNumList.GetThessalonians2Verse(title);
                break;
            case "1 Timothy":
            case "디모데전서":
                sheetNumList.GetTimothy1Verse(title);
                break;
            case "2 Timothy":
            case "디모데후서":
                sheetNumList.GetTimothy2Verse(title);
                break;
            case "Titus":
            case "디도서":
                sheetNumList.GetTitusVerse(title);
                break;
            case "Philemon":
            case "빌레몬서":
                sheetNumList.GetPhilemonVerse(title);
                break;
            case "Hebrews":
            case "히브리서":
                sheetNumList.GetHebrewsVerse(title);
                break;
            case "James":
            case "야고보서":
                sheetNumList.GetJamesVerse(title);
                break;
            case "1 Peter":
            case "베드로전서":
                sheetNumList.GetPeter1Verse(title);
                break;
            case "2 Peter":
            case "베드로후서":
                sheetNumList.GetPeter2Verse(title);
                break;
            case "1 John":
            case "요한일서":
                sheetNumList.GetJohn1Verse(title);
                break;
            case "2 John":
            case "요한이서":
                sheetNumList.GetJohn2Verse(title);
                break;
            case "3 John":
            case "요한삼서":
                sheetNumList.GetJohn3Verse(title);
                break;
            case "Jude":
            case "유다서":
                sheetNumList.GetJudeVerse(title);
                break;
            case "Revelation":
            case "요한계시록":
                sheetNumList.GetRevelationVerse(title);
                break;
        }
        isAll.clear();
        for (int i = sheetNumList.beginIdx; i <= sheetNumList.endIdx; ++i) {
            Cursor cursor = bmDBHelper.readIDRecord(i);
            if (cursor.getCount() > 0) {
                if (!isKorean) {
                    sideMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), eng_sheet.getCell(0, i).getContents());
                } else {
                    sideMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_24), korean_sheet.getCell(0, i).getContents());
                }
                isAll.add(true);
            } else {
                if (!isKorean) {
                    sideMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), eng_sheet.getCell(0, i).getContents());
                } else {
                    sideMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_check_box_outline_blank_24), korean_sheet.getCell(0, i).getContents());
                }
                isAll.add(false);
            }
        }
    }

    public void ChangeLanguage(boolean isKorean_) {
        if (language_switch.isChecked()) {
            if (isOuterSheet) {
                outerListView.setVisibility(View.VISIBLE);
                setOuterAdapter(korean_bible_titleList);
                outerListView.setAdapter(sideOuterMenuAdapter);
                midListView.setVisibility(View.GONE);
                innerListView.setVisibility(View.GONE);
            } else if (isMidSheet) {
                midListView.setVisibility(View.VISIBLE);
                getString_mid_ListView(korean_bible_titleList[mid_index]);
                midListView.setAdapter(sideMidMenuAdapter);
                outerListView.setVisibility(View.GONE);
                innerListView.setVisibility(View.GONE);
            } else if (isInnerSheet) {
                innerListView.setVisibility(View.VISIBLE);
                outerListView.setVisibility(View.GONE);
                midListView.setVisibility(View.GONE);
                if (reverse_Language(mid_reverse_str).equals("Unexpected")) {
                    String tmp = mid_str;
                    mid_str = mid_reverse_str;
                    mid_reverse_str = tmp;
                }
                getString_Inner_ListView(mid_str);
                innerListView.setAdapter(sideMenuAdapter);
            }
        } else {
            if (isOuterSheet) {
                outerListView.setVisibility(View.VISIBLE);
                setOuterAdapter(bible_titleList);
                outerListView.setAdapter(sideOuterMenuAdapter);

                midListView.setVisibility(View.GONE);
                innerListView.setVisibility(View.GONE);
            } else if (isMidSheet) {
                midListView.setVisibility(View.VISIBLE);
                getString_mid_ListView(bible_titleList[mid_index]);
                midListView.setAdapter(sideMidMenuAdapter);
                outerListView.setVisibility(View.GONE);
                innerListView.setVisibility(View.GONE);
            } else if (isInnerSheet) {
                innerListView.setVisibility(View.VISIBLE);
                outerListView.setVisibility(View.GONE);
                midListView.setVisibility(View.GONE);
                if (reverse_Language(mid_str).equals("Unexpected")) {
                    String tmp = mid_str;
                    mid_str = mid_reverse_str;
                    mid_reverse_str = tmp;
                }
                getString_Inner_ListView(mid_reverse_str);
                innerListView.setAdapter(sideMenuAdapter);
            }
        }
    }

    public void setOuterAdapter(String[] bible) {
        sideOuterMenuAdapter.clearItem();
        for (String each : bible)
            sideOuterMenuAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_menu_book_24), each);
    }

    public String reverse_Language(String str) {
        String[] splited = str.split(" ");
        String subStr = "";
        String subNum = "";
        if (!isKorean) {
            if (splited.length == 3) { // 상하 존재
                subStr = splited[0] + " " + splited[1];
                subNum = splited[2];
            } else if (splited.length == 4) {// 솔로몬서
                subStr = splited[0] + " " + splited[1] + " " + splited[2];
                subNum = splited[3];
            } else {
                subStr = splited[0];
                subNum = splited[1];
            }
            switch (subStr) {
                case "Genesis":
                    return "창세기 " + subNum;
                case "Exodus":
                    return "출애굽기 " + subNum;
                case "Leviticus":
                    return "레위기 " + subNum;
                case "Numbers":
                    return "민수기 " + subNum;
                case "Deuteronomy":
                    return "신명기 " + subNum;
                case "Joshua":
                    return "여호수아 " + subNum;
                case "Judges":
                    return "사사기 " + subNum;
                case "Ruth":
                    return "룻기 " + subNum;
                case "1 Samuel":
                    return "사무엘상 " + subNum;
                case "2 Samuel":
                    return "사무엘하 " + subNum;
                case "1 Kings":
                    return "열왕기상 " + subNum;
                case "2 Kings":
                    return "열왕기하 " + subNum;
                case "1 Chronicles":
                    return "역대상 " + subNum;
                case "2 Chronicles":
                    return "역대하 " + subNum;
                case "Ezra":
                    return "에스라 " + subNum;
                case "Nehemiah":
                    return "느헤미야 " + subNum;
                case "Esther":
                    return "에스더 " + subNum;
                case "Job":
                    return "욥기 " + subNum;
                case "Psalms":
                    return "시편 " + subNum;
                case "Proverbs":
                    return "잠언 " + subNum;
                case "Ecclesiastes":
                    return "전도서 " + subNum;
                case "Song of Solomon":
                    return "아가 " + subNum;
                case "Isaiah":
                    return "이사야 " + subNum;
                case "Jeremiah":
                    return "예레미야 " + subNum;
                case "Lamentations":
                    return "예레미야애가 " + subNum;
                case "Ezekiel":
                    return "에스겔 " + subNum;
                case "Daniel":
                    return "다니엘 " + subNum;
                case "Hosea":
                    return "호세아 " + subNum;
                case "Joel":
                    return "요엘 " + subNum;
                case "Amos":
                    return "아모스 " + subNum;
                case "Obadiah":
                    return "오바댜 " + subNum;
                case "Jonah":
                    return "요나 " + subNum;
                case "Micah":
                    return "미가 " + subNum;
                case "Nahum":
                    return "나훔 " + subNum;
                case "Habakkuk":
                    return "하박국 " + subNum;
                case "Zephaniah":
                    return "스바냐 " + subNum;
                case "Haggai":
                    return "학개 " + subNum;
                case "Zechariah":
                    return "스가랴 " + subNum;
                case "Malachi":
                    return "말라기 " + subNum;
                case "Matthew":
                    return "마태복음 " + subNum;
                case "Mark":
                    return "마가복음 " + subNum;
                case "Luke":
                    return "누가복음 " + subNum;
                case "John":
                    return "요한복음 " + subNum;
                case "Acts":
                    return "사도행전 " + subNum;
                case "Romans":
                    return "로마서 " + subNum;
                case "1 Corinthians":
                    return "고린도전서 " + subNum;
                case "2 Corinthians":
                    return "고린도후서 " + subNum;
                case "Galatians":
                    return "갈라디아서 " + subNum;
                case "Ephesians":
                    return "에베소서 " + subNum;
                case "Philippians":
                    return "빌립보서 " + subNum;
                case "Colossians":
                    return "골로새서 " + subNum;
                case "1 Thessalonians":
                    return "데살로니가전서 " + subNum;
                case "2 Thessalonians":
                    return "데살로니가후서 " + subNum;
                case "1 Timothy":
                    return "디모데전서 " + subNum;
                case "2 Timothy":
                    return "디모데후서 " + subNum;
                case "Titus":
                    return "디도서 " + subNum;
                case "Philemon":
                    return "빌레몬서 " + subNum;
                case "Hebrews":
                    return "히브리서 " + subNum;
                case "James":
                    return "야고보서 " + subNum;
                case "1 Peter":
                    return "베드로전서 " + subNum;
                case "2 Peter":
                    return "베드로후서 " + subNum;
                case "1 John":
                    return "요한일서 " + subNum;
                case "2 John":
                    return "요한이서 " + subNum;
                case "3 John":
                    return "요한삼서 " + subNum;
                case "Jude":
                    return "유다서 " + subNum;
                case "Revelation":
                    return "요한계시록 " + subNum;
                default:
                    return "Unexpected";
            }
        } else {
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
    }

    public int extractSheetNum(String str) {
        int result = 0;
        if (!isKorean) {
            if (str.split(" ").length == 4) {
                result = Integer.parseInt(str.split(" ")[3].split(":")[0]);
            } else if (str.split(" ").length == 3) {
                result = Integer.parseInt(str.split(" ")[2].split(":")[0]);
            } else {
                result = Integer.parseInt(str.split(" ")[1].split(":")[0]);
            }
        } else {
            result = Integer.parseInt(str.split(" ")[1].split(":")[0]);
        }
        return result;
    }

    public String extractSheetTitle(String str) {
        String result = "";
        if (!isKorean) {
            if (str.split(" ").length == 4) {
                result = str.split(" ")[0] + " " + str.split(" ")[1] + " " + str.split(" ")[2];
            } else if (str.split(" ").length == 3) {
                result = str.split(" ")[0] + " " + str.split(" ")[1];
            } else {
                result = str.split(" ")[0];
            }
        } else {
            result = str.split(" ")[0];
        }
        return result;
    }

    public boolean isAllRead(String str) {
        for (boolean chk : isAll) {
            if (!chk) return false;
        }
        return true;
    }

    public void estimateIsAll(int start, int finish) {
        isAll.clear();
        for (int i = start; i <= finish; ++i) {
            Cursor cursor = bmDBHelper.readIDRecord(i);
            if (cursor.getCount() > 0) {
                isAll.add(true);
            } else {
                isAll.add(false);
            }
        }
    }

    public void setContentAndconnectHttpAndGetJson(String recv, final int currSide, final String location, final int idx) {
        if (recv.contains(" ")) {
            recv = recv.replace(' ', '+');
        }
        final String finalReceive = recv;

        if (location.equals("left")) {
            if (currSide == 1) {
                side1_Content.setText("불러오는 중입니다.");
            } else if (currSide == 2) {
                side2_Content.setText("불러오는 중입니다.");
            } else if (currSide == 3) {
                side3_Content.setText("불러오는 중입니다.");
            }
        }
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 아래의 두 String 변수를 전역으로 선언했더니 Theard들끼리 공유하는 자원이 되서 동기화가 안되서 같은 내용이 무작위로 선택되는 문제 발생
                    //전역변수로 할 시, synchronized 키워드나, 각 변수를 지역변수로 선언하도록 한다.
                    // 파싱 결과의 순서는 다 달라질 수 있다.
                    final String receiveMsgFromJson;
                    String tempString;
                    URL url = new URL("https://labs.bible.org/api/?passage=" + finalReceive + "&type=json&formatting=plain");
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
                                parseJson(receiveMsgFromJson, currSide, location, idx);
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
        th.start();
    }

    public void parseJson(String jsonString, int currSide, String location, int idx) {
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (currSide == 1) {
                    side1_Content.setText(jsonObject.optString("text"));
                } else if (currSide == 2) {
                    if (location.equals("left")) {
                        side2_Content.setText(jsonObject.optString("text"));
                    } else if (location.equals("right")) {
                        side2_Content.setText(side2_Content.getText().toString() + "\n\n" + jsonObject.optString("text"));
                    }
                } else if (currSide == 3) {
                    if (location.equals("left")) {
                        side3_Content.setText(jsonObject.optString("text"));
                    } else if (location.equals("center")) {
                        side3_Content.setText(side3_Content.getText().toString() + "\n\n" + jsonObject.optString("text"));
                    } else if (location.equals("right")) {
                        side3_Content.setText(side3_Content.getText().toString() + "\n\n" + jsonObject.optString("text"));
                    }
                }
            }
            if (currSide == 2 && location.equals("left")) {
                setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, idx + 1).getContents(), currSide, "right", idx + 1);
            }
            if (currSide == 3 && location.equals("left")) {
                setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, idx + 1).getContents(), currSide, "center", idx + 1);
            }
            if (currSide == 3 && location.equals("center")) {
                setContentAndconnectHttpAndGetJson(eng_sheet.getCell(0, idx + 1).getContents(), currSide, "right", idx + 1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


