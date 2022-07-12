package com.zdl.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 表示网络传输的一个端点
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Peer {

    private String host;
    private int port;
}
