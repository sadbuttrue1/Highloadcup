package me.shubbush.highloadcup.dao;

import java.util.List;

/**
 * Storage of userId map to List<visitId>
 * @author shubanev.a
 */
public class UserVisitStorage extends CrudStorage<Integer, List<Integer>> {
    private static UserVisitStorage instance;

    public static UserVisitStorage getInstance() {
        if (instance == null)
            instance = new UserVisitStorage();
        return instance;
    }

    private UserVisitStorage() {
    }
}
