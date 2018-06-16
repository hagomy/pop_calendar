package com.pickth.haeun.popcalendar.view.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pickth.haeun.popcalendar.R;
import com.pickth.haeun.popcalendar.content.ContentResolverActivity;
import com.pickth.haeun.popcalendar.manager.CalendarDataManager;
import com.pickth.haeun.popcalendar.model.CalendarItem;
import com.pickth.haeun.popcalendar.model.Human;
import com.pickth.haeun.popcalendar.model.MyDate;

import java.util.Calendar;

/**
 * Created by HaEun on 2018-05-28.
 */

public class AddActivity extends AppCompatActivity {
    MyDate date;
    EditText etTitle, etMemo;
    Button btnContact;
    TextView tvName, tvTel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setElevation(0);

        // CalendarListDialog 에서 넘긴 연도, 월, 날짜를 받아오는 부분
        int year = getIntent().getIntExtra("year", Calendar.getInstance().get(Calendar.YEAR));
        int month = getIntent().getIntExtra("month", Calendar.getInstance().get(Calendar.MONTH)+1);
        int day = getIntent().getIntExtra("day", Calendar.getInstance().get(Calendar.DATE));
        date = new MyDate(year, month, day);

        TextView tvDate = findViewById(R.id.tv_add_date);
        tvDate.setText(year+"년" + month+"월" + day+"일");
        etTitle = findViewById(R.id.et_add_title);
        etMemo = findViewById(R.id.et_add_memo);

        btnContact = (Button)findViewById(R.id.btn_add_contact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContentResolverActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 우측에 더하기 메뉴 생성
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tvName = (TextView)findViewById(R.id.tv_add_name);
        tvTel = (TextView)findViewById(R.id.tv_add_tel);

        if (resultCode == RESULT_OK){
            String name = data.getStringExtra("Name");
            tvName.setText(name);
            String tel = data.getStringExtra("Tel");
            tvTel.setText(tel);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add :
                String title = etTitle.getText().toString();
                String memo = etMemo.getText().toString();
                String name = tvName.getText().toString();
                String tel = tvTel.getText().toString();
                Human human = new Human(name, tel);

                if(title.length() == 0) {
                    Toast.makeText(this, "값을 입력하세요", Toast.LENGTH_SHORT).show();
                    return false;
                }

                // 입력한 값을 파일에 저장하는 부분
                new CalendarDataManager(AddActivity.this).addItem(new CalendarItem(title, memo, date, human));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
