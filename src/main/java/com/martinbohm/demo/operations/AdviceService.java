package com.martinbohm.demo.operations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.martinbohm.demo.entities.Advice;

@Service
@JsonRpcService("/advice")
public interface AdviceService {

    @JsonRpcMethod("GiveMeAdvice")
    public List<Advice> giveMeAdvice(String topic, Integer amount);

    @JsonRpcMethod("test")
    public String get();
}
