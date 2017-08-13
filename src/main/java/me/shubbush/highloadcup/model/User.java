package me.shubbush.highloadcup.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import me.shubbush.highloadcup.exception.EntityValidationException;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author shubanev.a
 */
@Data
@Builder
@JsonRootName("users")
public class User implements Model<Integer> {

    private Integer id;
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String gender;
    @JsonProperty("birth_date")
    private Date birthDate;

    public int getAge() {
        LocalDate bd = birthDate.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        LocalDate now = LocalDate.now(ZoneOffset.UTC);

        return (int) ChronoUnit.YEARS.between(bd, now);
    }

    @Override
    public void validate() throws EntityValidationException {

    }
}
