package com.example.todo.presentation.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.data.BoredAPIClient;
import com.example.todo.model.BoredAction;
import com.example.todo.presentation.intro.IntroActivity;
import com.google.android.material.slider.RangeSlider;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;

import static com.example.todo.R.drawable.icon_favorite_blue;
import static com.example.todo.R.drawable.icon_selected_favorite_red;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private TextView textCategory;
    private ImageView imageFavorite;
    private LottieAnimationView lottieAnimationLike;
    private TextView textExplore;
    private TextView textPrice;
    private View viewPersonCircle1;
    private View viewPersonCircle2;
    private View viewPersonCircle3;
    private View viewPersonCircle4;
    private ProgressBar progressBarAccessibility;
    private Spinner spinnerCategory;
    private RangeSlider rangeSliderPrice;
    private RangeSlider rangeSliderAccessibility;

    private String spinnerSelectedValues;

    private List<Float> rangeSliderSelectedPrice;
    private Float rangeSliderSelectedMinPrice;
    private Float rangeSliderSelectedMaxPrice;

    private List<Float> rangeSliderSelectedAccessibility;
    private Float rangeSliderSelectedMinAccessibility;
    private Float rangeSliderSelectedMaxAccessibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (App.appPreferences.isFirstLaunch()) {
            startActivity(new Intent(this, IntroActivity.class));
        }

        initializationViews();
        createSpinnerCategory();
        spinnerGetSelectedValues();
        //rangeSliderPriceSetLabelFormatter();
        rangeSliderPriceGetSelectedValues();
        rangeSliderAccessibilityGetSelectedValues();
    }

    private void initializationViews() {
        textCategory = findViewById(R.id.text_main_category);
        imageFavorite = findViewById(R.id.image_main_favorite);
        lottieAnimationLike = findViewById(R.id.lottieAnimation_main_like);
        textExplore = findViewById(R.id.text_main_explore);
        textPrice = findViewById(R.id.text_main_free);
        viewPersonCircle1 = findViewById(R.id.view_main_person_circle_1);
        viewPersonCircle2 = findViewById(R.id.view_main_person_circle_2);
        viewPersonCircle3 = findViewById(R.id.view_main_person_circle_3);
        viewPersonCircle4 = findViewById(R.id.view_main_person_circle_4);
        progressBarAccessibility = findViewById(R.id.progressBar_main_accessibility);
        spinnerCategory = findViewById(R.id.spinner_main_category);
        rangeSliderPrice = findViewById(R.id.rangeSlider_price);
        rangeSliderAccessibility = findViewById(R.id.rangeSlider_accessibility);
    }

    public void mainAPINextClick(View view) {
        if (spinnerSelectedValues != null) {
            if (spinnerSelectedValues.equals("RANDOM")) {
                spinnerSelectedValues = null;
            } else if (spinnerSelectedValues.equals("Category")) {
                Toast.makeText(this, "Выберите категорию", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        App.boredAPIClient.getAction(
                spinnerSelectedValues,
                rangeSliderSelectedMinPrice,
                rangeSliderSelectedMaxPrice,
                rangeSliderSelectedMinAccessibility,
                rangeSliderSelectedMaxAccessibility,
                new BoredAPIClient.BoredActionCallback() {
                    @Override
                    public void onSuccess(BoredAction boredAction) {
                        textCategory.setText(boredAction.getType());
                        textExplore.setText(boredAction.getActivity());
                        textPrice.setText(boredAction.getPrice().toString() + "$");
                        switch (boredAction.getParticipants()) {
                            case 1:
                                recoveryParticipantsViews();
                                invisibleParticipantsCase1();
                                break;
                            case 2:
                                recoveryParticipantsViews();
                                invisibleParticipantsCase2();
                                break;
                            case 3:
                                recoveryParticipantsViews();
                                invisibleParticipantsCase3();
                                break;
                            case 4:
                                recoveryParticipantsViews();
                                invisibleParticipantsCase4();
                                break;
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            progressBarAccessibility.setProgress((int) (boredAction.getAccessibility() * 100), true);
                        }
                        Log.d("anim", boredAction.toString());
                    }

                    @Override
                    public void onFailure(Exception E) {
                        Log.d("anim", E.getMessage());
                    }
                });
    }


    private boolean flag = true;

    public void mainLikeClick(View view) {
        if (flag) {
            lottieAnimationLike.setVisibility(View.VISIBLE);
            lottieAnimationLike.playAnimation();
            imageFavorite.setImageResource(icon_selected_favorite_red);
        } else {
            lottieAnimationLike.setVisibility(View.INVISIBLE);
            imageFavorite.setImageResource(icon_favorite_blue);
        }
        flag = !flag;
    }

    private void recoveryParticipantsViews() {
        viewPersonCircle1.setVisibility(View.VISIBLE);
        viewPersonCircle2.setVisibility(View.VISIBLE);
        viewPersonCircle3.setVisibility(View.VISIBLE);
        viewPersonCircle4.setVisibility(View.VISIBLE);
    }

    private void invisibleParticipantsCase1() {
        viewPersonCircle2.setVisibility(View.INVISIBLE);
        viewPersonCircle3.setVisibility(View.INVISIBLE);
        viewPersonCircle4.setVisibility(View.INVISIBLE);
    }

    private void invisibleParticipantsCase2() {
        viewPersonCircle1.setVisibility(View.INVISIBLE);
        viewPersonCircle3.setVisibility(View.INVISIBLE);
        viewPersonCircle4.setVisibility(View.INVISIBLE);
    }

    private void invisibleParticipantsCase3() {
        viewPersonCircle1.setVisibility(View.INVISIBLE);
        viewPersonCircle2.setVisibility(View.INVISIBLE);
        viewPersonCircle4.setVisibility(View.INVISIBLE);
    }

    private void invisibleParticipantsCase4() {
        viewPersonCircle1.setVisibility(View.INVISIBLE);
        viewPersonCircle2.setVisibility(View.INVISIBLE);
        viewPersonCircle3.setVisibility(View.INVISIBLE);
    }

    private void createSpinnerCategory() {
        String[] dropdownCategory = getResources().getStringArray(R.array.category);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this, R.layout.custom_spinner_item, dropdownCategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

    private void spinnerGetSelectedValues() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                spinnerSelectedValues = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void rangeSliderPriceSetLabelFormatter() {
        rangeSliderPrice.setLabelFormatter(value -> {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            format.setMaximumFractionDigits(0);
            format.setCurrency(Currency.getInstance("USD"));
            return format.format(value);
        });
    }

    private void rangeSliderPriceGetSelectedValues() {
        rangeSliderPrice.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                rangeSliderSelectedPrice = slider.getValues();
                rangeSliderSelectedMinPrice = rangeSliderSelectedPrice.get(0);
                rangeSliderSelectedMaxPrice = rangeSliderSelectedPrice.get(1);
            }
        });
    }

    private void rangeSliderAccessibilityGetSelectedValues() {
        rangeSliderAccessibility.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                rangeSliderSelectedAccessibility = slider.getValues();
                rangeSliderSelectedMinAccessibility = rangeSliderSelectedAccessibility.get(0);
                rangeSliderSelectedMaxAccessibility = rangeSliderSelectedAccessibility.get(1);
            }
        });
    }
}
