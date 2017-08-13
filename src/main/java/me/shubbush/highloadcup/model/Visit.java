package me.shubbush.highloadcup.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;
import me.shubbush.highloadcup.exception.EntityValidationException;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author shubanev.a
 */
@Data
@Builder
@JsonRootName("visits")
public class Visit implements Model<Integer> {

    private Integer id;
    private int location;
    private int user;
    @JsonProperty("visited_at")
    private Date visitedAt;
    private int mark;

    @Override
    public void validate() throws EntityValidationException {

    }
}
