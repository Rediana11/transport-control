package com.ikubinfo.internship.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "travel_card")
public class TravelCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Code is mandatory")
    private String code;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_card_type_id")
    private TravelCardTypeEntity travelCardType;

    @Column(name = "activated_offer_date")
    private LocalDateTime activatedOfferDate;

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
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

    public TravelCardTypeEntity getTravelCardType() {
        return travelCardType;
    }

    public void setTravelCardType(TravelCardTypeEntity travelCardType) {
        this.travelCardType = travelCardType;
    }

    public LocalDateTime getActivatedOfferDate() {
        return activatedOfferDate;
    }

    public void setActivatedOfferDate(LocalDateTime activatedOfferDate) {
        this.activatedOfferDate = activatedOfferDate;
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