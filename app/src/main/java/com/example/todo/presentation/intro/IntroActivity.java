package com.example.todo.presentation.intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todo.App;
import com.example.todo.R;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class IntroActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private WormDotsIndicator wormDotsIndicatorIntro;
    private Button buttonSkip;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.viewPager_intro);
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));

        wormDotsIndicatorIntro = findViewById(R.id.dotsIndicator_intro);
        wormDotsIndicatorIntro.setViewPager(viewPager);

        buttonSkip = findViewById(R.id.button_intro_skip);
        buttonNext = findViewById(R.id.button_intro_next);

        viewPager.addOnPageChangeListener(viewListener);
    }

    public void skipClick(View view) {
        App.appPreferences.setIsFirstLaunch();
        finish();
    }

    public void nextClick(View view) {
        if (viewPager.getCurrentItem() < 2) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            if (position == 0) {
                pageRecovery();
                buttonNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextClick(v);
                    }
                });
            }
            if (position == 1) {
                pageRecovery();
                buttonNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextClick(v);
                    }
                });
            }
            if (position == 2) {
                lastPageChanges();
                buttonNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        skipClick(v);
                    }
                });
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void lastPageChanges() {
        buttonSkip.setVisibility(View.GONE);
        buttonNext.setText("Start");
    }

    private void pageRecovery() {
        buttonSkip.setVisibility(View.VISIBLE);
        buttonNext.setText("Next");
    }
}
