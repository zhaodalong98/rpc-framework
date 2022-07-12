package com.zdl.example;

import com.zdl.rpc.server.RpcServer;

public class Server {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(CalcService.class, new CalcServiceImpl());
        try {
            rpcServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
