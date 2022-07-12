package com.zdl.rpc.client;

import com.zdl.rpc.Peer;
import com.zdl.rpc.codec.Decoder;
import com.zdl.rpc.codec.Encoder;
import com.zdl.rpc.codec.JSONDecoder;
import com.zdl.rpc.codec.JSONEncoder;
import com.zdl.rpc.transport.HTTPTransportClient;
import com.zdl.rpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> transportSelector = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> peers = Arrays.asList(new Peer("127.0.0.1",3000));
}
