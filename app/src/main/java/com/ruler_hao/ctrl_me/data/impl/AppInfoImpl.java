package com.ruler_hao.ctrl_me.data.impl;

import android.content.Context;

import com.ruler_hao.ctrl_me.data.AppUsageHelper;
import com.ruler_hao.ctrl_me.data.repository.AppInfoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppInfoImpl implements AppInfoRepository {

    private final Context context;

    public AppInfoImpl(Context context) {
        this.context = context;
    }

    public List<AppUsageHelper.AppInfo> loadAppData() {
        List<AppUsageHelper.AppInfo> installedApps = AppUsageHelper.getInstalledApps(context);

        List<AppUsageHelper.AppInfo> dayUsage = AppUsageHelper.getAppUsageStats(context, "day");

        return combineAppData(installedApps, dayUsage);
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
}
