package com.martinbohm.demo.operations;

import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.martinbohm.demo.entities.AdviceResponse;
import com.martinbohm.demo.entities.AdviceRequest;

@Service
@JsonRpcService("/advice")
public interface AdviceService {

    @JsonRpcMethod("GiveMeAdvice")
    public AdviceResponse giveMeAdvice(AdviceRequest request);

    @JsonRpcMethod("test")
    public String get();
}
