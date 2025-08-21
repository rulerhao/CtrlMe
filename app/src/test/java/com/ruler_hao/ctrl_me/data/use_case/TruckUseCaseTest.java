package com.ruler_hao.ctrl_me.data.use_case;

import com.ruler_hao.ctrl_me.data.api.ApiRequest;
import com.ruler_hao.ctrl_me.data.impl.TruckRepositoryImpl;
import com.ruler_hao.ctrl_me.domain.repository.TruckRepository;
import com.ruler_hao.ctrl_me.domain.use_case.TruckUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TruckUseCaseTest {

    @Mock
    private ApiRequest apiRequest;
    private TruckUseCase truckUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        TruckRepository truckRepository = new TruckRepositoryImpl(apiRequest);
        truckUseCase = TruckUseCase.getInstance(truckRepository);
    }

    @Test
    public void testExecute() {
        truckUseCase.execute();
    }
}
