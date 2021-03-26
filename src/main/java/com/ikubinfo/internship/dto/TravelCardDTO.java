package com.ikubinfo.internship.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class TravelCardDTO {

    private Long id;

    @NotBlank(message = "Code is mandatory")
    @Size(min = 2, max = 30, message
            = "Code must be between 2 and 30 characters")
    private String code;

    private PersonDTO person;

    private BigDecimal balance;

    private LocalDateTime activatedOfferDate;

    private TravelCardTypeDTO travelCardType;

    private Date createdOn;

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

    public TravelCardTypeDTO getTravelCardType() {
        return travelCardType;
    }

    public void setTravelCardType(TravelCardTypeDTO travelCardType) {
        this.travelCardType = travelCardType;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getActivatedOfferDate() {
        return activatedOfferDate;
    }

    public void setActivatedOfferDate(LocalDateTime activatedOfferDate) {
        this.activatedOfferDate = activatedOfferDate;
    }
}
