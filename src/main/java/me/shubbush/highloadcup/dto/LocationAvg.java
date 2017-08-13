package me.shubbush.highloadcup.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author shubanev.a
 */
@Data
public class LocationAvg {

    @JsonFormat
    private double avg = 0.0;

    public void setAvg(double avg) {
        BigDecimal bd = new BigDecimal(avg).setScale(5, RoundingMode.HALF_UP);
        this.avg = bd.doubleValue();
    }
}
