package me.shubbush.highloadcup.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author shubanev.a
 */
@Data
@Builder
public class User {

    private int id;
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String gender;
    @JsonProperty("birth_date")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Instant birthDate;
}
