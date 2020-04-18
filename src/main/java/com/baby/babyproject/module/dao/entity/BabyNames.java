package com.baby.babyproject.module.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class BabyNames implements Serializable {
    private Integer id;

    private String babyName;

    private String babyNameExplain;

    private Date commitTime;

    private Integer uesrId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName == null ? null : babyName.trim();
    }

    public String getBabyNameExplain() {
        return babyNameExplain;
    }

    public void setBabyNameExplain(String babyNameExplain) {
        this.babyNameExplain = babyNameExplain == null ? null : babyNameExplain.trim();
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public Integer getUesrId() {
        return uesrId;
    }

    public void setUesrId(Integer uesrId) {
        this.uesrId = uesrId;
    }
}