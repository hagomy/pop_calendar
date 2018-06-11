package com.pickth.haeun.popcalendar.view.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pickth.haeun.popcalendar.R;
import com.pickth.haeun.popcalendar.manager.CalendarDataManager;
import com.pickth.haeun.popcalendar.model.CalendarItem;
import com.pickth.haeun.popcalendar.model.MyDate;
import com.pickth.haeun.popcalendar.util.StringUtil;

/**
 * Created by HaEun on 2018-05-28.
 */

public class AddActivity extends AppCompatActivity {
    MyDate date;
    EditText etTitle, etMemo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // CalendarListDialog 에서 넘긴 연도, 월, 날짜를 받아오는 부분
        int year = getIntent().getIntExtra("year", StringUtil.INSTANCE.getYear());
        int month = getIntent().getIntExtra("month", StringUtil.INSTANCE.getMonth());
        int day = getIntent().getIntExtra("day", StringUtil.INSTANCE.getDay());
        date = new MyDate(year, month, day);

        TextView tvDate = findViewById(R.id.tv_add_date);
        tvDate.setText(year+"년"+month+"월"+day+"일");
        etTitle = findViewById(R.id.et_add_title);
        etMemo = findViewById(R.id.et_add_memo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add :
                String title = etTitle.getText().toString();
                String memo = etMemo.getText().toString();
                if(title.length() == 0 && memo.length() == 0) {
                    Toast.makeText(this, "값을 입력하세요", Toast.LENGTH_SHORT).show();
                    return false;
                }

                // 입력한 값을 파일에 저장하는 부분
                new CalendarDataManager(AddActivity.this).addItem(new CalendarItem(title, memo, date));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
