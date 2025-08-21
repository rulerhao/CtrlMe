package com.ruler_hao.ctrl_me.presentation.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruler_hao.ctrl_me.R;
import com.ruler_hao.ctrl_me.data.AppUsageHelper;
import com.ruler_hao.ctrl_me.presentation.AppInfoAdapter;
import com.ruler_hao.ctrl_me.presentation.view_model.AppListFragmentViewmodel;

public class AppListFragment extends Fragment implements AppInfoAdapter.OnMonitorSwitchListener {

    private AppListFragmentViewmodel viewmodel;

    private RecyclerView rvAppList;
    private AppInfoAdapter appInfoAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewmodel = new AppListFragmentViewmodel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_app_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvAppList = view.findViewById(R.id.rv_app_list);
        rvAppList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        loadAppData();
    }

    private void loadAppData() {
        appInfoAdapter = new AppInfoAdapter(viewmodel.appInfoList);
        appInfoAdapter.setOnMonitorSwitchListener(this);
        rvAppList.setAdapter(appInfoAdapter);
    }

    @Override
    public void onMonitorSwitchChanged(AppUsageHelper.AppInfo appInfo, boolean isChecked) {
        Log.d("AppListFragment", "監控狀態變更: " + appInfo.appName + " -> " + isChecked);
    }
}
