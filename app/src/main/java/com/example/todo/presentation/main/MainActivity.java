package com.example.todo.presentation.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.todo.R;
import com.example.todo.presentation.intro.IntroActivity;
import com.google.android.material.slider.Slider;

public class MainActivity extends AppCompatActivity {

    public static final String SP_KEY = "isShown";
    private Spinner spinnerCategory;
    private Slider sliderPrice;
    private Slider sliderAccessibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isShown()) {
            startActivity(new Intent(this, IntroActivity.class));
        }
        spinnerCategory = findViewById(R.id.spinner_main_category);
        sliderPrice = findViewById(R.id.slider_price);
        sliderAccessibility = findViewById(R.id.slider_accessibility);
        createCategoryDropdown();
        setSliderValues();
    }

    private boolean isShown() {
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        return preferences.getBoolean(SP_KEY, true);
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

    private void setSliderValues() {
        sliderPrice.setValues(50.0F, 85.0F);
        sliderAccessibility.setValues(50.0F, 85.0F);
    }
}
