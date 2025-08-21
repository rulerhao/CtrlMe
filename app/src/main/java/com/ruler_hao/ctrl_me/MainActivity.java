package com.ruler_hao.ctrl_me;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.ruler_hao.ctrl_me.data.AppUsageHelper;
import com.ruler_hao.ctrl_me.data.AppsInfoUtils;

import java.util.List;

public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 確認權限
        if (!new AppsInfoUtils().hasUsageStatsPermission(this)) {
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        }

        // 取得已安裝應用
        List<AppUsageHelper.AppInfo> apps = AppUsageHelper.getInstalledApps(this);

// 取得過去一小時的使用時間
        List<AppUsageHelper.AppInfo> hourUsage = AppUsageHelper.getAppUsageStats(this, "hour");

// 取得今天的使用時間
        List<AppUsageHelper.AppInfo> dayUsage = AppUsageHelper.getAppUsageStats(this, "day");

// 取得這週的使用時間
        List<AppUsageHelper.AppInfo> weekUsage = AppUsageHelper.getAppUsageStats(this, "week");

        Log.d("MainActivity", "已安裝應用程式數量: " + apps.size());
        Log.d("MainActivity", "過去一小時使用時間: " + hourUsage.size());
        Log.d("MainActivity", "今天使用時間: " + dayUsage.size());
        Log.d("MainActivity", "這週使用時間: " + weekUsage.size());
    }
}
