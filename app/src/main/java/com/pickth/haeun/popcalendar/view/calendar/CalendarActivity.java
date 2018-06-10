package com.pickth.haeun.popcalendar.view.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pickth.haeun.popcalendar.R;
import com.pickth.haeun.popcalendar.listener.CalendarClickListener;
import com.pickth.haeun.popcalendar.util.GridSpacingItemDecoration;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rvCalendar;
    CalendarAdapter mAdapter;

    Button btnPreMonth, btnNextMonth;
    TextView tvMonth, tvPreMonth, tvNextMonth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mAdapter = new CalendarAdapter();
        mAdapter.setCalendarClickListener(new CalendarClickListener() {
            @Override
            public void onClick() {
                // show dialog
                Toast.makeText(CalendarActivity.this, "test", Toast.LENGTH_SHORT).show();
            }
        });

        // recycler view setting
        rvCalendar = findViewById(R.id.rv_calendar);
        rvCalendar.setAdapter(mAdapter);
        rvCalendar.setLayoutManager(new GridLayoutManager(this, 7));
        rvCalendar.addItemDecoration(new GridSpacingItemDecoration(this, 7, 3, false));

        mAdapter.notifyDataSetChanged();

        initializeView();
        setMonth();
    }

    private void initializeView() {
        btnPreMonth = findViewById(R.id.btn_calendar_pre_month);
        btnNextMonth = findViewById(R.id.btn_calendar_next_month);
        tvMonth = findViewById(R.id.tv_calendar_month);
        tvPreMonth = findViewById(R.id.tv_calendar_pre_month);
        tvNextMonth = findViewById(R.id.tv_calendar_next_month);
        btnPreMonth.setOnClickListener(this);
        btnNextMonth.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btnPreMonth.getId()) {
            mAdapter.setPreviousMonth();
            setMonth();
        } else if(view.getId() == btnNextMonth.getId()) {
            mAdapter.setNextMonth();
            setMonth();
        }
    }

    private void setMonth() {
        int month = mAdapter.getMonth();
        int preMonth = month-1;
        int nextMonth = month+1;

        if(month == 1) {
            preMonth = 12;
        } else if(month == 12) {
            nextMonth = 1;
        }

        tvMonth.setText(month+"월");
        tvPreMonth.setText(preMonth+"월");
        tvNextMonth.setText(nextMonth+"월");
    }
}
