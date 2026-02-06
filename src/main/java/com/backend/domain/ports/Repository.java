package com.backend.domain.ports;

public interface Repository<T, IDT extends Number> {
    T getById(IDT id);
    T save(T instance);
    void update(T instance);
    void deleteById(IDT id);
    Boolean existsById(IDT id);
}
