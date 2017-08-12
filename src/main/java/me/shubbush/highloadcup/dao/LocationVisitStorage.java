package me.shubbush.highloadcup.dao;

import java.util.List;

/**
 * Storage of locationId map to List<visitId>
 * @author shubanev.a
 */
public class LocationVisitStorage extends CrudStorage<Integer, List<Integer>> {
    private static LocationVisitStorage instance;

    public static LocationVisitStorage getInstance() {
        if (instance == null)
            instance = new LocationVisitStorage();
        return instance;
    }

    private LocationVisitStorage() {
    }
}
