package cn.edu.nyist.Entity;

import java.sql.Time;

/**
 * Created by Leafage on 2018/5/10 18:09.
 * DESCRIPTION : 考勤数据
 */

public class AttenceData {
    private String id;
    private String classNum;
    private String dorNum;
    private String name;
    private String sex;
    private String age;
    private String status;
    private String phone;
    private String major;
    private String department;
    private String school;
    private String attenceStatus;
    private Time time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getDorNum() {
        return dorNum;
    }

    public void setDorNum(String dorNum) {
        this.dorNum = dorNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAttenceStatus() {
        return attenceStatus;
    }

    public void setAttenceStatus(String attenceStatus) {
        this.attenceStatus = attenceStatus;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
