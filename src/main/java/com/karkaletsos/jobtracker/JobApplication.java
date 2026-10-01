package com.karkaletsos.jobtracker;

import java.time.LocalDate;

public class JobApplication {

    private int id;
    private String company;
    private String position;
    private LocalDate applicationDate;
    private String status;
    private String notes;

    public JobApplication(int id, String company, String position, LocalDate applicationDate, String status, String notes) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.applicationDate = applicationDate;
        this.status = status;
        this.notes = notes;

    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", applicationDate=" + applicationDate +
                ", status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}