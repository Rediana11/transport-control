package com.ikubinfo.internship.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;


public class PersonDTO {

    private Long id;

    private Set<RoleDTO> roles;

    private TravelCardDTO travelCard;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 20, message
            = "First name must be between 2 and 20 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    private Date dateBirth;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    private int phoneNo;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 2, max = 20, message
            = "Username must be between 2 and 20 characters")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private String address;

    private Date created_on;

    private boolean isDeleted;

    private Date updatedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public TravelCardDTO getTravelCard() {
        return travelCard;
    }

    public void setTravelCard(TravelCardDTO travelCard) {
        this.travelCard = travelCard;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
