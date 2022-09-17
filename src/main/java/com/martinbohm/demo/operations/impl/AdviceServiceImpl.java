package com.martinbohm.demo.operations.impl;

import java.util.List;
import java.util.Objects;
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

        //validation: topic must be specified
        //validation: amount should be more than 0

        AdviceResponse result = adviceProvider.listAdvices(request.getTopic());
        Integer amount = amountFor(request.getAmount(), result.getSlips().size());

        List<String> advices = result
                .getSlips()
                .stream()
                .map(adv -> adv.getAdvice())
                .limit(amount)
                .collect(Collectors.toList());

        Advice adviceResponse = new Advice();
        adviceResponse.setAdviceList(advices);

        return adviceResponse;
    }

    private int amountFor(Integer amount, int slipsTotal) {

        if (!Objects.isNull(amount) && amount < slipsTotal) {
            return amount;
        }
        return slipsTotal;
    }

    @Override
    public String get() {

        return "test";
    }
}
