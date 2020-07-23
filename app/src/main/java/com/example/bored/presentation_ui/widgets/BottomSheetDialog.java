package com.example.bored.presentation_ui.widgets;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bored.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    public static final String ARG_EXPLORE = "text_explore";
    public static final String ARG_LINK = "text_link";

    private LinearLayout linearShare;
    private LinearLayout linearLink;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializationView(view);
        linearShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetShareClick();
            }
        });
        linearLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetLinkClick();
            }
        });
    }

    private void initializationView(View view) {
        linearShare = view.findViewById(R.id.linear_bottomSheet_share);
        linearLink = view.findViewById(R.id.linear_bottomSheet_link);
    }

    private void bottomSheetShareClick() {
        String textExplore = getArguments().getString(ARG_EXPLORE);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Привет! Мне скучно Bored App предложила мне "
                + textExplore + " давай вместе");
        startActivity(intent);

    }

    private void bottomSheetLinkClick() {
        String textLink = getArguments().getString(ARG_LINK);
        if (textLink != null) {
            ClipboardManager clipboardManager = (ClipboardManager) requireActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("TextView", textLink);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(getContext(), "Ссылка скопировано в буфер обмена", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Ссылки нет", Toast.LENGTH_SHORT).show();
        }
    }
}
