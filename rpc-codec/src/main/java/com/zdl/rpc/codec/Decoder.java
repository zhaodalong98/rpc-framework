package com.zdl.rpc.codec;

/**
 * @description: εεΊεε
 */
public interface Decoder {

    <T> T decoder(byte[] bytes, Class<T> clazz);
}
