package cn.edu.nyist.Entity;

/**
 * Created by Leafage on 2018/5/10 18:02.
 * DESCRIPTION : 班级信息
 */

public class ClassData {
    private String id;
    private String school;
    private String department;
    private String major;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
