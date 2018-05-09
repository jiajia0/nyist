package cn.edu.nyist.Entity;

/**
 * Created by Leafage on 2018/5/9 8:45.
 * DESCRIPTION :
 */

public class Student {

    private StudentData data;
    private Integer status;
    private String msg;

    public StudentData getData() {
        return data;
    }

    public void setData(StudentData data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
