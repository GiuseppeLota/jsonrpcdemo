package com.martinbohm.demo.operations;

import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.martinbohm.demo.entities.Advice;
import com.martinbohm.demo.entities.AdviceRequest;

@Service
@JsonRpcService("/advice")
public interface AdviceService {

    @JsonRpcMethod("GiveMeAdvice")
    public Advice giveMeAdvice(AdviceRequest request);

    @JsonRpcMethod("test")
    public String get();
}
