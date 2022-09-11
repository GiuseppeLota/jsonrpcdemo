package com.martinbohm.demo.operations.impl;

import org.springframework.stereotype.Service;

import com.martinbohm.demo.operations.UserService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;

@Service
@AutoJsonRpcServiceImpl
public class UserServiceImpl implements UserService  {

    public String get() {
        return "Yes";
    }
}
