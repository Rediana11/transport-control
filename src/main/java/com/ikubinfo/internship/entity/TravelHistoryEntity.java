package com.ikubinfo.internship.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "travel_history")
public class TravelHistoryEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_card_id", referencedColumnName = "id")
    private TravelCardEntity travelCard;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private RouteEntity route;

    @Column(name = "checkedCardTime")
    private LocalDateTime checkedCardTime;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public TravelCardEntity getTravelCard() {
        return travelCard;
    }

    public void setTravelCard(TravelCardEntity travelCard) {
        this.travelCard = travelCard;
    }

    public RouteEntity getRoute() {
        return route;
    }

    public void setRoute(RouteEntity route) {
        this.route = route;
    }

    public LocalDateTime getCheckedCardTime() {
        return checkedCardTime;
    }

    public void setCheckedCardTime(LocalDateTime checkedCardTime) {
        this.checkedCardTime = checkedCardTime;
    }
}
