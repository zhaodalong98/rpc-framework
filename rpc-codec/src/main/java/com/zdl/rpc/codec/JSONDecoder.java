package com.zdl.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @description: fastjson实现反序列化
 */
public class JSONDecoder implements Decoder {
    @Override
    public <T> T decoder(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
