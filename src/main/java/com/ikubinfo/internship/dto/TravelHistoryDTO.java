package com.ikubinfo.internship.dto;

import java.time.LocalDateTime;

public class TravelHistoryDTO {


    private Long id;


    private TravelCardDTO travelCard;

    private RouteDTO route;

    private LocalDateTime checkedCardTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TravelCardDTO getTravelCard() {
        return travelCard;
    }

    public void setTravelCard(TravelCardDTO travelCard) {
        this.travelCard = travelCard;
    }

    public RouteDTO getRoute() {
        return route;
    }

    public void setRoute(RouteDTO route) {
        this.route = route;
    }

    public LocalDateTime getCheckedCardTime() {
        return checkedCardTime;
    }

    public void setCheckedCardTime(LocalDateTime checkedCardTime) {
        this.checkedCardTime = checkedCardTime;
    }
}
