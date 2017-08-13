package me.shubbush.highloadcup.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.shubbush.highloadcup.Application;
import me.shubbush.highloadcup.dao.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author shubanev.a
 */
public class StorageLoaderServiceTest {

    public static final ObjectMapper objectMapper = Application.objectMapper;
    public static final UserStorage userStorage = UserStorage.getInstance();
    public static final UserVisitStorage userVisitStorage = UserVisitStorage.getInstance();
    public static final LocationStorage locationStorage = LocationStorage.getInstance();
    public static final LocationVisitStorage locationVisitStorage = LocationVisitStorage.getInstance();
    public static final VisitStorage visitStorage = VisitStorage.getInstance();

    @Test
    public void extractZip() throws Exception {

        StorageLoaderService loaderService = new StorageLoaderService(userStorage, userVisitStorage, locationStorage,
                locationVisitStorage, visitStorage, objectMapper, "data/test.zip");

        loaderService.loadStorages();

        assertEquals(1, userStorage.findAll().size());
        assertEquals(1, userVisitStorage.findAll().size());
        assertEquals(1, locationStorage.findAll().size());
        assertEquals(1, locationVisitStorage.findAll().size());
        assertEquals(1, visitStorage.findAll().size());
    }

}