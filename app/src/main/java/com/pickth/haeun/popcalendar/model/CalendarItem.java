package com.pickth.haeun.popcalendar.model;

import com.pickth.haeun.popcalendar.util.StringUtil;

public class CalendarItem {
    /**
     * 연도, 월, 날짜 정보가 있는 클래스
     */
    private MyDate date;

    public String title;
    public String memo;
    public Human human;

    public CalendarItem(String title, String memo) {
        this(title, memo, new MyDate(StringUtil.INSTANCE.getYear(), StringUtil.INSTANCE.getMonth(), StringUtil.INSTANCE.getDay()));
    }

    public CalendarItem(String title, String memo, MyDate date) {
        this.title = title;
        this.memo = memo;
        this.date = date;
    }

    public boolean isEqualDate(MyDate date) {
        return this.date.equals(date);
    }
}
