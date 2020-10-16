package com.example.nerdlauncher;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NerdLauncherFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public NerdLauncherFragment() {
        // Required empty public constructor
    }

    public static NerdLauncherFragment newInstance(String param1, String param2) {
        NerdLauncherFragment fragment = new NerdLauncherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nerd_launcher, container, false);
        findViews(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.nerd_launcher_recycler);
    }
}