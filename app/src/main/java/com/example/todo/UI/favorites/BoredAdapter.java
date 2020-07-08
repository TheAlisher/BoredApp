package com.example.todo.UI.favorites;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.UI.models.Bored;

import java.util.ArrayList;

public class BoredAdapter extends RecyclerView.Adapter<BoredAdapter.BoredViewHolder> {

    private ArrayList<Bored> card;

    public BoredAdapter(ArrayList<Bored> card) {
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
        holder.setIsRecyclable(true);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BoredViewHolder extends RecyclerView.ViewHolder {

        private TextView textCategory;
        private TextView textExplore;
        private TextView textPrice;
        private View viewPersonCircle1;
        private View viewPersonCircle2;
        private View viewPersonCircle3;
        private View viewPersonCircle4;
        private ProgressBar progressBarAccessibility;
        private TextView textLink;

        public BoredViewHolder(@NonNull View itemView) {
            super(itemView);
            initializationViews(itemView);
        }

        private void initializationViews(View itemView) {
            textCategory = itemView.findViewById(R.id.text_listBored_category);
            textExplore = itemView.findViewById(R.id.text_listBored_explore);
            textPrice = itemView.findViewById(R.id.rangeSlider_price);
            viewPersonCircle1 = itemView.findViewById(R.id.view_listBored_person_circle_1);
            viewPersonCircle2 = itemView.findViewById(R.id.view_listBored_person_circle_2);
            viewPersonCircle3 = itemView.findViewById(R.id.view_listBored_person_circle_3);
            viewPersonCircle4 = itemView.findViewById(R.id.view_listBored_person_circle_4);
            progressBarAccessibility = itemView.findViewById(R.id.progressBar_listBored_accessibility);
            textLink = itemView.findViewById(R.id.text_listBored_link);
        }

        public void onBind(Bored bored) {
            textCategory.setText(bored.getCategory());
            textExplore.setText(bored.getExplore());
            textPrice.setText(bored.getPrice() + "$");
            createParticipants(bored);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                progressBarAccessibility.setProgress((int) (bored.getAccessibility() * 100), true);
            }
            textLink.setText(bored.getLink());
        }

        private void createParticipants(Bored bored) {
            switch (bored.getParticipants()) {
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

    }
}
