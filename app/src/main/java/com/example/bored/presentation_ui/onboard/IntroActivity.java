package com.example.bored.presentation_ui.onboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bored.App;
import com.example.bored.R;
import com.example.bored.presentation_ui.main.MainActivity;
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
        initializationViews();
        createViewPagerWithDotsIndicator();
    }

    private void initializationViews() {
        viewPager = findViewById(R.id.viewPager_intro);
        wormDotsIndicatorIntro = findViewById(R.id.dotsIndicator_intro);
        buttonSkip = findViewById(R.id.button_intro_skip);
        buttonNext = findViewById(R.id.button_intro_next);
    }

    private void createViewPagerWithDotsIndicator() {
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        wormDotsIndicatorIntro.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(viewListener);
    }

    public void introSkipClick(View view) {
        App.appPreferences.setLaunched();
        MainActivity.start(this);
        overridePendingTransition(R.anim.static_animation, R.anim.zoom_out);
        finish();
    }

    public void introNextClick(View view) {
        if (viewPager.getCurrentItem() < 2) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        }
    }

    private ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
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
                        introNextClick(v);
                    }
                });
            }
            if (position == 1) {
                pageRecovery();
                buttonNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        introNextClick(v);
                    }
                });
            }
            if (position == 2) {
                lastPageChanges();
                buttonNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        introSkipClick(v);
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
        buttonNext.setText(R.string.button_intro_start);
    }

    private void pageRecovery() {
        buttonSkip.setVisibility(View.VISIBLE);
        buttonNext.setText(R.string.button_intro_next);
    }
}
