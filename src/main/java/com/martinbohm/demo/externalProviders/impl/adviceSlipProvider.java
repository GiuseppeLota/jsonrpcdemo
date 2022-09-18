package com.martinbohm.demo.externalProviders.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.martinbohm.demo.dto.AdviceResult;
import com.martinbohm.demo.externalProviders.AdviceProvider;

@Service
public class adviceSlipProvider implements AdviceProvider {

    @Autowired
    WebClient webClient;

    @Value("${advice.slip.searchPath}")
    private String searchPath;

    public AdviceResult listAdvices(String topic) {
        String searchUri = String.format("%s/%s", searchPath, topic);

        return webClient
                .get()
                .uri(searchUri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(AdviceResult.class)
                .block()
                .getBody();
    }

}
