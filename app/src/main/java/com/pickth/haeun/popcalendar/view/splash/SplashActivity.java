package com.pickth.haeun.popcalendar.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pickth.haeun.popcalendar.view.calendar.CalendarActivity;

public class SplashActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 처음 시작하는 화면
        // 바로 CalendarActivity로 넘김
        startActivity(new Intent(this, CalendarActivity.class));
        finish();
    }
}
