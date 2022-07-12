package com.zdl.rpc.codec;

/**
 * @description: 序列化
 */
public interface Encoder {

    byte[] encoder(Object obj);
}
