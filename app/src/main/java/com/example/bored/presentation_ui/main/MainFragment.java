package com.example.bored.presentation_ui.main;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
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
import com.example.bored.App;
import com.example.bored.R;
import com.example.bored.data.remote.BoredAPIClient;
import com.example.bored.model.BoredAction;
import com.example.bored.presentation_ui.widgets.BottomSheetDialog;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;

import static com.example.bored.R.drawable.icon_favorite;
import static com.example.bored.R.drawable.icon_favorite_selected;

public class MainFragment extends Fragment {

    private View viewRectangleCategory;
    private TextView textCategory;
    private ImageView imageMore;
    private ImageView imageFavorite;
    private TextView textExplore;
    private TextView textFree;
    private TextView textPrice;
    private ProgressBar progressBarAccessibility;
    private TextView textAccessibility;
    private ImageView imageParticipantsIcon1;
    private ImageView imageParticipantsIcon2;
    private ImageView imageParticipantsIcon3;
    private ImageView imageParticipantsIcon4;
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

    private String key;

    public MainFragment() {
    }

    public static Fragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializationViews(view);
        createSpinnerCategory();
        setLabelFormatterRangeSliderPrice();
        getSelectedValuesSpinnerCategory();
        getSelectedValuesRangeSliderPrice();
        getSelectedValuesRangeSliderAccessibility();
        imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainMoreClick();
            }
        });
        imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainLikeClick();
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainNextClick();
            }
        });
    }

    private void initializationViews(View view) {
        viewRectangleCategory = view.findViewById(R.id.view_main_rectangleCategory);
        textCategory = view.findViewById(R.id.text_main_category);
        imageMore = view.findViewById(R.id.image_main_more);
        imageFavorite = view.findViewById(R.id.image_main_favorite);
        textExplore = view.findViewById(R.id.text_main_explore);
        textFree = view.findViewById(R.id.text_main_free);
        textPrice = view.findViewById(R.id.text_main_price);
        progressBarAccessibility = view.findViewById(R.id.progressBar_main_accessibility);
        textAccessibility = view.findViewById(R.id.text_main_accessibility);
        imageParticipantsIcon1 = view.findViewById(R.id.image_main_participants_icon_1);
        imageParticipantsIcon2 = view.findViewById(R.id.image_main_participants_icon_2);
        imageParticipantsIcon3 = view.findViewById(R.id.image_main_participants_icon_3);
        imageParticipantsIcon4 = view.findViewById(R.id.image_main_participants_icon_4);
        textParticipants = view.findViewById(R.id.text_main_participants);
        viewRectangleLink = view.findViewById(R.id.view_main_rectangleLink);
        buttonLink = view.findViewById(R.id.button_main_open_link);
        textLink = view.findViewById(R.id.text_main_link);
        lottieAnimationLike = view.findViewById(R.id.lottieAnimation_main_like);
        lottieAnimationLoading = view.findViewById(R.id.lottieAnimation_main_loading);
        buttonNext = view.findViewById(R.id.button_main_next);
        spinnerCategory = view.findViewById(R.id.spinner_main_category);
        rangeSliderPrice = view.findViewById(R.id.rangeSlider_main_price);
        rangeSliderAccessibility = view.findViewById(R.id.rangeSlider_main_accessibility);
    }

    private void setAnimationCircularReveal(View view) {
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;
        float finalRadius = (float) Math.hypot(cx, cy);
        Animator animator;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
            animator.start();
        }
    }

    private void createSpinnerCategory() {
        String[] dropdownCategory = getResources().getStringArray(R.array.spinner_category);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(requireContext(), R.layout.custom_spinner_item, dropdownCategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

    private void setLabelFormatterRangeSliderPrice() {
        rangeSliderPrice.setLabelFormatter(new Slider.LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                NumberFormat format = NumberFormat.getCurrencyInstance();
                format.setMaximumFractionDigits(1);
                format.setCurrency(Currency.getInstance("USD"));
                return format.format(value);
            }
        });
    }

    private void getSelectedValuesSpinnerCategory() {
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

    private void getSelectedValuesRangeSliderPrice() {
        rangeSliderPrice.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                rangeSliderSelectedPrice = slider.getValues();
                rangeSliderSelectedMinPrice = rangeSliderSelectedPrice.get(0);
                rangeSliderSelectedMaxPrice = rangeSliderSelectedPrice.get(1);
            }
        });
    }

    private void getSelectedValuesRangeSliderAccessibility() {
        rangeSliderAccessibility.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                rangeSliderSelectedAccessibility = slider.getValues();
                rangeSliderSelectedMinAccessibility = rangeSliderSelectedAccessibility.get(0);
                rangeSliderSelectedMaxAccessibility = rangeSliderSelectedAccessibility.get(1);
            }
        });
    }

    private void mainMoreClick() {
        String category = textCategory.getText().toString().trim();
        String price = textFree.getText().toString().trim();
        if (category.equals("Category") || price.equals("free")) {
            Toast.makeText(getContext(), "Выберите параметры и нажмите NEXT", Toast.LENGTH_SHORT).show();
            return;
        } else {
            openBottomSheet();
        }
    }

    private void openBottomSheet() {
        BottomSheetDialog bottomSheet = new BottomSheetDialog();
        bottomSheet.show(requireActivity().getSupportFragmentManager(), "bottomSheet");
        Bundle bundle = new Bundle();
        bundle.putString(BottomSheetDialog.ARG_EXPLORE, textExplore.getText().toString().trim());
        if (textLink.getText().toString().trim().isEmpty()) {
            bundle.putString(BottomSheetDialog.ARG_LINK, null);
        } else {
            bundle.putString(BottomSheetDialog.ARG_LINK, textLink.getText().toString().trim());
        }
        bottomSheet.setArguments(bundle);
    }

    private boolean isLiked = true;

    public void mainLikeClick() {
        String category = textCategory.getText().toString().trim();
        String price = textFree.getText().toString().trim();
        if (isLiked) {
            if (category.equals("Category") || price.equals("free")) {
                Toast.makeText(getContext(), "Выберите параметры и нажмите NEXT", Toast.LENGTH_SHORT).show();
                return;
            } else {
                setLikeAnimation();
                setLikeIcon();
                App.boredRepository.saveBoredAction(App.boredRepository.lastAction);
            }
        } else {
            recoveryLikeIcon();
            App.boredRepository.deleteBoredAction(App.boredRepository.lastAction);
        }
    }

    private void setLikeAnimation() {
        lottieAnimationLike.setVisibility(View.VISIBLE);
        lottieAnimationLike.playAnimation();
    }

    private void setLikeIcon() {
        imageFavorite.setImageResource(icon_favorite_selected);
        isLiked = !isLiked;
    }

    private void recoveryLikeIcon() {
        lottieAnimationLike.setVisibility(View.INVISIBLE);
        imageFavorite.setImageResource(icon_favorite);
        isLiked = true;
    }

    public void mainNextClick() {
        recoveryLikeIcon();
        setRandomBoredAction();
        boredAPIGetAction();
    }

    private void setRandomBoredAction() {
        if (spinnerSelectedValues != null) {
            if (spinnerSelectedValues.equals("RANDOM")) {
                spinnerSelectedValues = null;
            }
        }
    }

    private void boredAPIGetAction() {
        invisibleAllAndPlayLoading();
        App.boredRepository.getAction(
                spinnerSelectedValues,
                rangeSliderSelectedMinPrice,
                rangeSliderSelectedMaxPrice,
                rangeSliderSelectedMinAccessibility,
                rangeSliderSelectedMaxAccessibility,
                new BoredAPIClient.BoredActionCallback() {
                    @Override
                    public void onSuccess(BoredAction boredAction) {
                        visibleAllAndPauseAnimation();
                        try {
                            App.boredRepository.lastAction = boredAction;
                            key = App.boredRepository.lastAction.getKey();
                            setBoredAction(boredAction);
                        } catch (NullPointerException NPE) {
                            catchNullPointerException();
                        }
                        if (App.boredRepository.getBoredAction(key) != null) {
                            setLikeIcon();
                        }
                        Log.d("anime", boredAction.toString());
                    }

                    @Override
                    public void onFailure(Exception E) {
                        Toast.makeText(getContext(), "Проверьте интернет соединение", Toast.LENGTH_SHORT).show();
                        lottieAnimationLoading.setAnimation(R.raw.lottie_dino);
                        lottieAnimationLoading.playAnimation();
                        Log.e("anime", E.getMessage());
                    }
                });
    }

    private void invisibleAllAndPlayLoading() {
        viewRectangleCategory.setVisibility(View.INVISIBLE);
        textCategory.setVisibility(View.INVISIBLE);
        imageMore.setVisibility(View.INVISIBLE);
        imageFavorite.setVisibility(View.INVISIBLE);
        textExplore.setVisibility(View.INVISIBLE);
        textFree.setVisibility(View.INVISIBLE);
        textPrice.setVisibility(View.INVISIBLE);
        progressBarAccessibility.setVisibility(View.INVISIBLE);
        textAccessibility.setVisibility(View.INVISIBLE);
        imageParticipantsIcon1.setVisibility(View.INVISIBLE);
        imageParticipantsIcon2.setVisibility(View.INVISIBLE);
        imageParticipantsIcon3.setVisibility(View.INVISIBLE);
        imageParticipantsIcon4.setVisibility(View.INVISIBLE);
        textParticipants.setVisibility(View.INVISIBLE);
        viewRectangleLink.setVisibility(View.INVISIBLE);
        buttonLink.setVisibility(View.INVISIBLE);
        textLink.setVisibility(View.INVISIBLE);
        loadingPlay();
    }

    private void loadingPlay() {
        lottieAnimationLoading.setAnimation(R.raw.lottie_loading);
        lottieAnimationLoading.setVisibility(View.VISIBLE);
        lottieAnimationLoading.playAnimation();
    }

    private void visibleAllAndPauseAnimation() {
        setAnimationCircularReveal(viewRectangleCategory);
        viewRectangleCategory.setVisibility(View.VISIBLE);
        textCategory.setVisibility(View.VISIBLE);
        imageMore.setVisibility(View.VISIBLE);
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

    private void loadingPause() {
        lottieAnimationLoading.setVisibility(View.INVISIBLE);
        lottieAnimationLoading.pauseAnimation();
    }

    private void setBoredAction(BoredAction boredAction) {
        textCategory.setText(boredAction.getType());
        setRectangleCategoryColor();
        textExplore.setText(boredAction.getActivity());
        textFree.setText(boredAction.getPrice().toString() + '$');
        setProgressBarAccessibility((int) (boredAction.getAccessibility() * 100));
        setParticipants(boredAction);
        setLink(boredAction);
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

    private void setParticipants(BoredAction boredAction) {
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
        imageParticipantsIcon1.setVisibility(View.VISIBLE);
        imageParticipantsIcon2.setVisibility(View.VISIBLE);
        imageParticipantsIcon3.setVisibility(View.VISIBLE);
        imageParticipantsIcon4.setVisibility(View.VISIBLE);
    }

    private void invisibleParticipantsCase1() {
        imageParticipantsIcon2.setVisibility(View.INVISIBLE);
        imageParticipantsIcon3.setVisibility(View.INVISIBLE);
        imageParticipantsIcon4.setVisibility(View.INVISIBLE);
    }

    private void invisibleParticipantsCase2() {
        imageParticipantsIcon1.setVisibility(View.INVISIBLE);
        imageParticipantsIcon3.setVisibility(View.INVISIBLE);
        imageParticipantsIcon4.setVisibility(View.INVISIBLE);
    }

    private void invisibleParticipantsCase3() {
        imageParticipantsIcon1.setVisibility(View.INVISIBLE);
        imageParticipantsIcon2.setVisibility(View.INVISIBLE);
        imageParticipantsIcon4.setVisibility(View.INVISIBLE);
    }

    private void invisibleParticipantsCase4() {
        imageParticipantsIcon1.setVisibility(View.INVISIBLE);
        imageParticipantsIcon2.setVisibility(View.INVISIBLE);
        imageParticipantsIcon3.setVisibility(View.INVISIBLE);
    }

    private void setProgressBarAccessibility(int progress) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBarAccessibility.setProgress(progress, true);
        }
    }

    private void setLink(BoredAction boredAction) {
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

    private void catchNullPointerException() {
        viewRectangleCategory.getBackground().setColorFilter(Color.parseColor("#2F80ED"), PorterDuff.Mode.SRC_IN);
        textFree.setText("free");
        recoveryParticipantsViews();
        textLink.setText("");
        setProgressBarAccessibility(0);
        Toast.makeText(getContext(), "Не найдено, измените параметры", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (App.boredRepository.getBoredAction(key) == null) {
            recoveryLikeIcon();
        }
        setLastAction();
    }

    private void setLastAction() {
        if (App.boredRepository.lastAction != null) {
            setBoredAction(App.boredRepository.lastAction);
            if (App.boredRepository.getBoredAction(App.boredRepository.lastAction.getKey()) == null) {
                recoveryLikeIcon();
            } else {
                setLikeIcon();
                isLiked = false;
            }
        }
    }
}
