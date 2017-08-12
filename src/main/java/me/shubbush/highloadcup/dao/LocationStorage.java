package me.shubbush.highloadcup.dao;

import me.shubbush.highloadcup.model.Location;

/**
 * @author shubanev.a
 */
public class LocationStorage extends CrudStorage<Integer, Location> {
    private static LocationStorage instance;

    public static LocationStorage getInstance() {
        if (instance == null)
            instance = new LocationStorage();
        return instance;
    }

    private LocationStorage() {
    }
}
