package com.example.bored.presentation_ui.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.example.bored.App;
import com.example.bored.R;
import com.example.bored.presentation_ui.OnItemClickListener;
import com.example.bored.model.BoredAction;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private BoredAdapter adapter;
    private ArrayList<BoredAction> card = new ArrayList<>();
    private CardView cardViewYouHaveNoSavedYet;

    private ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback
            (0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, @NotNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            int position = viewHolder.getAdapterPosition();
            App.boredRepository.deleteBoredAction(card.get(position));
            card.remove(position);
            adapter.notifyItemRemoved(position);
        }

        @Override
        public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            if (!App.appPreferences.isSwipeDelete()) return 0;
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
                favoritesImageFavoriteClick(position);
            }
        });
    }

    private void initializationViews(View view) {
        cardViewYouHaveNoSavedYet = view.findViewById(R.id.cardView_favorites_youHavNoSavedYet);
    }

    private void createRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_favorites);
        LinearLayoutManager linearLayoutManagerBored = new LinearLayoutManager(getContext());
        linearLayoutManagerBored.setReverseLayout(true);
        linearLayoutManagerBored.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManagerBored);
        adapter = new BoredAdapter(card);
        recyclerView.setAdapter(adapter);
    }

    private void favoritesImageFavoriteClick(int position) {
        if (App.appPreferences.isClickDelete()) {
            if (App.appPreferences.isManualData()) {
                App.boredRepository.deleteBoredAction(card.get(position));
                card.remove(position);
                adapter.notifyDataSetChanged();
            } else if (App.appPreferences.isLiveData()) {
                App.boredRepository.deleteBoredAction(card.get(position));
            }
        } else if (App.appPreferences.isSwipeDelete()) {
            Toast.makeText(getContext(), "Для удаления свайпните", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
        setItemTouchHelper();
        createRecyclerViewAnimation();
    }

    @Override
    public void onPause() {
        super.onPause();
        loadData();
    }

    private void loadData() {
        if (App.boredRepository.getAllActions().isEmpty()) {
            cardViewYouHaveNoSavedYet.setVisibility(View.VISIBLE);
        } else {
            cardViewYouHaveNoSavedYet.setVisibility(View.GONE);
        }
        int cardSize = card.size();
        if (App.appPreferences.isManualData()) {
            card.clear();
            card.addAll(App.boredRepository.getAllActions());
            adapter.notifyDataSetChanged();
        } else if (App.appPreferences.isLiveData()) {
            App.boredRepository
                    .getAllActionsLive()
                    .observe(getViewLifecycleOwner(), new Observer<List<BoredAction>>() {
                        @Override
                        public void onChanged(List<BoredAction> boredActions) {
                            card.clear();
                            card.addAll(boredActions);
                            adapter.notifyDataSetChanged();
                        }
                    });
        }
        if (cardSize < App.boredRepository.getAllActions().size()) {
            recyclerView.scrollToPosition(card.size() - 1);
        }
    }

    private void setItemTouchHelper() {
        if (App.appPreferences.isSwipeDelete()) {
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
    }

    private void createRecyclerViewAnimation() {
        LayoutAnimationController layoutAnimationController =
                AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_fall_down);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        adapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
