package com.martinbohm.demo.externalProviders.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.martinbohm.demo.dto.AdviceResponse;
import com.martinbohm.demo.externalProviders.AdviceProvider;

@Service
public class adviceSlipProvider implements AdviceProvider {

    @Autowired
    WebClient webClient;

    public AdviceResponse findAll() {
        return webClient
                .get()
                .uri("/advice/search/spiders")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(AdviceResponse.class)
                .block()
                .getBody();
    }

}
