package com.pickth.haeun.popcalendar.view.pop;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pickth.haeun.popcalendar.R;

import static android.view.View.inflate;

/**
 * Created by jinsil on 2018-06-12.
 */

public class Callpop extends AppCompatActivity {
    String phoneNumber;

    protected  void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_pop);
    }

    public Callpop(Context context, String phone){
        phoneNumber = phone;
    }

    LinearLayout container = (LinearLayout)findViewById(R.id.ll_pop_page);
    View view = inflate(this, R.layout.item_pop, null);
    //container.addView(view);

    TextView textView = (TextView)container.findViewById(R.id.tv_pop_sc);

//일정이 있습니다, 버튼 생성 후 인프레이터로 연결

}