package com.zdl.rpc.server;

import com.zdl.rpc.Request;
import com.zdl.rpc.Response;
import com.zdl.rpc.codec.Decoder;
import com.zdl.rpc.codec.Encoder;
import com.zdl.rpc.transport.RequestHandler;
import com.zdl.rpc.transport.TransportServer;
import com.zdl.rpc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    private RequestHandler handler = new RequestHandler() {
        Response resp = new Response();
        @Override
        public void onRequest(InputStream receive, OutputStream toResp) {
            try {
                byte[] inBytes = IOUtils.readFully(receive, receive.available());
                Request request = RpcServer.this.decoder.decoder(inBytes, Request.class);
                log.info("get request: {}",request);
                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis, request);
                resp.setData(ret);

            } catch (IOException e) {
                log.warn(e.getMessage(),e);
                resp.setCode(1);
                resp.setMessage("RpcServer get error:"+e.getClass().getName()+":"+e.getMessage());
            }finally {
                byte[] outBytes = RpcServer.this.encoder.encoder(resp);
                try {
                    toResp.write(outBytes);
                    log.info("response client");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public RpcServer(RpcServerConfig config){
        this.config = config;
        //net
        this.net = ReflectionUtils.newInstance(config.getTransportClazz());
        this.net.init(config.getPort(), this.handler);
        //codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        //service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public void start() throws Exception{
        this.net.start();
    }
    public void stop(){
        this.net.stop();
    }

    public <T> void register(Class<T> interfaceClass, T bean){
        serviceManager.register(interfaceClass, bean);
    }

    public RpcServer(){
        this(new RpcServerConfig());
    }

}
