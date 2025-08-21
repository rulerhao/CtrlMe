package com.ruler_hao.ctrl_me.data;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AppUsageHelper {

    // 資料結構：App 資訊
    public static class AppInfo {
        public String appName;
        public String packageName;
        public Drawable icon;
        public long usageTimeMillis;

        public AppInfo(String appName, String packageName, Drawable icon, long usageTimeMillis) {
            this.appName = appName;
            this.packageName = packageName;
            this.icon = icon;
            this.usageTimeMillis = usageTimeMillis;
        }
    }

    /**
     * 取得已安裝的應用程式清單 (不包含系統應用)
     */
    public static List<AppInfo> getInstalledApps(Context context) {
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> apps = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        List<AppInfo> appList = new ArrayList<>();

        for (ApplicationInfo app : apps) {
            // 過濾掉系統 app
            if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String name = pm.getApplicationLabel(app).toString();
                Drawable icon = pm.getApplicationIcon(app);
                appList.add(new AppInfo(name, app.packageName, icon, 0));
            }
        }
        return appList;
    }

    /**
     * 取得特定時間區間的應用使用時間
     * @param context Context
     * @param interval "hour", "day", "week", "month"
     * @return List<AppInfo>
     */
    public static List<AppInfo> getAppUsageStats(Context context, String interval) {
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        PackageManager pm = context.getPackageManager();

        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        long startTime;

        switch (interval) {
            case "hour":
                calendar.add(Calendar.HOUR_OF_DAY, -1);
                break;
            case "day":
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                break;
            case "week":
                calendar.add(Calendar.DAY_OF_MONTH, -7);
                break;
            case "month":
                calendar.add(Calendar.MONTH, -1);
                break;
            default:
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                break;
        }

        startTime = calendar.getTimeInMillis();

        List<UsageStats> stats = usm.queryUsageStats(
                UsageStatsManager.INTERVAL_BEST, startTime, endTime);

        List<AppInfo> usageList = new ArrayList<>();

        if (stats != null) {
            for (UsageStats usageStats : stats) {
                long totalTime = usageStats.getTotalTimeInForeground();
                if (totalTime > 0) {
                    try {
                        ApplicationInfo appInfo = pm.getApplicationInfo(usageStats.getPackageName(), 0);
                        String appName = pm.getApplicationLabel(appInfo).toString();
                        Drawable icon = pm.getApplicationIcon(appInfo);

                        usageList.add(new AppInfo(appName, usageStats.getPackageName(), icon, totalTime));
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return usageList;
    }
}