package com.example.smartmuseum.util.http;

/**
 * 网络请求结果接收
 */
public class HttpResult<T> {

    private int code;
    private String message;

    //模板类Data
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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
