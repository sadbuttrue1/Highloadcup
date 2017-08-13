package me.shubbush.highloadcup.dao;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author shubanev.a
 */
public abstract class CrudStorage<ID, T> {

    private ConcurrentHashMap<ID, T> storage;

    public CrudStorage() {
        storage = new ConcurrentHashMap<>();
    }

    public T find(ID id) {
        return storage.get(id);
    }

    public Collection<T> findAll() {
        return storage.values();
    }

    public List<T> findByIdIn(List<ID> ids) {
        return ids.stream()
                .map(id -> storage.get(id))
                .collect(Collectors.toList());
    }

    public T save(ID id, T obj) {
        return storage.put(id, obj);
    }

    public void putIfAbsent(ID key, T value) {
        storage.putIfAbsent(key, value);
    }

    public void delete(ID id) {
        storage.remove(id);
    }

}
