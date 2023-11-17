package com.example.interfaceextension.sample.domain;

/**
 * @author huangguang
 */
public class R {
    private String msg;
    private Object data;
    private Integer code;

    public R(String msg, Object data, Integer code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public static R ok(Object obj) {
        return new R("操作成功", obj, 200);
    }

    public static R ok() {
        return new R("操作成功", null, 200);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
