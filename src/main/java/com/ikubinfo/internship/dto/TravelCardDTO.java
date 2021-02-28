package com.ikubinfo.internship.dto;

import java.util.Date;

public class TravelCardDTO {

    private Long id;

    private String code;

    private PersonDTO person;

    private String travelCardType;

    private Date created_on;

    private Date updatedOn;

    private boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public String getTravelCardType() {
        return travelCardType;
    }

    public void setTravelCardType(String travelCardType) {
        this.travelCardType = travelCardType;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
