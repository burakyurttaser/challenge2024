package com.lydiasystems.challenge.fault.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class ErrorRestResponse {
    private Integer code;
    private String errorMessage;
    private Date date;

    public ErrorRestResponse(Integer code, String errorMessage, Date date) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.date = date;
    }

    public ErrorRestResponse(String errorMessage, Date date) {
        this.errorMessage = errorMessage;
        this.date = date;
    }
}