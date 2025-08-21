package com.ruler_hao.ctrl_me.domain.use_case;

import com.ruler_hao.ctrl_me.data.entity.StationData;
import com.ruler_hao.ctrl_me.domain.repository.TruckRepository;

import java.util.ArrayList;
import java.util.List;

public class TruckUseCase {

    private static final int LIMIT = 1000;

    private static TruckUseCase instance;  // 修正為 TruckUseCase 而非 ApiRequest
    private final TruckRepository repository;

    private TruckUseCase(TruckRepository repository) {
        this.repository = repository;
    }

    public static synchronized TruckUseCase getInstance(TruckRepository repository) {
        if (instance == null) {
            instance = new TruckUseCase(repository);
        }
        return instance;
    }

    public List<StationData> execute() {
        List<StationData> allData = new ArrayList<>();
        int offset = 0;
        while (true) {
            List<StationData> data = repository.getTruckData(offset, LIMIT).getResults();
            if (data.isEmpty()) break;
            allData.addAll(data);
            offset += data.size();
        }

        return allData;
    }
}
