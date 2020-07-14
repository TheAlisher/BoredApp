package com.example.todo.UI.favorites;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.UI.OnItemClickListener;
import com.example.todo.model.BoredAction;

import java.util.ArrayList;

public class BoredAdapter extends RecyclerView.Adapter<BoredAdapter.BoredViewHolder> {

    private ArrayList<BoredAction> card;
    private OnItemClickListener onItemClickListener;

    public BoredAdapter(ArrayList<BoredAction> card) {
        this.card = card;
    }

    @NonNull
    @Override
    public BoredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bored, parent, false);
        return new BoredViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoredViewHolder holder, int position) {
        holder.onBind(card.get(position));
    }

    @Override
    public int getItemCount() {
        return card.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class BoredViewHolder extends RecyclerView.ViewHolder {

        private TextView textCategory;
        private ImageView imageFavoriteSelected;
        private TextView textExplore;
        private TextView textPrice;
        private ProgressBar progressBarAccessibility;
        private ImageView imageCardParticipantsIcon1;
        private ImageView imageCardParticipantsIcon2;
        private ImageView imageCardParticipantsIcon3;
        private ImageView imageCardParticipantsIcon4;
        private ImageView imageCardParticipantsIcon4plus;
        private TextView textLink;

        public BoredViewHolder(@NonNull View itemView) {
            super(itemView);
            initializationViews(itemView);
            imageFavoriteSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnItemClick(getAdapterPosition());
                }
            });
            textLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapterLinkClick(view);
                }
            });
        }

        private void initializationViews(View itemView) {
            textCategory = itemView.findViewById(R.id.text_card_category);
            imageFavoriteSelected = itemView.findViewById(R.id.image_card_favorite_selected);
            textExplore = itemView.findViewById(R.id.text_card_explore);
            textPrice = itemView.findViewById(R.id.text_card_free);
            progressBarAccessibility = itemView.findViewById(R.id.progressBar_card_accessibility);
            imageCardParticipantsIcon1 = itemView.findViewById(R.id.image_card_participants_icon_1);
            imageCardParticipantsIcon2 = itemView.findViewById(R.id.image_card_participants_icon_2);
            imageCardParticipantsIcon3 = itemView.findViewById(R.id.image_card_participants_icon_3);
            imageCardParticipantsIcon4 = itemView.findViewById(R.id.image_card_participants_icon_4);
            imageCardParticipantsIcon4plus = itemView.findViewById(R.id.image_card_participants_icon_4plus);
            textLink = itemView.findViewById(R.id.text_card_link);
        }

        private void adapterLinkClick(View view) {
            String URL = textLink.getText().toString().trim();
            if (URL.isEmpty()) {
                Toast.makeText(view.getContext(), "Ссылки нет", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                view.getContext().startActivity(intent);
            }
        }

        public void onBind(BoredAction boredAction) {
            textCategory.setText(boredAction.getType());
            textExplore.setText(boredAction.getActivity());
            textPrice.setText(boredAction.getPrice().toString() + '$');
            createParticipants(boredAction);
            setProgressBarAccessibility((int) (boredAction.getAccessibility() * 100));
            createLink(boredAction);
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
            imageCardParticipantsIcon1.setVisibility(View.VISIBLE);
            imageCardParticipantsIcon2.setVisibility(View.VISIBLE);
            imageCardParticipantsIcon3.setVisibility(View.VISIBLE);
            imageCardParticipantsIcon4.setVisibility(View.VISIBLE);
            imageCardParticipantsIcon4plus.setVisibility(View.INVISIBLE);
        }

        private void invisibleParticipantsCase1() {
            imageCardParticipantsIcon2.setVisibility(View.INVISIBLE);
            imageCardParticipantsIcon3.setVisibility(View.INVISIBLE);
            imageCardParticipantsIcon4.setVisibility(View.INVISIBLE);
        }

        private void invisibleParticipantsCase2() {
            imageCardParticipantsIcon1.setVisibility(View.INVISIBLE);
            imageCardParticipantsIcon3.setVisibility(View.INVISIBLE);
            imageCardParticipantsIcon4.setVisibility(View.INVISIBLE);
        }

        private void invisibleParticipantsCase3() {
            imageCardParticipantsIcon1.setVisibility(View.INVISIBLE);
            imageCardParticipantsIcon2.setVisibility(View.INVISIBLE);
            imageCardParticipantsIcon4.setVisibility(View.INVISIBLE);
        }

        private void invisibleParticipantsCase4() {
            imageCardParticipantsIcon1.setVisibility(View.INVISIBLE);
            imageCardParticipantsIcon2.setVisibility(View.INVISIBLE);
            imageCardParticipantsIcon3.setVisibility(View.INVISIBLE);
            imageCardParticipantsIcon4plus.setVisibility(View.VISIBLE);

        }

        private void setProgressBarAccessibility(int progress) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                progressBarAccessibility.setProgress(progress);
            }
        }

        private void createLink(BoredAction boredAction) {
            textLink.setText(boredAction.getLink());
            textLink.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }
}
