package cn.chairc.blog.model;


//返回前端管理页面的信息结果集
public class DataResultSet {

    private String code;            //返回码
    private String msg;             //返回信息
    private Object data = null;     //返回数据，默认设为null，需要返回数据时，使用setData()方法
    private int page_num;           //  当前页
    private int page_total;         //  总页数
    private int page_count;         //  总条数

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

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
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
