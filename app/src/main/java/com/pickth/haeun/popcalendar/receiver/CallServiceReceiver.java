package com.pickth.haeun.popcalendar.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.pickth.haeun.popcalendar.manager.CalendarDataManager;
import com.pickth.haeun.popcalendar.model.CalendarItem;

import java.util.ArrayList;

/**
 * Created by jinsil on 2018-06-04.
 */

public class CallServiceReceiver extends BroadcastReceiver {
    public String TAG = getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle bundle = intent.getExtras();

        if(action.equals("android.intent.action.PHONE_STATE")){
            String state = bundle.getString(TelephonyManager.EXTRA_STATE);
            if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                Log.d(TAG, "EXTRA_STATE_IDLE");
            }else if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String phoneNumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Log.d(TAG, "EXTRA_STATE_RINGING INCOMMING NUMBER : " + phoneNumber);

                CalendarDataManager dataManager = new CalendarDataManager(context);
                ArrayList<CalendarItem> arrayList = dataManager.getItemsByHuman(phoneNumber);
                String array = "";
                for (int i=0; i<arrayList.size(); i++){
                    array += arrayList.get(i).date.getYear() +"-"+ arrayList.get(i).date.getMonth() +"-"+ arrayList.get(i).date.getDay();
                    array += "  "+ arrayList.get(i).title +"  "+ arrayList.get(i).memo + "\n";
                }
                Toast.makeText(context, array ,Toast.LENGTH_LONG).show();

            }else if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                Log.d(TAG, "EXTRA_STATE_OFFHOOK");
            }
        }else if(action.equals(intent.ACTION_NEW_OUTGOING_CALL)){
            Log.d(TAG, "OUTGOING CALL :" + bundle.getString(Intent.EXTRA_PHONE_NUMBER));
       }
    }
}
