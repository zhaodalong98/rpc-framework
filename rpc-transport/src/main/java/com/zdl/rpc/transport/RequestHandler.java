package com.zdl.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @description: 处理网路请求的handler
 */
public interface RequestHandler {

    void onRequest(InputStream receive, OutputStream toResp);
}
