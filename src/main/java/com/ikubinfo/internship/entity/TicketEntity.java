package com.ikubinfo.internship.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private RouteEntity route;

    @Column(name = "ticket_purchase_time")
    private LocalDateTime ticketPurchaseTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RouteEntity getRoute() {
        return route;
    }

    public void setRoute(RouteEntity route) {
        this.route = route;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getTicketPurchaseTime() {
        return ticketPurchaseTime;
    }

    public void setTicketPurchaseTime(LocalDateTime ticketPurchaseTime) {
        this.ticketPurchaseTime = ticketPurchaseTime;
    }
}
