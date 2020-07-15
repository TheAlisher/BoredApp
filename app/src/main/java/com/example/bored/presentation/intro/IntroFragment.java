package com.example.bored.presentation.intro;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.bored.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends Fragment {

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
        int position = getArguments().getInt(SectionsPagerAdapter.PAGER_POSITION);
        FrameLayout fragmentIntro = view.findViewById(R.id.fragmentIntro);
        switch (position) {
            case 0:
                fragmentIntro.setBackgroundResource(R.color.colorPrimary);
                break;
            case 1:
                fragmentIntro.setBackgroundResource(R.color.colorAccent);
                break;
            case 2:
                fragmentIntro.setBackgroundResource(R.color.colorPrimaryDark);
                break;
        }
    }
}
