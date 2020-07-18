package com.example.bored.presentation_ui.onboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bored.R;

public class IntroFragment extends Fragment {

    private ConstraintLayout fragmentIntro;
    private TextView textTitle;
    private TextView textSubtitle;

    public IntroFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializationViews(view);
        int position = getArguments().getInt(SectionsPagerAdapter.PAGER_POSITION);
        switch (position) {
            case 0:
                fragmentIntro.setBackgroundResource(R.color.Madison);
                break;
            case 1:
                fragmentIntro.setBackgroundResource(R.color.Madison);
                textTitle.setText(R.string.text_intro_title_2page);
                textSubtitle.setText(R.string.text_intro_subtitle_2page);
                break;
            case 2:
                fragmentIntro.setBackgroundResource(R.color.Madison);
                textTitle.setText(R.string.text_intro_title_3page);
                textSubtitle.setText(R.string.text_intro_subtitle_3page);
                textSubtitle.setMovementMethod(LinkMovementMethod.getInstance());
                textSubtitle.setLinkTextColor(getResources().getColor(R.color.DodgerBlue));
                break;
        }
    }

    private void initializationViews(View view) {
        fragmentIntro = view.findViewById(R.id.fragmentIntro);
        textTitle = view.findViewById(R.id.text_intro_title);
        textSubtitle = view.findViewById(R.id.text_intro_subtitle);
    }
}
