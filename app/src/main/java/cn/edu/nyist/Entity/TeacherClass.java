package cn.edu.nyist.Entity;

import java.util.List;

/**
 * Created by Leafage on 2018/5/10 18:02.
 * DESCRIPTION : 教师所管理的班级信息
 */

public class TeacherClass {
    private Integer status;
    private String msg;
    private List<ClassData> data;

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

    public List<ClassData> getData() {
        return data;
    }

    public void setData(List<ClassData> data) {
        this.data = data;
    }
}
