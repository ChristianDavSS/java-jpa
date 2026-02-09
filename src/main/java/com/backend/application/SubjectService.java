package com.backend.application;

import com.backend.domain.entity.Subject;
import com.backend.domain.ports.Repository;

import java.util.List;

public class SubjectService {
    private final Repository<Subject, Long> subjectRepository;

    public SubjectService(Repository<Subject, Long> subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAll() {
        return this.subjectRepository.getAll();
    }

    public Subject save(Subject subject) {
        return this.subjectRepository.save(subject);
    }

    public void update(Subject subject) {
        this.subjectRepository.update(subject);
    }

    public Subject getById(Long id) {
        return this.subjectRepository.getById(id);
    }

    public void deleteById(Long id) {
        this.subjectRepository.deleteById(id);
    }

    public Boolean existsById(Long id) {
        return this.subjectRepository.existsById(id);
    }
}
