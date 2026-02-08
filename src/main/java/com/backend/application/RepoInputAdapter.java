package com.backend.application;

import com.backend.domain.ports.Repository;

import java.util.List;

/**
 * RepoInputAdapter is an adapter that executes the repository methods defined by an infrastructure
 * As I don't use different logics between tables, it is global (using generics) for now.
 * */
public class RepoInputAdapter<X, T> {
    private final Repository<X, T> repository;
    public RepoInputAdapter(Repository<X, T> repository) {
        this.repository = repository;
    }

    public X save(X instance) {
        return this.repository.save(instance);
    }

    public X getById(T id) {
        return this.repository.getById(id);
    }

    public void update(X instance) {
        this.repository.update(instance);
    }

    public void deleteById(T id) {
        this.repository.deleteById(id);
    }

    public List<X> getAll() {
        return this.repository.getAll();
    }

    public Boolean existsById(T id) {
        return this.repository.existsById(id);
    }
}
