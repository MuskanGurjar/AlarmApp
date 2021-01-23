package com.example.alarmappsample;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class AlarmReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    Dialog dialog;

    @Override
    public void onReceive(final Context context, Intent intent) {

        Log.d("msg", "control reached Broadcast class");
        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);


        try {
            mp.setDataSource(context, Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.alarm));
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mp.start();

        //dialogbox show
        dialog = new Dialog(context);


        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

        final Button snooze = dialog.findViewById(R.id.snooze);
        Button stop = dialog.findViewById(R.id.stop);
//check snooze
        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp = new MediaPlayer();
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("msg", "snooze pressed");
                        mp.start();
                    }
                }, 3* 1000);
                dialog.dismiss();

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();

                Log.d("msg", "stop pressed");

                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
