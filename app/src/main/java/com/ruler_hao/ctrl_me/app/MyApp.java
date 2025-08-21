package com.ruler_hao.ctrl_me.app;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import com.ruler_hao.ctrl_me.data.api.ApiRequest;
import com.ruler_hao.ctrl_me.data.impl.AppInfoImpl;
import com.ruler_hao.ctrl_me.data.impl.TruckRepositoryImpl;
import com.ruler_hao.ctrl_me.domain.use_case.AppInfoUseCase;
import com.ruler_hao.ctrl_me.domain.use_case.TruckUseCase;

public class MyApp extends Application {
    public static ApiRequest apiRequest;
    public static TruckUseCase truckUseCase;
    public static LocationManager locationManager;
    public static AppInfoUseCase appInfoUseCase;

    @Override
    public void onCreate() {
        super.onCreate();

        apiRequest = ApiRequest.getInstance();
        truckUseCase = TruckUseCase.getInstance(new TruckRepositoryImpl(apiRequest));
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        appInfoUseCase = AppInfoUseCase.getInstance(new AppInfoImpl(this));
    }
}
