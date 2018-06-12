package com.pickth.haeun.popcalendar.content;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.pickth.haeun.popcalendar.R;
import com.pickth.haeun.popcalendar.view.add.AddActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//https://blog.naver.com/qkwldqk/50147100401
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
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " asc");

        while (c.moveToNext()){
            HashMap<String, String> map = new HashMap<String, String>();
            String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
            map.put("name",name);

            Cursor phoneCursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                    null,
                    null
            );

            if (phoneCursor.moveToFirst()){
                String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                map.put("phone", number);
            }

            phoneCursor.close();
            dataList.add(map);
        }//end while
        c.close();

        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
                dataList,
                android.R.layout.simple_list_item_2,
                //R.layout.item_contact_list,
                // LayoutInflater.from(context).inflate(R.layout.item_pop, null),
                //LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_contact_list, null),
                new String[]{"name", "phone"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = dataList.get(i).get("name");
                String number = dataList.get(i).get("phone");
                Intent outIntent = new Intent(getApplicationContext(), AddActivity.class);
                outIntent.putExtra("Number", number);
                outIntent.putExtra("Name", name);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}
