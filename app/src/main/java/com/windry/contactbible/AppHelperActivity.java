package com.windry.contactbible;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class AppHelperActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_helper_layout);
        TextView main_content = findViewById(R.id.main_content);
        ImageButton back_btn = findViewById(R.id.back_menu);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        main_content.setText("※Contact Bible 도움말※\n\n컨택 바이블은 언택트시대 속에서 서로가 방황하지 않고 주님의 말씀에 더욱 컨택트하기를 소망하여 제작하였습니다.\n\n\n -구절 화면-\n\n1. 절 단위로 읽는 성경입니다.\n\n2. 화면을 터치하거나 왼쪽으로 스와이프(슬라이드)하면 다음 구절로 넘어갑니다." +
                "\n\n3. 왼쪽 상단 버튼으로 메뉴창을 열어 성경 전체 중 원하는 책, 장, 구절로 이동하여 볼 수 있습니다.(책의 내용이 많은 경우 여는데 시간이 추가적으로 소요됩니다.)\n그리고 메뉴창 상단에 영&한 변경 스위치가 있어 영어모드, 한글모드로 변경 가능합니다. \n또한 영&한 모드 변경 후 추가로 화면을 건들거나, 메뉴에서 새로운 구절을 클릭했을 때 화면에 언어가 변경되어 보여집니다.\n\n4. 오른쪽 상단 막대기로 절을 읽는 단위를 변경합니다.(최대 3)\n\n" +
                "5. 왼쪽 상단 \"MEMO\"버튼으로 메모창을 열고 닫아 해당 구절에서의 자신의 메모를 작성하고 저장할 수 있습니다. (단 1절씩 보기일 때만 사용 가능합니다.)\n\n" +
                "6. 오른쪽 가장 상단 위 책갈피 버튼 클릭시 해당 구절을 즐겨찾기로 설정할 수 있습니다.\n(단 1절씩 보기일 때만 사용 가능합니다. 또한, 즐겨찾기 된 항목들은 화면 왼쪽 아래 버튼" +
                " \"메인메뉴\" -> \"즐겨찾기\"에서 확인 할 수 있습니다.)\n\n7. 하단부에는 구절의 글씨를 키우고 줄이는 버튼이 있어 원하는 글씨 크기로 변경할 수 있습니다. \n\n" +
                "8. 화면 터치 시 다음 구절로 넘어가는데, 이 때 화면 터치의 의미는 \"읽음\"표시로 되어 진도율에 반영됩니다.\n\n9. 앱 종료 시 마지막으로 읽었던 구절이 저장되어 다시 앱을 시작" +
                "했을 때 이어서 볼 수 있습니다.\n\n10. 본문 구절 화면에서 오른쪽 방향으로 화면을 스와이프(슬라이드)하면 이전 구절로 이동하게 되고 \"읽음\" 표시는 해제가 됩니다.\n\n11. 절 단위가 2절 이상일 때 모든 동작의 기준은 둘 중 혹은 셋 중 제일 왼쪽 구절입니다.\n(현재 위치 저장, 새로운 장 팝업창등등)\n\n12. " +
                "사이드메뉴에서 어느구절을 읽었는지 체크표시로 보여지게 되고, 만약 특정 한장을 읽었다면 장 표시 메뉴칸 각 장 왼쪽에 체크표시가됩니다.( 다 읽지 않았으면 체크표시가 되있지 않습니다.)\n\n13. 스마트폰 기기 음량조절버튼에서 음량 낮추기 버튼 클릭시 화면 터치효과처럼 다음구절로 이동합니다. 또한 음량 높이기 버튼 클릭시 이전구절로 이동합니다. " +
                "\n\n14. 하단부 메뉴 오른쪽에 묵상용 음악을 키고 끌수 있는 버튼이 있어 on/off식으로 음악을 조절할 수 있습니다. 또한 플레이 버튼 오른쪽에 음량 조절할 수 있는 버튼이 있어서 음악 음량의 크기를 조절할 수 있습니다. \n\n\n-메인메뉴-\n\n1. \"이어서 읽기\" 메뉴는 원래 읽고 있던 구절로 다시 이동하여 이어서 보여줍니다.\n\n2. \"진도율 확인\" 메뉴는 현재 자신이 " +
                "얼만큼 읽었는지 진도를 퍼센트와 막대로 보여줍니다. \n\n3. \"즐겨찾기\" 메뉴는 \"이어서 읽기\"에서 위의 6번을 한 제목들과 즐겨찾기한 날짜를 보여줍니다.(이 또한 영한모드에 따라서 언어가 변경됩니다.) 그리고 각 제목을 클릭하면 구절을 보여줍니다." +
                "\n또한, 각 목록을 길게 클릭시 즐겨찾기를 삭제할 수도 있습니다.\n\n\n※성경 정보: \n(영)American Standard Version(free)\n(한)개역한글\n\n※문의 사항: ssg2864@gmail.com\n\n※App Version : 1.1.2\n\nCopyright by Seunggun");
    }
}
