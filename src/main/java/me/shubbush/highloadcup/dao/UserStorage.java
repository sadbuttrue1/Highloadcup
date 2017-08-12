package me.shubbush.highloadcup.dao;

import me.shubbush.highloadcup.model.User;

/**
 * @author shubanev.a
 */
public class UserStorage extends CrudStorage<Integer, User> {
    private static UserStorage storage;

    public static UserStorage getInstance() {
        if (storage == null)
            storage = new UserStorage();
        return storage;
    }

    private UserStorage() {
    }
}
