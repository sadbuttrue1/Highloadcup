package me.shubbush.highloadcup.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author shubanev.a
 */

@Data
@Builder
public class Location {

    private int id;
    private String place;
    private String country;
    private String city;
    private String distance;

}
