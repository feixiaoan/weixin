package com.gjl.weixin.utils;

public class R<T> {
    public final static Integer CODE_SUCCESS = 0;
    public final static Integer CODE_FAIL = 1;
    public final static String MSG_SUCCESS = "操作成功";
    public final static String MSG_FAIL = "操作失败";

    // 响应业务状态 0 成功， 1失败
    private Integer code;
    // 响应消息
    private String msg;
    // 响应中的数据
    private T data;

    public R() {
    }

    public R(T data) {
        this.code = 0;
        this.msg = MSG_SUCCESS;
        this.data = data;
    }

    public R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> R<T> ok(T data) {
        return new R<T>(data);
    }

    public static <T> R<T> ok() {
        return new R<T>(CODE_SUCCESS, MSG_SUCCESS, null);
    }

    public static <T> R<T> error() {
        return new R<T>(CODE_FAIL, MSG_FAIL, null);
    }

    public static <T> R<T> error(String msg) {
        return new R<T>(CODE_FAIL, msg, null);
    }

    public static <T> R<T> build(Integer code, String msg) {
        return new R<T>(code, msg, null);
    }

    public static <T> R<T> build(Integer code, String msg, T data) {
        return new R<T>(code, msg, data);
    }

    public static <T> R<T> getResult(T t) {
        R<T> result = new R<>(t);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}