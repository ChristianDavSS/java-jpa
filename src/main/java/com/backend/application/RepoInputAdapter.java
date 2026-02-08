package com.backend.application;

import com.backend.domain.entity.Student;
import com.backend.domain.ports.Repository;

import java.util.List;

/**
 * RepoInputAdapter is an adapter that executes the repository methods defined by an infrastructure
 * As I don't use different logics between tables, it is global (using generics) for now.
 * */
public class RepoInputAdapter<T, IDT> {
    private final Repository<T, IDT> repository;
    public RepoInputAdapter(Repository<T, IDT> repository) {
        this.repository = repository;
    }

    public T save(T instance) {
        return this.repository.save(instance);
    }

    public T getById(IDT id) {
        return this.repository.getById(id);
    }

    public void update(T instance) {
        this.repository.update(instance);
    }

    public void deleteById(IDT id) {
        this.repository.deleteById(id);
    }

    public List<T> getAll() {
        return this.repository.getAll();
    }

    public Boolean existsById(IDT id) {
        return this.repository.existsById(id);
    }
}
