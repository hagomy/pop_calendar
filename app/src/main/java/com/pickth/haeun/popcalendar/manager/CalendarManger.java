package com.pickth.haeun.popcalendar.manager;

import android.util.Log;

import java.util.Calendar;

public class CalendarManger {   //달력 만들기
    private String TAG = "CalendarManger";
    private Calendar mCalendar;

    /** 설정한 시작 요일이 언제인지, 일(0), 월(1), 토(6) */
    private int mStartDay;
    private int mCurYear;
    private int mCurMonth;
    private int mLastDay;

    public CalendarManger() {
        mCalendar = Calendar.getInstance();
        calculate();
    }

    /** 년도 */
    public int getCurYear() {
        return mCurYear;
    }

    /** 월 */
    public int getCurMonth() {
        return mCurMonth + 1;
    }

    /** 0~6, 현재 달의 첫 번째 날짜가 몇 번째 부터 시작하는지 */
    public int getFirstDay() {
        return getFirstDay(mCalendar.get(Calendar.DAY_OF_WEEK));
    }

    /** 달의 총 일 수 */
    public int getLastDay() {
        return mLastDay;
    }

    /**
     * 주의 시작 요일을 구하는 메소드
     * 캘린더에서는 1~7을 사용하고 있는데
     * 여기서는 0~6을 사용하기 위함.
     * @return 0은 일요일
     */
    public int getFirstDayOfWeek() {
        switch (mStartDay) {
            case 7: // saturday
            case 2: // monday
                return mStartDay - 1;
            default:
                return 0;
        }
    }

    /**
     * 계산하는 메소드
     */
    private void calculate() {

        // set to the first day of the month
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);

        mStartDay = mCalendar.getFirstDayOfWeek();
        mCurYear = mCalendar.get(Calendar.YEAR);
        mCurMonth = mCalendar.get(Calendar.MONTH);
        mLastDay = getMonthLastDay(mCurYear, mCurMonth);
    }

    /**
     * 달의 일 수를 구하는 메소드
     *
     * @param year
     * @param month
     * @return
     */
    private int getMonthLastDay(int year, int month) {
        switch (month) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                return (31);

            case 3:
            case 5:
            case 8:
            case 10:
                return (30);

            default:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                    return (29);   // 2월 윤년계산
                } else {
                    return (28);
                }
        }
    }


    public void setPreviousMonth() {
        mCalendar.add(Calendar.MONTH, -1);
        calculate();
    }

    public void setNextMonth() {
        mCalendar.add(Calendar.MONTH, 1);
        calculate();
    }

    private int getFirstDay(int dayOfWeek) {
        return dayOfWeek - 1;
    }

    public void logCalendarInfo() {
        Log.d(TAG,
                "\nmStartDay : " + mStartDay +
                        "\nmCurYear : " + mCurYear +
                        "\nmCurMonth : " + mCurMonth +
                        "\ngetFirstDay() : " + getFirstDay() +
                        "\nmLastDay : " + mLastDay);
    }


}
