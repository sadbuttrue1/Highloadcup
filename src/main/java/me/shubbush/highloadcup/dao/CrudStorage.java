package me.shubbush.highloadcup.dao;

import me.shubbush.highloadcup.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
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

    public T find(ID id) throws EntityNotFoundException {
        return Optional.ofNullable(storage.get(id))
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<T> findByIdIn(List<ID> ids) {
        return ids.stream()
                .map(id -> storage.get(id))
                .collect(Collectors.toList());
    }

    public T save(ID id, T obj) {
        return storage.put(id, obj);
    }

    public void delete(ID id) throws EntityNotFoundException {
        Optional.ofNullable(storage.remove(id))
                .orElseThrow(EntityNotFoundException::new);
    }

}
