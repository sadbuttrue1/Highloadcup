package me.shubbush.highloadcup.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import me.shubbush.highloadcup.model.Location;
import me.shubbush.highloadcup.model.Visit;

import java.util.Date;

/**
 * @author shubanev.a
 */
@Data
@Builder
@AllArgsConstructor
public class LocationVisit {

    private int mark;
    @JsonProperty("visited_at")
    private Date visitedAt;
    private String place;
    @JsonIgnore
    private String country;

    public LocationVisit(Visit visit, Location location) {
        this.mark = visit.getMark();
        this.visitedAt = visit.getVisitedAt();

        this.place = location.getPlace();
        this.country = location.getCountry();
    }
}
