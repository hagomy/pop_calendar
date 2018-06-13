package com.pickth.haeun.popcalendar.view.pop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.pickth.haeun.popcalendar.R;
import com.pickth.haeun.popcalendar.view.calendar.CalendarActivity;

import static android.view.View.inflate;

/**
 * Created by jinsil on 2018-06-12.
 */

public class Callpop extends AppCompatActivity {

    protected  void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_pop);
    }

    LinearLayout container = (LinearLayout)findViewById(R.id.page);
    View view = inflate(this, R.layout.item_pop, null);
    //container.addView(view);

    TextView textView = (TextView)container.findViewById(R.id.popup);

//일정이 있습니다, 버튼 생성 후 인프레이터로 연결

}