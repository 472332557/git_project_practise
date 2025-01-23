package com.liangzc.example.netty.protocol;

public enum ReqTypeE {
    REQUEST((byte) 1),
    RESPONSE((byte) 2),
    PING((byte) 3),
    PONG((byte) 4);

    private byte type;

    ReqTypeE(byte type) {
        this.type = type;
    }

    public byte getType() {
        return this.type;
    }
}
