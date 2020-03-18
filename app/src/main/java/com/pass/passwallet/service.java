package com.pass.passwallet;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by madhav on 4/20/2016.
 */
public class service extends Service {
    @Nullable

    int counter = 30;
    public IBinder onBind(Intent intent) {


        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(service.this, "Passwallet Unlocked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Toast.makeText(service.this, "Passwallet Locked due to Maximum failed passcode!", Toast.LENGTH_SHORT).show();

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {


                final SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(service.this);
                final SharedPreferences.Editor edit = spf.edit();
                edit.putInt("sec" , counter--);
                edit.commit();
            }

            public void onFinish() {
                final SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(service.this);
                final SharedPreferences.Editor edit = spf.edit();
                edit.putInt("counter" , 3);
                edit.commit();


                stopSelf();
            }
        }.start();


        return super.onStartCommand(intent, flags, startId);


    }
}
