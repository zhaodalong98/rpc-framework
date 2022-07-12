package com.zdl.rpc.client;

import com.zdl.rpc.Peer;
import com.zdl.rpc.transport.TransportClient;
import com.zdl.rpc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class RandomTransportSelector implements TransportSelector{

    /**
     *  以及连接好的client
     */
    private List<TransportClient> clients;
    public RandomTransportSelector(){
        this.clients = new ArrayList<>();
    }

    @Override
    public void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
            count = Math.max(count, 1);
            for(Peer peer : peers){
                for(int i = 0; i < count; i++){
                    TransportClient client = ReflectionUtils.newInstance(clazz);
                    client.connect(peer);
                    clients.add(client);
                }
                log.info("connect server:{}",peer);
            }
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        for(TransportClient client:clients){
            client.close();
        }
        clients.clear();

    }
}
