package com.zdl.rpc.server;

import com.zdl.rpc.Request;
import com.zdl.rpc.utils.ReflectionUtils;

/**
 * @description:调用具体的服务
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtils.invoke(
                service.getTarget(),service.getMethod(),request.getParameters());
    }
}
