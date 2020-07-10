package com.example.todo.UI.home;

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
    private ImageView imageUserIcon1;
    private ImageView imageUserIcon2;
    private ImageView imageUserIcon3;
    private ImageView imageUserIcon4;
    private ImageView imageUserIconPlus;
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

    private BoredAction boredActions;
    private String key;

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
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainAPINextClick();
            }
        });
        imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainLikeClick();
            }
        });
        spinnerGetSelectedValues();
        //rangeSliderPriceSetLabelFormatter();
        rangeSliderPriceGetSelectedValues();
        rangeSliderAccessibilityGetSelectedValues();
    }

    private void initializationViews(View view) {
        textCategory = view.findViewById(R.id.text_listBored_category);
        imageFavorite = view.findViewById(R.id.image_listBored_favorite);
        lottieAnimationLike = view.findViewById(R.id.lottieAnimation_main_like);
        textExplore = view.findViewById(R.id.text_listBored_explore);
        textPrice = view.findViewById(R.id.text_listBored_free);
        textLink = view.findViewById(R.id.text_listBored_link);
        imageUserIcon1 = view.findViewById(R.id.image_user_icon_1);
        imageUserIcon2 = view.findViewById(R.id.image_user_icon_2);
        imageUserIcon3 = view.findViewById(R.id.image_user_icon_3);
        imageUserIcon4 = view.findViewById(R.id.image_user_icon_4);
        imageUserIconPlus = view.findViewById(R.id.image_user_icon_plus);
        buttonNext = view.findViewById(R.id.button_main_next);
        progressBarAccessibility = view.findViewById(R.id.progressBar_listBored_accessibility);
        spinnerCategory = view.findViewById(R.id.spinner_main_category);
        rangeSliderPrice = view.findViewById(R.id.rangeSlider_price);
        rangeSliderAccessibility = view.findViewById(R.id.rangeSlider_accessibility);
    }

    private void createSpinnerCategory() {
        String[] dropdownCategory = getResources().getStringArray(R.array.spinner_category);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(requireContext(), R.layout.custom_spinner_item, dropdownCategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

    private boolean isLiked = true;

    public void mainLikeClick() {
        String category = textCategory.getText().toString().trim();
        String price = textPrice.getText().toString().trim();
        if (isLiked) {
            if (category.equals("Category") || price.equals("free")) {
                Toast.makeText(getContext(), "Выберите параметры и нажмите NEXT", Toast.LENGTH_SHORT).show();
                return;
            } else {
                setLikeAnimationAndIcon();
                saveBoredAction();
            }
        } else {
            recoveryLikeIcon();
            deleteBoredAction();
        }
        BoredAction boredAction = App.boredStorage.getBoredAction(null);
        Log.d("anim", "Stored " + boredAction);
    }

    private void setLikeAnimationAndIcon() {
        lottieAnimationLike.setVisibility(View.VISIBLE);
        lottieAnimationLike.playAnimation();
        imageFavorite.setImageResource(icon_selected_favorite_red);
        isLiked = !isLiked;
    }

    private void recoveryLikeIcon() {
        lottieAnimationLike.setVisibility(View.INVISIBLE);
        imageFavorite.setImageResource(icon_favorite_blue);
        isLiked = true;
    }

    private void saveBoredAction() {
        App.boredStorage.saveBoredAction(boredActions);
        Log.d("anim", "Receive " + boredActions.toString());
        for (BoredAction action : App.boredStorage.getAllActions()) {
            Log.d("anim", action.toString());
        }
    }

    private void deleteBoredAction() {
        App.boredStorage.deleteBoredAction(boredActions);
    }

    public void mainAPINextClick() {
        recoveryLikeIcon();
        setRandomBoredActionType();
        BoredAPIGetAction();
    }

    private void setRandomBoredActionType() {
        if (spinnerSelectedValues != null) {
            if (spinnerSelectedValues.equals("RANDOM")) {
                spinnerSelectedValues = null;
                rangeSliderPrice.setValues(0.0f, 1.0f);
                rangeSliderAccessibility.setValues(0.0f, 1.0f);
            }
        }
    }

    private void BoredAPIGetAction() {
        App.boredAPIClient.getAction(
                spinnerSelectedValues,
                rangeSliderSelectedMinPrice,
                rangeSliderSelectedMaxPrice,
                rangeSliderSelectedMinAccessibility,
                rangeSliderSelectedMaxAccessibility,
                new BoredAPIClient.BoredActionCallback() {
                    @Override
                    public void onSuccess(BoredAction boredAction) {
                        try {
                            key = boredAction.getKey();
                            boredActions = boredAction;
                            textCategory.setText(boredAction.getType());
                            textExplore.setText(boredAction.getActivity());
                            textPrice.setText(boredAction.getPrice().toString() + '$');
                            createParticipants(boredAction);
                            createLink(boredAction);
                            setProgressBarAccessibility((int) (boredAction.getAccessibility() * 100));
                        } catch (NullPointerException NPE) {
                            textPrice.setText("free");
                            recoveryParticipantsViews();
                            setProgressBarAccessibility(0);
                            Toast.makeText(getContext(), "Не найдено, измените параметры", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("anim", boredAction.toString());

                        BoredAction boredKey = App.boredStorage.getBoredAction(key);
                        if (boredKey != null) {
                            setLikeAnimationAndIcon();
                            Log.d("anim", "Stored " + boredAction);
                        }
                    }

                    @Override
                    public void onFailure(Exception E) {
                        Log.d("anim", E.getMessage());
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
        imageUserIcon1.setVisibility(View.VISIBLE);
        imageUserIcon2.setVisibility(View.VISIBLE);
        imageUserIcon3.setVisibility(View.VISIBLE);
        imageUserIcon4.setVisibility(View.VISIBLE);
        imageUserIconPlus.setVisibility(View.INVISIBLE);
    }

    private void invisibleParticipantsCase1() {
        imageUserIcon2.setVisibility(View.INVISIBLE);
        imageUserIcon3.setVisibility(View.INVISIBLE);
        imageUserIcon4.setVisibility(View.INVISIBLE);
    }

    private void invisibleParticipantsCase2() {
        imageUserIcon1.setVisibility(View.INVISIBLE);
        imageUserIcon3.setVisibility(View.INVISIBLE);
        imageUserIcon4.setVisibility(View.INVISIBLE);
    }

    private void invisibleParticipantsCase3() {
        imageUserIcon1.setVisibility(View.INVISIBLE);
        imageUserIcon2.setVisibility(View.INVISIBLE);
        imageUserIcon4.setVisibility(View.INVISIBLE);
    }

    private void invisibleParticipantsCase4() {
        imageUserIcon1.setVisibility(View.INVISIBLE);
        imageUserIcon2.setVisibility(View.INVISIBLE);
        imageUserIcon3.setVisibility(View.INVISIBLE);
        imageUserIconPlus.setVisibility(View.VISIBLE);
    }

    private void setProgressBarAccessibility(int progress) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBarAccessibility.setProgress(progress, true);
        }
    }

    private void createLink(BoredAction boredAction) {
        textLink.setTypeface(Typeface.DEFAULT_BOLD);
        textLink.setText(boredAction.getLink());
        textLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URL = boredAction.getLink();
                if (URL.isEmpty()) {
                    Toast.makeText(getContext(), "Ссылки нет", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                    startActivity(intent);
                }
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