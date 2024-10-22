package com.dabut.purcow;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    androidx.constraintlayout.widget.ConstraintLayout layout;
    boolean isLightTheme = true;
    SharedPreferences preferences;

    @SuppressLint({"WrongViewCast", "WrongConstant"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        preferences = getSharedPreferences("theme", MODE_PRIVATE);
        if (preferences.contains("isLightTheme")) {
            isLightTheme = preferences.getBoolean("isLightTheme", true);
        }
        if (isLightTheme) {
            setTheme(R.style.Theme_V2rayExample);
        } else {
            setTheme(R.style.Theme_V2rayExample2);
        }


        setContentView(R.layout.activity_splash);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        TextView textView = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        textView.setText("PurCow");
        if (isLightTheme) {
            layout = (androidx.constraintlayout.widget.ConstraintLayout) findViewById(R.id.splaskley);
            layout.setBackgroundResource(R.drawable.tttt);


        } else {
            layout = (androidx.constraintlayout.widget.ConstraintLayout) findViewById(R.id.splaskley);
            layout.setBackgroundResource(R.drawable.pink);

        }


        ImageView image1 = (ImageView) findViewById(R.id.imageView3);

        image1.setBackgroundResource(R.drawable.move);
        ;

        AnimationDrawable anim = (AnimationDrawable) image1.getBackground();
        anim.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }


}