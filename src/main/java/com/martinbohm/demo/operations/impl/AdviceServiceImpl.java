package com.martinbohm.demo.operations.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martinbohm.demo.dto.AdviceResponse;
import com.martinbohm.demo.entities.Advice;
import com.martinbohm.demo.externalProviders.AdviceProvider;
import com.martinbohm.demo.operations.AdviceService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;

@Service
@AutoJsonRpcServiceImpl
public class AdviceServiceImpl implements AdviceService  {

    @Autowired
    private AdviceProvider adviceProvider;

    @Override
    public List<Advice> giveMeAdvice(String topic, Integer amount) {

        AdviceResponse result =  adviceProvider.findAll();  

        return null;
    }

    @Override
    public String get() {
        
        return "test";
    }
}
