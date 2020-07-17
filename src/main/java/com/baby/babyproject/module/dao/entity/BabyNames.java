package com.baby.babyproject.module.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class BabyNames implements Serializable {
    private String id;

    private String babyName;

    private Date commitTime;

    private String uesrId;

    private String babyNameExplain;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName == null ? null : babyName.trim();
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public String getUesrId() {
        return uesrId;
    }

    public void setUesrId(String uesrId) {
        this.uesrId = uesrId == null ? null : uesrId.trim();
    }

    public String getBabyNameExplain() {
        return babyNameExplain;
    }

    public void setBabyNameExplain(String babyNameExplain) {
        this.babyNameExplain = babyNameExplain == null ? null : babyNameExplain.trim();
    }
}