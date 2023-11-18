package com.nishiket.jmc.model;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.PropertyName;

import java.util.Date;

public class ComplainModel {
    @DocumentId
    private String documentId;
    @PropertyName("complainNo")
    private String complainNo;
    @PropertyName("dateOfComplain")
    private String dateOfComplain;
    @PropertyName("wardNo")
    private String wardNo;
    @PropertyName("areaName")
    private String areaName;
    @PropertyName("branch")
    private String branch;
    @PropertyName("subject")
    private String subject;
    @PropertyName("details")
    private String details;
    @PropertyName("status")
    private String status;
    @PropertyName("expextedDate")
    private String expectedDate;
    @PropertyName("workDone")
    private String workDone;
//    private String photo;

    public ComplainModel(){

    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
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

    public String getWorkDone() {
        return workDone;
    }

    public void setWorkDone(String workDone) {
        this.workDone = workDone;
    }
}
