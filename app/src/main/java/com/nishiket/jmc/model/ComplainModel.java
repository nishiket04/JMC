package com.nishiket.jmc.model;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

public class ComplainModel {
    private String complainNo;
    private String dateOfComplain;
    private String wardNo;
    private String areaName;
    private String branch;
    private String subject;
    private String details;
    private String status;
    private String expectedDate;
    private long workDone;
//    private String photo;

    public ComplainModel(String complainNo, String dateOfComplain, String wardNo, String areaName, String branch, String subject, String details, String status, String expectedDate, long workDone) {
        this.complainNo = complainNo;
        this.dateOfComplain = dateOfComplain;
        this.wardNo = wardNo;
        this.areaName = areaName;
        this.branch = branch;
        this.subject = subject;
        this.details = details;
        this.status = status;
        this.expectedDate = expectedDate;
        this.workDone = workDone;
    }


    public String getComplainNo() {
        return complainNo;
    }

    public void setComplainNo(String complainNo) {
        this.complainNo = complainNo;
    }

    public String getDateOfComplain() {
        return dateOfComplain;
    }

    public void setDateOfComplain(String dateOfComplain) {
        this.dateOfComplain = dateOfComplain;
    }

    public String getWardNo() {
        return wardNo;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public long getWorkDone() {
        return workDone;
    }

    public void setWorkDone(int workDone) {
        this.workDone = workDone;
    }

}
