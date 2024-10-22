package com.dabut.purcow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Video extends AppCompatActivity {
    LinearLayout layout;
    boolean isLightTheme = true;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       SharedPreferences preferences = getSharedPreferences("theme", MODE_PRIVATE);
        if (preferences.contains("isLightTheme")) {
            isLightTheme = preferences.getBoolean("isLightTheme", true);
        }
        if (isLightTheme) {
            setTheme(R.style.Theme_V2rayExample);
        } else {
            setTheme(R.style.Theme_V2rayExample2);
        }




        setContentView(R.layout.activity_video);

        if (isLightTheme) {
            layout = (LinearLayout) findViewById(R.id.videoley);
            layout.setBackgroundResource(R.drawable.tttt);



        } else {
            layout = (LinearLayout ) findViewById(R.id.videoley);
            layout.setBackgroundResource(R.drawable.pink);

        }
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        TextView textView = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        textView.setText("PurCow");
      //  VideoView videoView = findViewById(R.id.videoView);
      //  videoView.setVideoPath("https://purnet.ir/assets/img/clients/aaa.webm");
      //  videoView.start();
      //  String url = "https://purnet.ir/assets/img/clients/loop.webm";
        final VideoView videoView = findViewById(R.id.videoView); //id in your xml file
        String path = "android.resource://" + getPackageName() + "/" + R.raw.pppppp;
        videoView.setVideoURI(Uri.parse(path)); //the string of the URL mentioned above
        MediaController mc = new MediaController(videoView.getContext());
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);
        videoView.requestFocus();
        videoView.start();
// Initialize Firebase



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mc.addOnUnhandledKeyEventListener((v, event) -> {
                //Handle BACK button
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP)
                {
                    mc.hide(); //Hide mediaController,according to your needs, you can also called here onBackPressed() or finish()
                }
                return true;
            });
        }

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                boolean running = true;
                final int duration = videoView.getDuration();

                new Thread(new Runnable() {
                    public void run() {
                        do{
                            TextView t = (TextView) findViewById(R.id.textv);

                            t.post(new Runnable() {
                                public void run() {
                                    int time = (duration - videoView.getCurrentPosition())/1000;
                                    t.setText(time+getString(R.string.secc));
                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(!running) break;
                        }
                        while(videoView.getCurrentPosition()<duration);
                    }
                }).start();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Video.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}