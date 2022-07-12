package com.zdl.rpc.client;


import com.zdl.rpc.Request;
import com.zdl.rpc.Response;
import com.zdl.rpc.ServiceDescriptor;
import com.zdl.rpc.codec.Decoder;
import com.zdl.rpc.codec.Encoder;
import com.zdl.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用远程服务的代理类
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {

    private  TransportSelector selector;
    private  Decoder decoder;
    private  Class clazz;
    private  Encoder encoder;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);
        Response resp = invokeRemote(request);
        if(resp == null || resp.getCode() != 0){
            throw new IllegalStateException("fail to invoke remote:" +resp);
        }
        return resp.getData();
    }

    private Response invokeRemote(Request request) {
        Response response = null;
        TransportClient client = null;

        try {
            client = selector.select();
            byte[] encoder = this.encoder.encoder(request);
            InputStream write = client.write(new ByteArrayInputStream(encoder));
            byte[] bytes = IOUtils.readFully(write, write.available());
            response = decoder.decoder(bytes, Response.class);
        } catch (IOException e) {
            log.warn("[invokeRemote] e={},{}",e.getMessage(),e);
            response = new Response();
            response.setCode(1);
            response.setMessage("Rpc error"+e.getClass()+":"+e.getMessage());
        } finally {
            if(client!=null)
                selector.release(client);
        }
            return response;
    }
}
