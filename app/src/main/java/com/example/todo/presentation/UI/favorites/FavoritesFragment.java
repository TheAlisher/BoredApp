package com.example.todo.presentation.UI.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.presentation.UI.OnItemClickListener;
import com.example.todo.model.BoredAction;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private BoredAdapter adapter;
    private ArrayList<BoredAction> card = new ArrayList<>();

    private CardView cardViewYouHaveNoSavedYet;
    LinearLayoutManager linearLayoutManagerBored;

    private ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback
            (0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, @NotNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            int position = viewHolder.getAdapterPosition();
            App.boredStorage.deleteBoredAction(card.get(position));
            card.remove(position);
            adapter.notifyDataSetChanged();
        }

        @Override
        public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            if (!App.appPreferences.isSwipeDeleteON()) return 0;
            return super.getSwipeDirs(recyclerView, viewHolder);
        }
    };

    public FavoritesFragment() {
    }

    public static Fragment newInstance() {
        return new FavoritesFragment();
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
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                if (App.appPreferences.isLiveDataON()) {
                    App.boredStorage.deleteBoredAction(card.get(position));
                }
                if (App.appPreferences.isManualDeleteON()) {
                    App.boredStorage.deleteBoredAction(card.get(position));
                    card.remove(position);
                    adapter.notifyDataSetChanged();
                }
                if (App.appPreferences.isSwipeDeleteON()) {
                    Toast.makeText(getContext(), "Для удаления свайпните", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loadData();
    }

    private void initializationViews(View view) {
        cardViewYouHaveNoSavedYet = view.findViewById(R.id.cardView_favorites_youHavNoSavedYet);
    }

    private void createRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_favorites);
        linearLayoutManagerBored = new LinearLayoutManager(getContext());
        linearLayoutManagerBored.setReverseLayout(true);
        linearLayoutManagerBored.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManagerBored);
        adapter = new BoredAdapter(card);
        recyclerView.setAdapter(adapter);
    }

    private void loadData() {
        if (App.boredStorage.getAllActions().isEmpty()) {
            cardViewYouHaveNoSavedYet.setVisibility(View.VISIBLE);
        } else {
            cardViewYouHaveNoSavedYet.setVisibility(View.GONE);
        }
        if (App.appPreferences.isManualDeleteON()) {
            card.clear();
            card.addAll(App.boredStorage.getAllActions());
            adapter.notifyDataSetChanged();
        } else if (App.appPreferences.isLiveDataON()) {
            App.boredStorage
                    .getAllActionsLive()
                    .observe(getViewLifecycleOwner(), new Observer<List<BoredAction>>() {
                        @Override
                        public void onChanged(List<BoredAction> boredActions) {
                            card.clear();
                            card.addAll(boredActions);
                            adapter.notifyDataSetChanged();
                        }
                    });
        } else if (App.appPreferences.isSwipeDeleteON()) {
            card.clear();
            card.addAll(App.boredStorage.getAllActions());
            adapter.notifyDataSetChanged();
            createItemTouchHelperForRecyclerView();
        }
    }

    private void createItemTouchHelperForRecyclerView() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onPause() {
        super.onPause();
        loadData();
    }
}
