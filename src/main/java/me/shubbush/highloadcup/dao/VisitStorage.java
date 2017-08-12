package me.shubbush.highloadcup.dao;

import me.shubbush.highloadcup.model.Visit;

/**
 * @author shubanev.a
 */
public class VisitStorage extends CrudStorage<Integer, Visit> {
    private static VisitStorage instance;

    public static VisitStorage getInstance() {
        if (instance == null)
            instance = new VisitStorage();
        return instance;
    }

    private VisitStorage() {
    }
}
