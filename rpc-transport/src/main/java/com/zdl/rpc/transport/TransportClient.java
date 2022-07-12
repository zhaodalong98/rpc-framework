package com.zdl.rpc.transport;

import com.zdl.rpc.Peer;

import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * @description: 1.创建连接
 * 2.发送数据，并且等待响应
 * 3.关闭连接
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data) throws MalformedURLException;

    void close();
}
