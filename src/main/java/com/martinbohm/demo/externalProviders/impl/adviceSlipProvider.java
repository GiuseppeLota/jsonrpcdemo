package com.martinbohm.demo.externalProviders.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.martinbohm.demo.dto.AdviceResponse;
import com.martinbohm.demo.externalProviders.AdviceProvider;

@Service
public class adviceSlipProvider implements AdviceProvider {

    public AdviceResponse findAll() {
        AdviceResponse result = WebClient.builder()
                .baseUrl("https://api.adviceslip.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(this::acceptedCodecs).build())
                .build()
                .get()
                .uri("/advice/search/spiders")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(AdviceResponse.class)
                .block()
                .getBody();
        return null;        
    }

    private void acceptedCodecs(ClientCodecConfigurer clientCodecConfigurer) {
        clientCodecConfigurer.customCodecs().registerWithDefaultConfig(new Jackson2JsonDecoder(new ObjectMapper(), MimeTypeUtils.TEXT_HTML));
        clientCodecConfigurer.customCodecs().registerWithDefaultConfig(new Jackson2JsonEncoder(new ObjectMapper(), MimeTypeUtils.TEXT_HTML));
      }

}
