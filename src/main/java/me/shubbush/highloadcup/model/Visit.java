package me.shubbush.highloadcup.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author shubanev.a
 */
@Data
@Builder
public class Visit {

    private int id;
    private int location;
    private int user;
    @JsonProperty("visited_at")
    private LocalDate visitedAt;
    private int mark;

}
