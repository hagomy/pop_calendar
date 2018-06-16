package com.pickth.haeun.popcalendar.view.calendar;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pickth.haeun.popcalendar.R;
import com.pickth.haeun.popcalendar.listener.CalendarClickListener;
import com.pickth.haeun.popcalendar.manager.CalendarDataManager;
import com.pickth.haeun.popcalendar.manager.CalendarManger;
import com.pickth.haeun.popcalendar.model.CalendarItem;
import com.pickth.haeun.popcalendar.model.MyDate;
import com.pickth.haeun.popcalendar.view.dialog.CalendarListAdapterTitle;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private CalendarManger mCalendarManager;
    private CalendarClickListener clickListener;

    // 요일을 보여주는 부분과 날짜를 보여주는 부분을 구분하는 상수
    private static final int ITEM_DATE = 0;
    private static final int ITEM_CALENDAR = 1;
    private int dateSize = 7;

    /**
     * 생성자
     * 날짜와 관련된 부분을 다루는 매니저를 초기화한다.
     */
    CalendarAdapter() {
        mCalendarManager = new CalendarManger();
        mCalendarManager.logCalendarInfo();
    }

    /**
     * 요일과 날짜를 뷰 타입으로 구분하게 하는 메소드
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if(position < dateSize) {
            return ITEM_DATE;
        }
        return ITEM_CALENDAR;
    }

    /**
     * 뷰 타입별로 다른 화면(뷰홀더)을 만들어주는 메소드
     * @param parent
     * @param viewType getItemViewType에서 리턴되는 값
     * @return
     */
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

    /**
     * 리턴된 뷰 홀더에 데이터를 입력시킨다.(바인딩)
     * @param holder onCreateViewHolder에서 만들어진 뷰 홀더
     * @param position 몇 번째 아이템인지
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position < dateSize) {
            // 요일이면
            // 요일 뷰 홀더에 바인딩하는 부분
            ((CalendarDateViewHolder)holder).onBind(position);
        } else {
            // 그 밑이면
            int year = mCalendarManager.getCurYear();
            int month = mCalendarManager.getCurMonth();

            int dayNumber = (position + 1) - mCalendarManager.getFirstDay() - dateSize;

            // 1일 이전이나 달의 마지막 일 이후일 때
            if (dayNumber < 1 || dayNumber > mCalendarManager.getLastDay()) {
                return;
            }

            // 날짜 뷰 홀더에 바인딩하는 부분
            ((CalendarViewHolder)holder).onBind(dayNumber, clickListener, new MyDate(year, month, dayNumber));
        }
    }

    /**
     * 총 아이템 갯수
     * @return dataSize(월~일) 7개와 6주인 42를 더한 값
     */
    @Override
    public int getItemCount() {
        return 42+dateSize;
    }

    /**
     *
     * @param listener CalendarActivity에서 생성한 클릭리스너를 받아오는 부분
     */
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

    /**
     * 날짜와 스케쥴을 보여주는 뷰 홀더
     */
    class CalendarViewHolder extends RecyclerView.ViewHolder {
        CalendarDataManager manager;
        ArrayList<CalendarItem> items;
        MyDate mDate;
        CalendarViewHolder(View view) {
            super(view);
        }

        void onBind(int position, final CalendarClickListener listener, MyDate date) {
            // 날짜, 아이템들, 데이터 메니저 초기화
            mDate = date;
            manager = new CalendarDataManager(itemView.getContext());
            items = manager.getItemsByDate(mDate);
            TextView tvDate = itemView.findViewById(R.id.tv_day_date);
            tvDate.setText(""+position);

            // recycler view로 아이템 리스트 뿌려주기
            CalendarListAdapterTitle adapter = new CalendarListAdapterTitle(0);
            RecyclerView rvSchedule = itemView.findViewById(R.id.rv_day_schedule);
            rvSchedule.setAdapter(adapter);
            rvSchedule.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayout.VERTICAL,false));

            //adapte에 item 추가하기
            for(CalendarItem item: items)
                adapter.addItem(item);

            // 새로고침
            adapter.notifyDataSetChanged();

            // 아이템을 눌렀을 때 클릭리스너. CalendarActivity에서 만든 클릭리스너를 CalendarAdapter에 넘겨서 여기서 사용한다.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(items, mDate);
                }
            });
        }
    }

    /**
     * 요일을 보여주는 뷰 홀더
     */
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
