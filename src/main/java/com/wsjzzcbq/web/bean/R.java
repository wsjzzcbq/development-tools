package com.wsjzzcbq.web.bean;

import lombok.Data;

@Data
public class R<T> {

    private Integer code;

    private String msg;

    private T data;

    public static<T> R<T> ok() {
        R<T> r = new R<>();
        r.setCode(1);
        return r;
    }

    public static<T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setCode(1);
        r.setData(data);
        return r;
    }
}
