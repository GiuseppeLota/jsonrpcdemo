package com.martinbohm.demo.operations;

import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/test")
public interface UserService {

    public String get();
}
