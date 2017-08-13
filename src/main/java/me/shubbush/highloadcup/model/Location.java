package me.shubbush.highloadcup.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;
import me.shubbush.highloadcup.exception.EntityValidationException;


/**
 * @author shubanev.a
 */

@Data
@Builder
@JsonRootName("locations")
public class Location implements Model<Integer> {

    private Integer id;
    private String place;
    private String country;
    private String city;
    private int distance;

    @Override
    public void validate() throws EntityValidationException {
        if (country.length() > 50 || city.length() > 50)
            throw new EntityValidationException();
    }
}
