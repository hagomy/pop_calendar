package com.pickth.haeun.popcalendar.listener;

import com.pickth.haeun.popcalendar.model.CalendarItem;
import com.pickth.haeun.popcalendar.model.MyDate;

import java.util.ArrayList;

public interface CalendarClickListener {

    /**
     * 캘린더 아이템을 눌렀을 때 호출할 메소드
     * @param items 해당 날짜의 스케쥴 리스트들
     * @param date 해당 날짜의 정보
     */
    void onClick(ArrayList<CalendarItem> items, MyDate date);
}
