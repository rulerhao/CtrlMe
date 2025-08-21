package com.ruler_hao.ctrl_me.domain.repository;

import com.ruler_hao.ctrl_me.data.entity.TruckData;

public interface TruckRepository {
    TruckData getTruckData(int offset, int limit);
}