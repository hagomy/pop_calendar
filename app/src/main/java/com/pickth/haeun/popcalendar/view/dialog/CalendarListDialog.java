package com.pickth.haeun.popcalendar.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pickth.haeun.popcalendar.R;
import com.pickth.haeun.popcalendar.model.CalendarItem;
import com.pickth.haeun.popcalendar.model.MyDate;
import com.pickth.haeun.popcalendar.view.add.AddActivity;

import java.util.ArrayList;

public class CalendarListDialog extends Dialog {
    MyDate date;
    RecyclerView rvList;
    TextView tvDate;
    private ArrayList<CalendarItem> mItems;
    
    public CalendarListDialog(Context context, ArrayList<CalendarItem> items, MyDate date) {
        super(context);
        this.date = date;
        mItems = items;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_calendar_list);

        // 리사이클러 뷰
        rvList = findViewById(R.id.rv_dialog_list);
        CalendarListAdapter adapter = new CalendarListAdapter(1);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false));

        // 아이템 추가
        for(CalendarItem item: mItems) {
            adapter.addItem(item);
        }

        // 새로고침
        adapter.notifyDataSetChanged();

        tvDate = (TextView)findViewById(R.id.tv_dialog_date);
        tvDate.setText(date.getYear() + "년 " + date.getMonth() + "월 " + date.getDay() + "일");

        // 버튼
        Button btnAdd = findViewById(R.id.btn_dialog_add);
        Button btnCancel = findViewById(R.id.btn_dialog_cancel);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 날짜 정보를 담아서 AddActivity에 넘기는 부분
                Intent addIntent = new Intent(getContext(), AddActivity.class);
                addIntent.putExtra("year", date.getYear());
                addIntent.putExtra("month", date.getMonth());
                addIntent.putExtra("day", date.getDay());
                getContext().startActivity(addIntent);
                dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
