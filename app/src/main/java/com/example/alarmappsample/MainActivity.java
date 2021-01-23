package com.example.alarmappsample;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.io.IOException;
import java.util.Calendar;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    TimePicker timePicker;

    Button stop, snooze, setAlarm;
    LinearLayout buttonlayout;
    AlarmManager alarmManager;
    PendingIntent pi;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timePicker = findViewById(R.id.timepicker);
        snooze = findViewById(R.id.snooze);
        stop = findViewById(R.id.stop);
        buttonlayout = findViewById(R.id.buttonlayout);
        setAlarm = findViewById(R.id.setAlarm);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(this, AlarmReceiver.class);
        pi = PendingIntent.getBroadcast(this, 0, myIntent, 0);

        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                //Long time=calendar.getTimeInMillis()-50*1000;
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                Log.d("msg", "Alarm set");
                setAlarm.setEnabled(false);

            }
        });





    }


}
/**/
