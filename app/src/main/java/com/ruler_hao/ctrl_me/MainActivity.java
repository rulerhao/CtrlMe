package com.ruler_hao.ctrl_me;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruler_hao.ctrl_me.data.AppUsageHelper;
import com.ruler_hao.ctrl_me.presentation.AppInfoAdapter;
import com.ruler_hao.ctrl_me.presentation.view.AppListFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends FragmentActivity implements AppInfoAdapter.OnMonitorSwitchListener {

    private RecyclerView rvAppList;
    private AppInfoAdapter appInfoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadFragment(new AppListFragment());
        }
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void initViews() {
        rvAppList = findViewById(R.id.rv_app_list);
        rvAppList.setLayoutManager(new LinearLayoutManager(this));
    }
    
    private void loadAppData() {
        // 取得已安裝應用
        List<AppUsageHelper.AppInfo> installedApps = AppUsageHelper.getInstalledApps(this);
        
        // 取得今天的使用時間
        List<AppUsageHelper.AppInfo> dayUsage = AppUsageHelper.getAppUsageStats(this, "day");
        
        // 合併安裝應用和使用時間數據
        List<AppUsageHelper.AppInfo> combinedAppList = combineAppData(installedApps, dayUsage);
        
        // 設置適配器
        appInfoAdapter = new AppInfoAdapter(combinedAppList);
        appInfoAdapter.setOnMonitorSwitchListener(this);
        rvAppList.setAdapter(appInfoAdapter);
        
        Log.d("MainActivity", "顯示應用程式數量: " + combinedAppList.size());
    }
    
    private List<AppUsageHelper.AppInfo> combineAppData(List<AppUsageHelper.AppInfo> installedApps, 
                                                        List<AppUsageHelper.AppInfo> usageStats) {
        Map<String, Long> usageMap = new HashMap<>();
        
        // 建立使用時間映射
        for (AppUsageHelper.AppInfo usageApp : usageStats) {
            usageMap.put(usageApp.packageName, usageApp.usageTimeMillis);
        }
        
        // 更新安裝應用的使用時間
        List<AppUsageHelper.AppInfo> combinedList = new ArrayList<>();
        for (AppUsageHelper.AppInfo installedApp : installedApps) {
            Long usageTime = usageMap.get(installedApp.packageName);
            if (usageTime != null) {
                installedApp.usageTimeMillis = usageTime;
            }
            combinedList.add(installedApp);
        }
        
        return combinedList;
    }
    
    @Override
    public void onMonitorSwitchChanged(AppUsageHelper.AppInfo appInfo, boolean isChecked) {
        Log.d("MainActivity", "監控狀態變更: " + appInfo.appName + " -> " + isChecked);
        // 這裡可以添加保存監控狀態的邏輯，例如保存到 SharedPreferences 或數據庫
    }
}
