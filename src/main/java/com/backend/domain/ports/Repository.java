package com.backend.domain.ports;

import java.util.List;

public interface Repository<X, T> {
    X getById(T id);
    List<X> getAll();
    X save(X instance);
    void update(X instance);
    void deleteById(T id);
    Boolean existsById(T id);
}
