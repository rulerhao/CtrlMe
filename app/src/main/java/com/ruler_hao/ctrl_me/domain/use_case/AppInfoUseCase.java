package com.ruler_hao.ctrl_me.domain.use_case;

import com.ruler_hao.ctrl_me.data.AppUsageHelper;
import com.ruler_hao.ctrl_me.data.repository.AppInfoRepository;

import java.util.List;

public class AppInfoUseCase {
    private static AppInfoUseCase instance;  // 修正為 TruckUseCase 而非 ApiRequest
    private final AppInfoRepository repository;

    private AppInfoUseCase(AppInfoRepository repository) {
        this.repository = repository;
    }

    public static synchronized AppInfoUseCase getInstance(AppInfoRepository repository) {
        if (instance == null) {
            instance = new AppInfoUseCase(repository);
        }
        return instance;
    }

    public List<AppUsageHelper.AppInfo> loadAppData() {
        return repository.loadAppData();
    }
}