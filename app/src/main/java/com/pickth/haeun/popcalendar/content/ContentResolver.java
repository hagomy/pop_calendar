package com.pickth.haeun.popcalendar.content;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.pickth.haeun.popcalendar.R;
import com.pickth.haeun.popcalendar.view.add.AddActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//http://mainia.tistory.com/4924

public class ContentResolver extends AppCompatActivity {
    ArrayList<Map<String, String>> dataList;
    ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mListView = (ListView)findViewById(R.id.lv_contact);

        dataList = new ArrayList<Map<String, String>>();
        Cursor c = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null,   //가져올 컬럼 이름 목록, null이면 모든 컬럼
                null,   //where 절에 해당하는 내용
                null,   //selection에서 ?로 표시한 곳에 들어갈 데이터
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " asc" //정렬을 위한 order by 구문
        );

        while (c.moveToNext()){
            HashMap<String, String> map = new HashMap<String, String>();

            //연락처 id 값
            String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

            //연락처 대표 이름
            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
            map.put("name",name);

            //ID로 전화 정보 조회
            Cursor phoneCursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                    null,
                    null
            );

            //데이터가 있는 경우
            if (phoneCursor.moveToFirst()){
                String tel = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                map.put("phone", tel);
            }
            phoneCursor.close();
            dataList.add(map);
        }//end while
        c.close();

        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
                dataList,
                android.R.layout.simple_list_item_2,
                new String[]{"name", "phone"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = dataList.get(i).get("name");  //선택된 연락처의 이름
                String tel = dataList.get(i).get("phone");   //선택된 연락처의 번호
                Intent outIntent = new Intent(getApplicationContext(), AddActivity.class);
                outIntent.putExtra("Tel", tel);
                outIntent.putExtra("Name", name);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}
