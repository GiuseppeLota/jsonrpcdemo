package com.martinbohm.demo.externalProviders;

import com.martinbohm.demo.dto.AdviceResponse;

public interface AdviceProvider {

    AdviceResponse listAdvices(String topic); 
}

