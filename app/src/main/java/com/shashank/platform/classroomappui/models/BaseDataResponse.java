package com.shashank.platform.classroomappui.models;

public class BaseDataResponse {

    boolean error;
    String msg = "";

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
