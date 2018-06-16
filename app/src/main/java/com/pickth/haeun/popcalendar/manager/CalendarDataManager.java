package com.pickth.haeun.popcalendar.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pickth.haeun.popcalendar.model.CalendarItem;
import com.pickth.haeun.popcalendar.model.MyDate;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CalendarDataManager {  //메모 저장
    private Context mContext;
    private ArrayList<CalendarItem> mItems = new ArrayList<>();

    public CalendarDataManager(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * mItems가 비어있으면 파일에서 가져오는 메소드
     * @return
     */
    public ArrayList<CalendarItem> getCalendarItems() {
        if(mItems.size() == 0) {
            String json = mContext
                    .getSharedPreferences("calendar_item", Context.MODE_PRIVATE)
                    .getString("calendars", "");

            if(json == "") return mItems;

            Type type = new TypeToken<ArrayList<CalendarItem>>() {}.getType();
            mItems = new Gson().fromJson(json, type);
        }

        return mItems;
    }

    /**
     * mItems를 파일에 저장하는 메소드
     * mItems에 아이템을 추가하거나 삭제했을 때 호출한다.
     */
    public void notifyDataSetChanged() {
        mContext.getSharedPreferences("calendar_item", Context.MODE_PRIVATE)
                .edit()
                .putString("calendars", new Gson().toJson(mItems).toString())
                .apply();
    }

    /**
     * 캘린더 아이템을 추가하는 메소드
     * @param item
     */
    public void addItem(CalendarItem item) {
        getCalendarItems().add(item);
        notifyDataSetChanged();
    }

    /**
     * 해당 날짜의 아이템들을 모두 가져오기
     * @return
     */
    public ArrayList<CalendarItem> getItemsByDate(MyDate date) {
        ArrayList<CalendarItem> items = new ArrayList<>();
        for(CalendarItem item : getCalendarItems()) {
            if(item.isEqualDate(date)) {
                items.add(item);
            }
        }
        return items;
    }

    /**
     * 해당 사람과의 일정을 모두 가져오기
     * @param phoneNumber
     * @return
     */
    public ArrayList<CalendarItem> getItemsByHuman(String phoneNumber) {
        ArrayList<CalendarItem> items = new ArrayList<>();
        ArrayList<CalendarItem> getItems = getCalendarItems();

        for(int i=0; i<getItems.size(); i++) {
            CalendarItem tmp = getItems.get(i);
            if (tmp.human.getTel().equals(phoneNumber)) {
                items.add(tmp);
            }
        }
        return items;
    }

}
