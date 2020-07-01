package com.example.todo.presentation.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.data.BoredAPIClient;
import com.example.todo.model.BoredAction;
import com.example.todo.presentation.intro.IntroActivity;
import com.google.android.material.slider.RangeSlider;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private Spinner spinnerCategory;
    private RangeSlider rangeSliderPrice;
    private RangeSlider rangeSliderAccessibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (App.appPreferences.isFirstLaunch()) {
            startActivity(new Intent(this, IntroActivity.class));
        }
        App.boredAPIClient.getAction(null, null, new BoredAPIClient.BoredActionCallback() {
            @Override
            public void onSuccess(BoredAction boredAction) {
                Log.d("anim", boredAction.toString());
            }

            @Override
            public void onFailure(Exception E) {
                Log.d("anim", E.getMessage());
            }
        });

        initializationViews();
        createCategoryDropdown();
    }

    private void initializationViews() {
        spinnerCategory = findViewById(R.id.spinner_main_category);
        rangeSliderPrice = findViewById(R.id.rangeSlider_price);
        rangeSliderAccessibility = findViewById(R.id.rangeSlider_accessibility);
    }

    public void mainNextClick(View view) {

    }

    private void createCategoryDropdown() {
        String[] dropdownCategory = getResources().getStringArray(R.array.category);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this, R.layout.custom_spinner_item, dropdownCategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }
}
