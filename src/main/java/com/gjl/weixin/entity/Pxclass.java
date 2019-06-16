package com.gjl.weixin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Pxclass {
    private Long id;

    private String category;

    private String className;

    private String classPerson;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String endTime;

    private String number;

    private String professionPerson;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String startTime;

    private String teachePerson;

    private String day;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getClassPerson() {
        return classPerson;
    }

    public void setClassPerson(String classPerson) {
        this.classPerson = classPerson == null ? null : classPerson.trim();
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getProfessionPerson() {
        return professionPerson;
    }

    public void setProfessionPerson(String professionPerson) {
        this.professionPerson = professionPerson == null ? null : professionPerson.trim();
    }



    public String getTeachePerson() {
        return teachePerson;
    }

    public void setTeachePerson(String teachePerson) {
        this.teachePerson = teachePerson == null ? null : teachePerson.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }
}