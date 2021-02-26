package com.ikubinfo.internship.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "travel_card")
public class TravelCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity person;

    @Column(name = "travel_card_type")
    private String travelCardType;

    @Column(name = "created_on")
    private Date created_on;

    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "is_deleted")
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

    public PersonEntity getUser() {
        return person;
    }

    public void setUser(PersonEntity user) {
        this.person = user;
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