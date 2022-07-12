package com.zdl.rpc;

import lombok.Data;
/**
 * @description: 请求服务
 */
@Data
public class Request {

    private ServiceDescriptor serviceDescriptor;
    private Object[] parameters;
}
