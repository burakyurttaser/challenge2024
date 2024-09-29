package com.lydiasystems.challenge.fault;


public class AppException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private Integer code;

    public AppException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}