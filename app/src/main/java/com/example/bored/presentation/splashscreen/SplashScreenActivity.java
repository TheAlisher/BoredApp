package com.example.bored.presentation.splashscreen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bored.R;
import com.example.bored.presentation.UI.main.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    //private static final int SPLASH_SCREEN = 2000;

    /*Animation topAnimation;
    Animation bottomAnimation;
    TextView textTitle;
    TextView textSubtitle;*/

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        initializationViews();
        textTitle.setAnimation(topAnimation);
        textSubtitle.setAnimation(bottomAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.start(SplashScreenActivity.this);
            }
        }, SPLASH_SCREEN);*/
        MainActivity.start(this);
        finish();
    }

    /*private void initializationViews() {
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        textTitle = findViewById(R.id.text_splash_title);
        textSubtitle = findViewById(R.id.text_splash_subtitle);
    }*/
}