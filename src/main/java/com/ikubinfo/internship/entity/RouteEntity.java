package com.ikubinfo.internship.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "route")
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "routes")
    Set<RouteTypeEntity> routeTypes= new HashSet<>();

    @Column
    @NotBlank(message = "Code is mandatory")
    private String code;

    @Column
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column
    private String description;

    @Column
    private String startPlace;

    @Column
    private String destination;

    @Column
    private Double price;

    @Column
    private String duration;

    @Column(name = "created_on")
    private Date created_on;


    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "is_Deleted")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Set<RouteTypeEntity> getRouteTypes() {
        return routeTypes;
    }

    public void setRouteTypes(Set<RouteTypeEntity> routeTypes) {
        this.routeTypes = routeTypes;
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