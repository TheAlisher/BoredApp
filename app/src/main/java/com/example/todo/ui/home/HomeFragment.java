package com.example.todo.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.google.android.material.slider.RangeSlider;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;

import static com.example.todo.R.drawable.icon_favorite_blue;
import static com.example.todo.R.drawable.icon_selected_favorite_red;

public class HomeFragment extends Fragment {

    private TextView textCategory;
    private ImageView imageFavorite;
    private LottieAnimationView lottieAnimationLike;
    private TextView textExplore;
    private TextView textPrice;
    private TextView textLink;
    private View viewPersonCircle1;
    private View viewPersonCircle2;
    private View viewPersonCircle3;
    private View viewPersonCircle4;
    private Button buttonNext;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializationViews(view);
        createSpinnerCategory();
        mainAPINextClick();
        mainLikeClick();
        spinnerGetSelectedValues();
        //rangeSliderPriceSetLabelFormatter();
        rangeSliderPriceGetSelectedValues();
        rangeSliderAccessibilityGetSelectedValues();
    }

    private void initializationViews(View view) {
        textCategory = view.findViewById(R.id.text_main_category);
        imageFavorite = view.findViewById(R.id.image_main_favorite);
        lottieAnimationLike = view.findViewById(R.id.lottieAnimation_main_like);
        textExplore = view.findViewById(R.id.text_main_explore);
        textPrice = view.findViewById(R.id.text_main_free);
        textLink = view.findViewById(R.id.text_main_link);
        viewPersonCircle1 = view.findViewById(R.id.view_main_person_circle_1);
        viewPersonCircle2 = view.findViewById(R.id.view_main_person_circle_2);
        viewPersonCircle3 = view.findViewById(R.id.view_main_person_circle_3);
        viewPersonCircle4 = view.findViewById(R.id.view_main_person_circle_4);
        buttonNext = view.findViewById(R.id.button_main_next);
        progressBarAccessibility = view.findViewById(R.id.progressBar_main_accessibility);
        spinnerCategory = view.findViewById(R.id.spinner_main_category);
        rangeSliderPrice = view.findViewById(R.id.rangeSlider_price);
        rangeSliderAccessibility = view.findViewById(R.id.rangeSlider_accessibility);
    }

    private void createSpinnerCategory() {
        String[] dropdownCategory = getResources().getStringArray(R.array.category);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(requireContext(), R.layout.custom_spinner_item, dropdownCategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

    public void mainAPINextClick() {
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinnerSelectedValues != null) {
                    if (spinnerSelectedValues.equals("RANDOM")) {
                        spinnerSelectedValues = null;
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
                                createParticipants(boredAction);
                                createLink(boredAction);
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
        });

    }

    private void createParticipants(BoredAction boredAction) {
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

    private void createLink(BoredAction boredAction) {
        if (boredAction.getLink().equals("")) {
            textLink.setTypeface(Typeface.DEFAULT_BOLD);
            textLink.setText(boredAction.getLink());
            textLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String URL = boredAction.getLink();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                    startActivity(intent);
                }
            });
        }
    }

    private boolean flag = true;

    public void mainLikeClick() {
        imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
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