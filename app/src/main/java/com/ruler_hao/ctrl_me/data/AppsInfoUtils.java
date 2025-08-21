package com.ruler_hao.ctrl_me.data;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;

public class AppsInfoUtils {
    public void getAppList() {

    }

    public boolean hasUsageStatsPermission(Context context) {
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Binder.getCallingUid(),
                context.getPackageName()
        );
        return (mode == AppOpsManager.MODE_ALLOWED);
    }
}
