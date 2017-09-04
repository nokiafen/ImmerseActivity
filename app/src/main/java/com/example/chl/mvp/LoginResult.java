package com.example.chl.mvp;

import java.io.Serializable;

/**
 * Created by chenhailin on 2017/9/4.
 */

public class LoginResult implements Serializable {
    int code;
    String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
