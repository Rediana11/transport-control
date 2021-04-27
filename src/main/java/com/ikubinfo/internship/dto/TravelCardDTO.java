package com.ikubinfo.internship.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class TravelCardDTO {

    private Long id;

    @NotBlank(message = "Code is mandatory")
    @Size(min = 2, max = 30, message
            = "Code must be between 2 and 30 characters")
    private String code;

    private PersonDTO person;

    private BigDecimal balance;

    private Set<RouteDTO> routes;

    private LocalDateTime activatedOfferDate;

    private TravelCardTypeDTO travelCardType;

    private boolean isLost;

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

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
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

    public Set<RouteDTO> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<RouteDTO> routes) {
        this.routes = routes;
    }
}
