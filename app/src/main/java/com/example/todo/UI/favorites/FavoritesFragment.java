package com.example.todo.UI.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.UI.models.Bored;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private BoredAdapter adapter;
    private ArrayList<Bored> card = new ArrayList<>();

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
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        card.addAll(App.getInstance().getDatabase().boredDao().getAll());
        adapter = new BoredAdapter(card);
        recyclerView.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        App
                .getInstance()
                .getDatabase()
                .boredDao()
                .getAllLive()
                .observe(getViewLifecycleOwner(), new Observer<List<Bored>>() {
                    @Override
                    public void onChanged(List<Bored> boreds) {
                        card.clear();
                        card.addAll(boreds);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}