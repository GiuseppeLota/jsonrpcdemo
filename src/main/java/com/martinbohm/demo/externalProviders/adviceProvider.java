package com.martinbohm.demo.externalProviders;

import com.martinbohm.demo.dto.AdviceResult;

public interface AdviceProvider {

    AdviceResult listAdvices(String topic); 
}

