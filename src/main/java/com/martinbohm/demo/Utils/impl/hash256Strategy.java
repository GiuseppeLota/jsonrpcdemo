package com.martinbohm.demo.Utils.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.martinbohm.demo.Utils.HashService;

@Service
public class hash256Strategy implements HashService {

    @Override
    public String Hash(String input) {
        return DigestUtils.sha256Hex(input);
    }

}