package com.zdl.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @description: fastjson实现序列化
 */
public class JSONEncoder implements Encoder {
    @Override
    public byte[] encoder(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
