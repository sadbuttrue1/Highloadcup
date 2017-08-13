package me.shubbush.highloadcup.model;

import me.shubbush.highloadcup.exception.EntityValidationException;

/**
 * @author shubanev.a
 */
public interface Model<ID> {

    ID getId();

    void validate() throws EntityValidationException;
}
