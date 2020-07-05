package com.spapr.bookshow.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GeneralResponse<T> implements Serializable {

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
