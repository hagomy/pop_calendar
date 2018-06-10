package com.pickth.haeun.popcalendar.view.calendar;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pickth.haeun.popcalendar.R;
import com.pickth.haeun.popcalendar.listener.CalendarClickListener;
import com.pickth.haeun.popcalendar.manager.CalendarManger;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<CalendarItem> mItems = new ArrayList<>(45);
    private CalendarManger mCalendarManager;
    private CalendarClickListener clickListener;

    public static int oddColor = Color.rgb(225, 225, 225);
    public static int headColor = Color.rgb(12, 32, 158);

    private static final int ITEM_DATE = 0;
    private static final int ITEM_CALENDAR = 1;
    private int dateSize = 7;

    CalendarAdapter() {
        mCalendarManager = new CalendarManger();
        mCalendarManager.logCalendarInfo();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType) {
            case ITEM_DATE:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_calendar_date, parent, false);
                return new CalendarDateViewHolder(itemView);
            case ITEM_CALENDAR:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_calendar, parent, false);
                return new CalendarViewHolder(itemView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position < dateSize) {
            // 요일이면
            ((CalendarDateViewHolder)holder).onBind(position);
        } else {
            // 그 밑이면
            int dayNumber = (position + 1) - mCalendarManager.getFirstDay() - dateSize;

            // 일요일부터 시작일 때 맨 윗줄을 띄움
//            if(mCalendarManager.getFirstDay() == 0) {
//                dayNumber -= 7;
//            }

            // 1일 이전이나 달의 마지막 일 이후일 때
            if (dayNumber < 1 || dayNumber > mCalendarManager.getLastDay()) {
                return;
            }

            ((CalendarViewHolder)holder).onBind(new CalendarItem(), dayNumber, clickListener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position < dateSize) {
            return ITEM_DATE;
        }
        return ITEM_CALENDAR;
    }

    @Override
    public int getItemCount() {
        return 42+dateSize;
    }

    public void setCalendarClickListener(CalendarClickListener listener) {
        clickListener = listener;
    }

    public int getMonth() {
        return mCalendarManager.getCurMonth();
    }

    public void setPreviousMonth() {
        mCalendarManager.setPreviousMonth();
        notifyDataSetChanged();
    }

    public void setNextMonth() {
        mCalendarManager.setNextMonth();
        notifyDataSetChanged();
    }

    class CalendarViewHolder extends RecyclerView.ViewHolder {
        CalendarViewHolder(View view) {
            super(view);
        }

        void onBind(CalendarItem item, int position, final CalendarClickListener listener) {
            TextView tvDate = itemView.findViewById(R.id.tv_day_date);
            tvDate.setText(""+position);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick();
                }
            });
        }
    }

    class CalendarDateViewHolder extends RecyclerView.ViewHolder {
        private String[] week = {"일", "월", "화", "수", "목", "금", "토"};
        CalendarDateViewHolder(View view) {
            super(view);
        }

        void onBind(int position) {
            TextView tvDate = itemView.findViewById(R.id.tv_calendar_date_name);
            tvDate.setText(week[position]+"요일");
        }
    }
}
