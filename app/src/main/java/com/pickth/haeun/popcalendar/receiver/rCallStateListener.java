package com.pickth.haeun.popcalendar.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jinsil on 2018-06-04.
 */

public class rCallStateListener extends BroadcastReceiver {
    public  String TAG = getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle bundle = intent.getExtras();

        if(action.equals("android.intent.action.PHONE_STATE")) {
            String state = bundle.getString(TelephonyManager.EXTRA_STATE_IDLE);
            if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                Log.d(TAG, "EXTRA_STATE_IDLE");
                Toast.makeText(context,  bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER), Toast.LENGTH_LONG).show();
            }else if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Log.d(TAG, "EXTRA_STATE_RINGING INCOMMING NUMBER : " + bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER));
                Toast.makeText(context,  bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER), Toast.LENGTH_LONG).show();
            }else if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                Log.d(TAG, "EXTRA_STATE_OFFHOOK");
                Toast.makeText(context,  bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER), Toast.LENGTH_LONG).show();
            }
        }
    }
}
