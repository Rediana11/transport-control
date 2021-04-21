package com.ikubinfo.internship.dto;

import java.time.LocalDateTime;

public class TicketDTO {

    private Long id;

    private String code;

    private RouteDTO route;

    private LocalDateTime ticketPurchaseTime;

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

    public RouteDTO getRoute() {
        return route;
    }

    public void setRoute(RouteDTO route) {
        this.route = route;
    }

    public LocalDateTime getTicketPurchaseTime() {
        return ticketPurchaseTime;
    }

    public void setTicketPurchaseTime(LocalDateTime ticketPurchaseTime) {
        this.ticketPurchaseTime = ticketPurchaseTime;
    }
}
