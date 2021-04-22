package com.ikubinfo.internship.repository;

import com.ikubinfo.internship.dto.StatisticData;
import com.ikubinfo.internship.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RouteRepository extends CommonRepository<RouteEntity>, JpaRepository<RouteEntity, Long> {


    @Query(value= "select * from\n" +
            "            \n" +
            "           \n" +
            "\t\t\t(select  concat(route.start_place,'-',route.destination) as route ,\n" +
            "\t\t\t date_trunc('day',travel_history.checked_card_time) as day ,\n" +
            "       count(route.id) as frequency\n" +
            "             from travel_history\n" +
            "             join route on travel_history.route_id = route.id\n" +
            "             WHERE  \n" +
            "            checked_card_time >= ? \n" +
            "            AND   checked_card_time<= ? \n" +
            "            GROUP BY  DATE_TRUNC('day',travel_history.checked_card_time),\n" +
            "             concat(route.start_place,'-',route.destination)\n" +
            "            ) travel_history order by frequency desc", nativeQuery = true)
    List<StatisticData> getMostFrequentedLines(LocalDateTime firstDate, LocalDateTime secondDate);




}
