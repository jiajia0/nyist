package cn.edu.nyist.Entity;

import java.util.List;

/**
 * Created by Leafage on 2018/5/10 18:09.
 * DESCRIPTION : 考勤记录
 */

public class AttenceRecord {
    private Integer status;
    private String msg;
    private List<AttenceData> data;

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

    public List<AttenceData> getData() {
        return data;
    }

    public void setData(List<AttenceData> data) {
        this.data = data;
    }
}
