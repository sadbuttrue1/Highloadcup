package me.shubbush.highloadcup;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.shubbush.highloadcup.dao.*;
import me.shubbush.highloadcup.service.StorageLoaderService;

/**
 * @author shubanev.a
 */
public class Application {

    public static final ObjectMapper objectMapper = configObjectMapper();
    public static final UserStorage userStorage = UserStorage.getInstance();
    public static final UserVisitStorage userVisitStorage = UserVisitStorage.getInstance();
    public static final LocationStorage locationStorage = LocationStorage.getInstance();
    public static final LocationVisitStorage locationVisitStorage = LocationVisitStorage.getInstance();
    public static final VisitStorage visitStorage = VisitStorage.getInstance();


    public static void main(String[] args) {
        String dataLocation = getDataLocation(args);
        StorageLoaderService loaderService = new StorageLoaderService(userStorage, userVisitStorage, locationStorage,
                locationVisitStorage, visitStorage, objectMapper, dataLocation);
    }

    private static String getDataLocation(String[] args) {
        if (args.length > 0)
            return args[0];
        return null;
    }

    private static ObjectMapper configObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        return objectMapper;
    }
}
