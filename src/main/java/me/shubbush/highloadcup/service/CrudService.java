package me.shubbush.highloadcup.service;

import me.shubbush.highloadcup.dao.CrudStorage;
import me.shubbush.highloadcup.exception.EntityNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author shubanev.a
 */
public abstract class CrudService<ID, T> {

    protected CrudStorage<ID, T> storage;

    public T find(ID id) throws EntityNotFoundException {
        return Optional.ofNullable(storage.find(id))
                .orElseThrow(EntityNotFoundException::new);
    }

    public Collection<T> findAll() {
        return storage.findAll();
    }

    public List<T> findByIdIn(List<ID> ids) {
        return ids.stream()
                .map(id -> storage.find(id))
                .collect(Collectors.toList());
    }

    public T save(ID id, T obj) {
        return storage.save(id, obj);
    }

    public T update(ID id, T obj) throws EntityNotFoundException {
        find(id);
        return storage.save(id, obj);
    }

    public void putIfAbsent(ID key, T value) {
        storage.putIfAbsent(key, value);
    }

    public void delete(ID id) throws EntityNotFoundException {
        find(id);
        storage.delete(id);
    }
}
