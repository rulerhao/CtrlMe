package com.ruler_hao.ctrl_me.presentation.view_model;

import com.ruler_hao.ctrl_me.app.MyApp;
import com.ruler_hao.ctrl_me.data.AppUsageHelper;

import java.util.List;

public class AppListFragmentViewmodel {

    public List<AppUsageHelper.AppInfo> appInfoList;

    public AppListFragmentViewmodel() {
        appInfoList = MyApp.appInfoUseCase.loadAppData();
    }
}
