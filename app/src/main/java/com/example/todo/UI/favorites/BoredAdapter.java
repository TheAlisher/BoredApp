package com.example.todo.UI.favorites;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        /*if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(R.color.Midnight);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }*/
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

        private ImageView imageFavoriteSelected;
        private TextView textCategory;
        private TextView textExplore;
        private TextView textPrice;
        private ImageView imageCardPersonCircle1;
        private ImageView imageCardPersonCircle2;
        private ImageView imageCardPersonCircle3;
        private ImageView imageViewPersonCircle4;
        private ImageView imageCardUserIconPlus;
        private ProgressBar progressBarAccessibility;
        private TextView textLink;

        public BoredViewHolder(@NonNull View itemView) {
            super(itemView);
            initializationViews(itemView);
            imageFavoriteSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnItemClick(getAdapterPosition());
                    imageFavoriteSelected.setImageResource(R.drawable.icon_favorite_blue);
                }
            });
        }

        private void initializationViews(View itemView) {
            imageFavoriteSelected = itemView.findViewById(R.id.image_listBored_favorite_selected);
            textCategory = itemView.findViewById(R.id.text_listBored_category);
            textExplore = itemView.findViewById(R.id.text_listBored_explore);
            textPrice = itemView.findViewById(R.id.text_listBored_free);
            imageCardPersonCircle1 = itemView.findViewById(R.id.image_card_user_icon_1);
            imageCardPersonCircle2 = itemView.findViewById(R.id.image_card_user_icon_2);
            imageCardPersonCircle3 = itemView.findViewById(R.id.image_card_user_icon_3);
            imageViewPersonCircle4 = itemView.findViewById(R.id.image_card_user_icon_4);
            imageCardUserIconPlus = itemView.findViewById(R.id.image_card_user_icon_plus);
            progressBarAccessibility = itemView.findViewById(R.id.progressBar_listBored_accessibility);
            textLink = itemView.findViewById(R.id.text_listBored_link);
        }

        public void onBind(BoredAction boredAction) {
            textCategory.setText(boredAction.getType());
            textExplore.setText(boredAction.getActivity());
            textPrice.setText(boredAction.getPrice().toString() + '$');
            createParticipants(boredAction);
            setProgressBarAccessibility((int) (boredAction.getAccessibility() * 100));
            textLink.setText(boredAction.getLink());
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
            imageCardPersonCircle1.setVisibility(View.VISIBLE);
            imageCardPersonCircle2.setVisibility(View.VISIBLE);
            imageCardPersonCircle3.setVisibility(View.VISIBLE);
            imageViewPersonCircle4.setVisibility(View.VISIBLE);
            imageCardUserIconPlus.setVisibility(View.INVISIBLE);
        }

        private void invisibleParticipantsCase1() {
            imageCardPersonCircle2.setVisibility(View.INVISIBLE);
            imageCardPersonCircle3.setVisibility(View.INVISIBLE);
            imageViewPersonCircle4.setVisibility(View.INVISIBLE);
        }

        private void invisibleParticipantsCase2() {
            imageCardPersonCircle1.setVisibility(View.INVISIBLE);
            imageCardPersonCircle3.setVisibility(View.INVISIBLE);
            imageViewPersonCircle4.setVisibility(View.INVISIBLE);
        }

        private void invisibleParticipantsCase3() {
            imageCardPersonCircle1.setVisibility(View.INVISIBLE);
            imageCardPersonCircle2.setVisibility(View.INVISIBLE);
            imageViewPersonCircle4.setVisibility(View.INVISIBLE);
        }

        private void invisibleParticipantsCase4() {
            imageCardPersonCircle1.setVisibility(View.INVISIBLE);
            imageCardPersonCircle2.setVisibility(View.INVISIBLE);
            imageCardPersonCircle3.setVisibility(View.INVISIBLE);
            imageCardUserIconPlus.setVisibility(View.VISIBLE);

        }

        private void setProgressBarAccessibility(int progress) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                progressBarAccessibility.setProgress(progress, true);
            }
        }
    }
}
