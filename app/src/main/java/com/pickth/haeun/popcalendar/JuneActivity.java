package com.pickth.haeun.popcalendar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.pickth.haeun.popcalendar.view.add.AddActivity;

/**
 * Created by HaEun on 2018-05-28.
 */

public class JuneActivity extends AppCompatActivity {
    TextView monthText;
    View dialogView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_june);
        setTitle("POPcaLENDER");

        monthText = (TextView)findViewById(R.id.tv_main_monthText);

        monthText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = (View) View.inflate(JuneActivity.this, R.layout.dialog1,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(JuneActivity.this);
                dlg.setTitle("사용자 정보 입력");
//                dlg.setIcon(R.drawable.ic_portrait);
                dlg.setView(dialogView);


                dlg.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                        startActivity(intent);
                    }
                });
                dlg.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dlg.show();
            }
        });
    }
}
