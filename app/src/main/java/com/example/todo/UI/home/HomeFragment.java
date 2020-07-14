package com.example.todo.UI.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
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

    private View viewRectangleCategory;
    private TextView textCategory;
    private ImageView imageFavorite;
    private TextView textExplore;
    private TextView textFree;
    private TextView textPrice;
    private ProgressBar progressBarAccessibility;
    private TextView textAccessibility;
    private ImageView imageUserIcon1;
    private ImageView imageUserIcon2;
    private ImageView imageUserIcon3;
    private ImageView imageUserIcon4;
    private ImageView imageUserIconPlus;
    private TextView textParticipants;
    private View viewRectangleLink;
    private Button buttonLink;
    private TextView textLink;
    private LottieAnimationView lottieAnimationLike;
    private LottieAnimationView lottieAnimationLoading;
    private Button buttonNext;
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
        imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeLikeClick();
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeAPINextClick();
            }
        });
        spinnerGetSelectedValues();
        //rangeSliderPriceSetLabelFormatter();
        rangeSliderPriceGetSelectedValues();
        rangeSliderAccessibilityGetSelectedValues();
    }

    private void initializationViews(View view) {
        viewRectangleCategory = view.findViewById(R.id.view_home_rectangleCategory);
        textCategory = view.findViewById(R.id.text_home_category);
        imageFavorite = view.findViewById(R.id.image_home_favorite);
        textExplore = view.findViewById(R.id.text_home_explore);
        textFree = view.findViewById(R.id.text_home_free);
        textPrice = view.findViewById(R.id.text_home_price);
        progressBarAccessibility = view.findViewById(R.id.progressBar_home_accessibility);
        textAccessibility = view.findViewById(R.id.text_home_accessibility);
        imageUserIcon1 = view.findViewById(R.id.image_home_participants_icon_1);
        imageUserIcon2 = view.findViewById(R.id.image_home_participants_icon_2);
        imageUserIcon3 = view.findViewById(R.id.image_home_participants_icon_3);
        imageUserIcon4 = view.findViewById(R.id.image_home_participants_icon_4);
        imageUserIconPlus = view.findViewById(R.id.image_home_participants_icon_4plus);
        textParticipants = view.findViewById(R.id.text_home_participants);
        viewRectangleLink = view.findViewById(R.id.view_home_rectangleLink);
        buttonLink = view.findViewById(R.id.button_home_open_link);
        textLink = view.findViewById(R.id.text_home_link);
        lottieAnimationLike = view.findViewById(R.id.lottieAnimation_home_like);
        lottieAnimationLoading = view.findViewById(R.id.lottieAnimation_home_loading);
        buttonNext = view.findViewById(R.id.button_home_next);
        spinnerCategory = view.findViewById(R.id.spinner_home_category);
        rangeSliderPrice = view.findViewById(R.id.rangeSlider_home_price);
        rangeSliderAccessibility = view.findViewById(R.id.rangeSlider_home_accessibility);
    }

    private void createSpinnerCategory() {
        String[] dropdownCategory = getResources().getStringArray(R.array.spinner_category);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(requireContext(), R.layout.custom_spinner_item, dropdownCategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

    private boolean isLiked = true;

    public void homeLikeClick() {
        String category = textCategory.getText().toString().trim();
        String price = textFree.getText().toString().trim();
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

    public void homeAPINextClick() {
        recoveryLikeIcon();
        setRandomBoredActionType();
        BoredAPIGetAction();
    }

    private void setRandomBoredActionType() {
        if (spinnerSelectedValues != null) {
            if (spinnerSelectedValues.equals("RANDOM")) {
                spinnerSelectedValues = null;
            }
        }
    }

    private void BoredAPIGetAction() {
        invisibleAllAndPlayLoading();
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
                            textFree.setText(boredAction.getPrice().toString() + '$');
                            createParticipants(boredAction);
                            createLink(boredAction);
                            setProgressBarAccessibility((int) (boredAction.getAccessibility() * 100));
                            setRectangleCategoryColor();
                        } catch (NullPointerException NPE) {
                            catchNullPointerException();
                        }
                        visibleAllAndPauseAnimation();

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

    private void invisibleAllAndPlayLoading() {
        viewRectangleCategory.setVisibility(View.INVISIBLE);
        textCategory.setVisibility(View.INVISIBLE);
        imageFavorite.setVisibility(View.INVISIBLE);
        textExplore.setVisibility(View.INVISIBLE);
        textFree.setVisibility(View.INVISIBLE);
        textPrice.setVisibility(View.INVISIBLE);
        progressBarAccessibility.setVisibility(View.INVISIBLE);
        textAccessibility.setVisibility(View.INVISIBLE);
        imageUserIcon1.setVisibility(View.INVISIBLE);
        imageUserIcon2.setVisibility(View.INVISIBLE);
        imageUserIcon3.setVisibility(View.INVISIBLE);
        imageUserIcon4.setVisibility(View.INVISIBLE);
        imageUserIconPlus.setVisibility(View.INVISIBLE);
        textParticipants.setVisibility(View.INVISIBLE);
        viewRectangleLink.setVisibility(View.INVISIBLE);
        buttonLink.setVisibility(View.INVISIBLE);
        textLink.setVisibility(View.INVISIBLE);
        loadingPlay();
    }

    private void visibleAllAndPauseAnimation() {
        viewRectangleCategory.setVisibility(View.VISIBLE);
        textCategory.setVisibility(View.VISIBLE);
        imageFavorite.setVisibility(View.VISIBLE);
        textExplore.setVisibility(View.VISIBLE);
        textFree.setVisibility(View.VISIBLE);
        textPrice.setVisibility(View.VISIBLE);
        progressBarAccessibility.setVisibility(View.VISIBLE);
        textAccessibility.setVisibility(View.VISIBLE);
        textParticipants.setVisibility(View.VISIBLE);
        viewRectangleLink.setVisibility(View.VISIBLE);
        buttonLink.setVisibility(View.VISIBLE);
        textLink.setVisibility(View.VISIBLE);
        loadingPause();
    }

    private void loadingPlay() {
        lottieAnimationLoading.setVisibility(View.VISIBLE);
        lottieAnimationLoading.playAnimation();
    }

    private void loadingPause() {
        lottieAnimationLoading.setVisibility(View.INVISIBLE);
        lottieAnimationLoading.pauseAnimation();
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
        imageUserIconPlus.setVisibility(View.VISIBLE);
    }

    private void invisibleParticipantsCase1() {
        imageUserIcon2.setVisibility(View.INVISIBLE);
        imageUserIcon3.setVisibility(View.INVISIBLE);
        imageUserIcon4.setVisibility(View.INVISIBLE);
        imageUserIconPlus.setVisibility(View.INVISIBLE);
    }

    private void invisibleParticipantsCase2() {
        imageUserIcon1.setVisibility(View.INVISIBLE);
        imageUserIcon3.setVisibility(View.INVISIBLE);
        imageUserIcon4.setVisibility(View.INVISIBLE);
        imageUserIconPlus.setVisibility(View.INVISIBLE);
    }

    private void invisibleParticipantsCase3() {
        imageUserIcon1.setVisibility(View.INVISIBLE);
        imageUserIcon2.setVisibility(View.INVISIBLE);
        imageUserIcon4.setVisibility(View.INVISIBLE);
        imageUserIconPlus.setVisibility(View.INVISIBLE);
    }

    private void invisibleParticipantsCase4() {
        imageUserIcon1.setVisibility(View.INVISIBLE);
        imageUserIcon2.setVisibility(View.INVISIBLE);
        imageUserIcon3.setVisibility(View.INVISIBLE);
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
                if (URL.isEmpty() || textLink.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Ссылки нет", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                    startActivity(intent);
                }
            }
        });
    }

    private void setRectangleCategoryColor() {
        String category = textCategory.getText().toString().trim();

        if (category.equals("education")) {
            viewRectangleCategory.getBackground().setColorFilter(Color.parseColor("#F4B300"), PorterDuff.Mode.SRC_IN);
        }

        if (category.equals("recreational")) {
            viewRectangleCategory.getBackground().setColorFilter(Color.parseColor("#3D1E6D"), PorterDuff.Mode.SRC_IN);
        }

        if (category.equals("social")) {
            viewRectangleCategory.getBackground().setColorFilter(Color.parseColor("#AE113D"), PorterDuff.Mode.SRC_IN);
        }

        if (category.equals("diy")) {
            viewRectangleCategory.getBackground().setColorFilter(Color.parseColor("#E064B7"), PorterDuff.Mode.SRC_IN);
        }

        if (category.equals("charity")) {
            viewRectangleCategory.getBackground().setColorFilter(Color.parseColor("#4D648D"), PorterDuff.Mode.SRC_IN);
        }

        if (category.equals("cooking")) {
            viewRectangleCategory.getBackground().setColorFilter(Color.parseColor("#D51400"), PorterDuff.Mode.SRC_IN);
        }

        if (category.equals("relaxation")) {
            viewRectangleCategory.getBackground().setColorFilter(Color.parseColor("#009688"), PorterDuff.Mode.SRC_IN);
        }

        if (category.equals("music")) {
            viewRectangleCategory.getBackground().setColorFilter(Color.parseColor("#03396C"), PorterDuff.Mode.SRC_IN);
        }

        if (category.equals("busywork")) {
            viewRectangleCategory.getBackground().setColorFilter(Color.parseColor("#851E3E"), PorterDuff.Mode.SRC_IN);
        }
    }

    private void catchNullPointerException() {
        viewRectangleCategory.getBackground().setColorFilter(Color.parseColor("#2F80ED"), PorterDuff.Mode.SRC_IN);
        textFree.setText("free");
        recoveryParticipantsViews();
        textLink.setText("");
        setProgressBarAccessibility(0);
        Toast.makeText(getContext(), "Не найдено, измените параметры", Toast.LENGTH_SHORT).show();
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
