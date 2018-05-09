package cn.edu.nyist.Entity;

/**
 * Created by Leafage on 2018/5/9 20:56.
 * DESCRIPTION :
 */

public class Teacher {
    private Integer status;
    private String msg;
    private TeacherData data;

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

    public TeacherData getData() {
        return data;
    }

    public void setData(TeacherData data) {
        this.data = data;
    }
}
