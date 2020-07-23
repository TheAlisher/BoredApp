package com.example.bored.presentation_ui.widgets;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private ImageView imageLink;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializationView(view);
        isLink();
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
        imageLink = view.findViewById(R.id.image_bottomSheet_link);
    }

    private void isLink() {
        String link = getArguments().getString(ARG_LINK);
        if (link != null) {
            imageLink.setImageResource(R.drawable.icon_link);
        } else {
            imageLink.setImageResource(R.drawable.icon_link_off);
        }
    }

    private void bottomSheetShareClick() {
        String explore = getArguments().getString(ARG_EXPLORE);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Привет! Мне скучно Bored App предложила мне "
                + explore + " давай вместе");
        startActivity(intent);

    }

    private void bottomSheetLinkClick() {
        String link = getArguments().getString(ARG_LINK);
        if (link != null) {
            ClipboardManager clipboardManager = (ClipboardManager) requireActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("TextView", link);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(getContext(), "Ссылка скопировано в буфер обмена", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Ссылки нет", Toast.LENGTH_SHORT).show();
        }
    }
}
