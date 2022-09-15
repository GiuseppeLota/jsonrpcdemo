package com.martinbohm.demo.operations.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.martinbohm.demo.config.Constants;
import com.martinbohm.demo.dto.AdviceResponse;
import com.martinbohm.demo.entities.Advice;
import com.martinbohm.demo.entities.AdviceRequest;
import com.martinbohm.demo.externalProviders.AdviceProvider;
import com.martinbohm.demo.operations.AdviceService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;

@Service
@AutoJsonRpcServiceImpl
public class AdviceServiceImpl implements AdviceService {

    @Autowired
    private AdviceProvider adviceProvider;

    @Override
    @Cacheable(value = Constants.CACHE_NAME, key = "#request.topic.concat(#request.amount)")
    public Advice giveMeAdvice(AdviceRequest request) {

        AdviceResponse result = adviceProvider.findAll();

        List<String> advices = result
                .getSlips()
                .stream()
                .map(adv -> adv.getAdvice())
                .collect(Collectors.toList());

        return new Advice(advices);
    }

    @Override
    public String get() {

        return "test";
    }
}
