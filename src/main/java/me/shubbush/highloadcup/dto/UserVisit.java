package me.shubbush.highloadcup.dto;

import lombok.Data;
import me.shubbush.highloadcup.model.User;
import me.shubbush.highloadcup.model.Visit;

import java.util.Date;

/**
 * @author shubanev.a
 */
@Data
public class UserVisit {

    private Date visitedAt;
    private int age;
    private String gender;
    private int mark;

    public UserVisit(User user, Visit visit) {
        this.age = user.getAge();
        this.gender = user.getGender();

        this.visitedAt = visit.getVisitedAt();
        this.mark = visit.getMark();
    }
}
