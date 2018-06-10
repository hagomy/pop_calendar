package com.pickth.haeun.popcalendar.view.calendar;

public class CalendarItem {
    private int dayValue;

    public CalendarItem() {

    }

    public CalendarItem(int day) {
        dayValue = day;
    }

    public int getDay() {
        return dayValue;
    }

    public void setDay(int day) {
        this.dayValue = day;
    }
}
