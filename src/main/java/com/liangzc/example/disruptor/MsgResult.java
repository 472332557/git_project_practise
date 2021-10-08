package com.liangzc.example.disruptor;

import lombok.Data;

@Data
public class MsgResult {

    private String name;

    private String value;

    public MsgResult() {
    }

    public MsgResult(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
