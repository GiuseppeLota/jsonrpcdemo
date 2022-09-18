package com.martinbohm.demo.operations.impl;

import java.util.ArrayList;
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
    @Cacheable(value = Constants.CACHE_NAME)
    public Advice giveMeAdvice(AdviceRequest request) {

        if (request.getTopic().isBlank() || request.getTopic().isEmpty()) {
            throw new IllegalArgumentException("Topic should not be empty or blank");
        }

        if (!Objects.isNull(request.getAmount()) && !(request.getAmount() > 0)) {
            throw new IllegalArgumentException("Amount should be more than 0");
        }

        AdviceResponse result = adviceProvider.listAdvices(request.getTopic());

        Advice adviceResponse = new Advice();

        if (!Objects.isNull(result.getMessage())) {
            adviceResponse.setErrorType(result.getMessage().getType());
            adviceResponse.setErrorMessage(result.getMessage().getText());
            adviceResponse.setAdviceList(new ArrayList<String>());
            return adviceResponse;
        }

        int limit = LimitFor(request.getAmount(), result.getSlips().size());

        List<String> advices = result
                .getSlips()
                .stream()
                .map(adv -> adv.getAdvice())
                .limit(limit)
                .collect(Collectors.toList());

        adviceResponse.setAdviceList(advices);

        return adviceResponse;
    }

    private int LimitFor(Integer amount, int slipsTotal) {

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
