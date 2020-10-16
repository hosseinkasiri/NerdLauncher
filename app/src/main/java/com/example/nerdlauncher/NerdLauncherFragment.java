package com.example.nerdlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class NerdLauncherFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ActivityAdapter mActivityAdapter;
    private List<ResolveInfo> mActivities;

    public NerdLauncherFragment() {
        // Required empty public constructor
    }

    public static NerdLauncherFragment newInstance() {
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
        setupAdapter();
        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.nerd_launcher_recycler);
    }

    private void setupAdapter(){
        final PackageManager packageManager = Objects.requireNonNull(getActivity()).getPackageManager();
        Intent queryIntent = new Intent(Intent.ACTION_MAIN);
        queryIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> activities = packageManager.queryIntentActivities(queryIntent, 0);
        Collections.sort(activities, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo o1, ResolveInfo o2) {
                String appName1 = o1.loadLabel(packageManager).toString();
                String appName2 = o2.loadLabel(packageManager).toString();
                return String.CASE_INSENSITIVE_ORDER.compare(appName1, appName2);
            }
        });
        mActivities = activities;
        if (mActivityAdapter == null) {
            mActivityAdapter = new ActivityAdapter(mActivities);
            mRecyclerView.setAdapter(mActivityAdapter);
        }else{
            mActivityAdapter.setActivities(mActivities);
            mActivityAdapter.notifyDataSetChanged();
        }
    }

    private class ActivityHolder extends RecyclerView.ViewHolder{

        private TextView mTitleText;
        private ResolveInfo mResolveInfo;

        public ActivityHolder(@NonNull View itemView) {
            super(itemView);
            mTitleText = (TextView) itemView;
        }

        public void bind(ResolveInfo resolveInfo){
            mResolveInfo = resolveInfo;
            mTitleText.setText(mResolveInfo.loadLabel(getActivity().getPackageManager()).toString());
        }
    }

    private class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder>{

        private List<ResolveInfo> mActivities;

        public ActivityAdapter(List<ResolveInfo> activities) {
            mActivities = activities;
        }

        public void setActivities(List<ResolveInfo> activities) {
            mActivities = activities;
        }

        @NonNull
        @Override
        public ActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ActivityHolder(new TextView(getContext()));
        }

        @Override
        public void onBindViewHolder(@NonNull ActivityHolder holder, int position) {
            ResolveInfo resolveInfo = mActivities.get(position);
            holder.bind(resolveInfo);
        }

        @Override
        public int getItemCount() {
            return mActivities.size();
        }
    }
}