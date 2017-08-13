package me.shubbush.highloadcup.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.AllArgsConstructor;
import me.shubbush.highloadcup.dao.*;
import me.shubbush.highloadcup.model.Location;
import me.shubbush.highloadcup.model.Model;
import me.shubbush.highloadcup.model.User;
import me.shubbush.highloadcup.model.Visit;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author shubanev.a
 */
@AllArgsConstructor
public class StorageLoaderService {

    private static final String DATA_LOCATION = "/tmp/data/data.zip";

    private UserStorage userStorage;
    private UserVisitStorage userVisitStorage;
    private LocationStorage locationStorage;
    private LocationVisitStorage locationVisitStorage;
    private VisitStorage visitStorage;
    private ObjectMapper objectMapper;
    private String dataLocation;

    public void loadStorages() {
        extractZip(dataLocation == null ? DATA_LOCATION : dataLocation);
    }

    public void extractZip(String location) {
        try {
            ZipFile zipFile = new ZipFile(location);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.getName().endsWith(".json") || entry.getName().contains("/"))
                    continue;
                String fileName = entry.getName().split("_")[0];
                CrudStorage storage = getStorageByFilename(fileName);
                InputStream stream = zipFile.getInputStream(entry);
                List<Model> values = deserializeModel(stream, fileName);
                values.forEach(m -> storage.save(m.getId(), m));
            }
            processVisits();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Model> deserializeModel(InputStream is, String fileName) throws IOException {
        ObjectReader objectReader = objectMapper.readerFor(objectMapper.getTypeFactory()
                .constructCollectionType(List.class, getClassByFilename(fileName)))
                .withRootName(fileName);
        return objectReader.readValue(is);
    }

    private void processVisits() {
        visitStorage.findAll()
                .forEach(v -> {
                    userVisitStorage.putIfAbsent(v.getUser(), new ArrayList<>());
                    locationVisitStorage.putIfAbsent(v.getLocation(), new ArrayList<>());
                    userVisitStorage.find(v.getUser()).add(v.getId());
                    locationVisitStorage.find(v.getLocation()).add(v.getId());
                });

    }

    private CrudStorage getStorageByFilename(String fileName) {
        if ("users".equals(fileName))
            return userStorage;
        if ("visits".equals(fileName))
            return visitStorage;
        if ("locations".equals(fileName))
            return locationStorage;

        throw new IllegalArgumentException();
    }

    private Class getClassByFilename(String fileName) {
        if ("users".equals(fileName))
            return User.class;
        if ("visits".equals(fileName))
            return Visit.class;
        if ("locations".equals(fileName))
            return Location.class;

        throw new IllegalArgumentException();
    }

}
