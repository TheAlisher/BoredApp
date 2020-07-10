package com.example.todo.UI.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.UI.OnItemClickListener;
import com.example.todo.model.BoredAction;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private BoredAdapter adapter;
    private ArrayList<BoredAction> card = new ArrayList<>();

    private CardView cardViewYouHaveNoSavedYet;
    LinearLayoutManager linearLayoutManagerBored;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializationViews(view);
        createRecyclerView(view);
        checkDatabase();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                App.boredStorage.deleteBoredAction(card.get(position));
            }
        });
    }

    private void initializationViews(View view) {
        cardViewYouHaveNoSavedYet = view.findViewById(R.id.cardView_favorites_youHavNoSavedYet);
    }

    private void createRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_favorites);
        linearLayoutManagerBored = new LinearLayoutManager(getContext());
        linearLayoutManagerBored.setReverseLayout(true);
        linearLayoutManagerBored.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManagerBored);
        card.addAll(App.boredStorage.getAllActions());
        adapter = new BoredAdapter(card);
        recyclerView.setAdapter(adapter);
    }

    private void checkDatabase() {
        if (card.isEmpty()) {
            cardViewYouHaveNoSavedYet.setVisibility(View.VISIBLE);
        } else {
            cardViewYouHaveNoSavedYet.setVisibility(View.GONE);
        }
    }
}