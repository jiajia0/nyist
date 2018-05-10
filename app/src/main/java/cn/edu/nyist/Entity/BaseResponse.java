package cn.edu.nyist.Entity;

/**
 * Created by Leafage on 2018/5/10 17:03.
 * DESCRIPTION : 只包含status和msg
 */

public class BaseResponse {
    private Integer status;
    private String msg;

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
