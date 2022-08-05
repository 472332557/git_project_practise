package com.liangzc.example.collection;

import lombok.Data;

@Data
public class BallSports {

    public BallSports(Integer ballId, String ballName, String ballType) {
        this.ballId = ballId;
        this.ballName = ballName;
        this.ballType = ballType;
    }

    private Integer ballId;

    private String ballName;

    private String ballType;
}
