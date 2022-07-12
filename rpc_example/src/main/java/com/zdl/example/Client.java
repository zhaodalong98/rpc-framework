package com.zdl.example;

import com.zdl.rpc.client.RpcClient;

public class Client {
    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        CalcService proxy = rpcClient.getProxy(CalcService.class);
        int add = proxy.add(1, 2);
        int minus = proxy.minus(2, 1);
        System.out.println("a + b =" + add + ", a - b = " + minus);

    }
}
