package cn.edu.nyist.Entity;

import java.util.List;

/**
 * Created by Leafage on 2018/5/18 14:21.
 * DESCRIPTION : 保存某个班级下的所有学生信息
 */

public class StudentForClass {
    private Integer status;
    private String msg;
    private List<StudentData> data;

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

    public List<StudentData> getData() {
        return data;
    }

    public void setData(List<StudentData> data) {
        this.data = data;
    }
}
