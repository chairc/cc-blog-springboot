package com.cc.blog.model;


//返回前端的结果集
public class ResultSet {

    private String code;            //返回码
    private String msg;             //返回信息
    private Object data = null;     //返回数据，默认设为null，需要返回数据时，使用setData()方法

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public void success(String msg){
        this.code = "1";
        this.msg = msg;
    }

    public void fail(String msg){
        this.code = "0";
        this.msg = msg;
    }

    public void error() {
        this.code = "error";
        this.msg = "异常错误";
    }
}
