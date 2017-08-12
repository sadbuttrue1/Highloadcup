package me.shubbush.highloadcup.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import me.shubbush.highloadcup.dao.*;

/**
 * @author shubanev.a
 */
@AllArgsConstructor
public class StorageLoaderService {

    private UserStorage userStorage;
    private UserVisitStorage userVisitStorage;
    private LocationStorage locationStorage;
    private LocationVisitStorage locationVisitStorage;
    private VisitStorage visitStorage;
    private FileReaderService fileReaderService;
    private ObjectMapper objectMapper;

    public void loadStorages() {

    }

}
