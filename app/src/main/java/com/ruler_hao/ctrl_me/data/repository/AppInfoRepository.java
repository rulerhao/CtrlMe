package com.ruler_hao.ctrl_me.data.repository;

import com.ruler_hao.ctrl_me.data.AppUsageHelper;

import java.util.List;

public interface AppInfoRepository {
    List<AppUsageHelper.AppInfo> loadAppData();
}
