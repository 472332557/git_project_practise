package com.liangzc.example.exception;


public class OwenException extends RuntimeException {


    private Integer code;

    public OwenException() {
    }

    public OwenException(String message) {
        super(message);
    }

    public OwenException(Integer code, String message) {
        super(message);
        this.code = code;
    }


    public Integer getCode() {
        return code;
    }
}
