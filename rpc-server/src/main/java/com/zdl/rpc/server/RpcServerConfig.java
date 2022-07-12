package com.zdl.rpc.server;

import com.zdl.rpc.codec.Decoder;
import com.zdl.rpc.codec.Encoder;
import com.zdl.rpc.codec.JSONDecoder;
import com.zdl.rpc.codec.JSONEncoder;
import com.zdl.rpc.transport.HttpTransportServer;
import com.zdl.rpc.transport.TransportServer;
import lombok.Data;

/**
 * @description: server配置
 */
@Data
public class RpcServerConfig {
    //网络协议
    private Class<? extends TransportServer> transportClazz = HttpTransportServer.class;
    //序列化
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    //反序列化
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private  int port = 3000;
}
