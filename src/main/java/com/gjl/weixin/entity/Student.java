package com.gjl.weixin.entity;

import java.util.List;

public class Student {
    private Long id;

    private String cardId;

    private String department;

    private String education;

    private String employeeId;

    private String eName;

    private String eSex;

    private String phone;

    private Long pxclassId;

    private Pxclass pxclass;

    public Pxclass getPxclass() {
        return pxclass;
    }

    public void setPxclass(Pxclass pxclass) {
        this.pxclass = pxclass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName == null ? null : eName.trim();
    }

    public String geteSex() {
        return eSex;
    }

    public void seteSex(String eSex) {
        this.eSex = eSex == null ? null : eSex.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Long getPxclassId() {
        return pxclassId;
    }

    public void setPxclassId(Long pxclassId) {
        this.pxclassId = pxclassId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", cardId='" + cardId + '\'' +
                ", department='" + department + '\'' +
                ", education='" + education + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", eName='" + eName + '\'' +
                ", eSex='" + eSex + '\'' +
                ", phone='" + phone + '\'' +
                ", pxclassId=" + pxclassId +
                '}';
    }
}