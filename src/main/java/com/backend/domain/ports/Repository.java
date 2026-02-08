package com.backend.domain.ports;

import java.util.List;

public interface Repository<T, IDT> {
    T getById(IDT id);
    List<T> getAll();
    T save(T instance);
    void update(T instance);
    void deleteById(IDT id);
    Boolean existsById(IDT id);
}
