package com.backend.application;

import com.backend.domain.entity.Enrollment;
import com.backend.domain.entity.EnrollmentPK;
import com.backend.domain.ports.Repository;

import java.util.List;

public class EnrollmentService {
    private final Repository<Enrollment, EnrollmentPK> takesRepository;

    public EnrollmentService(Repository<Enrollment, EnrollmentPK> takesRepository) {
        this.takesRepository = takesRepository;
    }

    public List<Enrollment> getAll() {
        return this.takesRepository.getAll();
    }

    public Enrollment save(Enrollment enrollment) {
        return this.takesRepository.save(enrollment);
    }

    public void update(Enrollment enrollment) {
        this.takesRepository.update(enrollment);
    }

    public Enrollment getById(EnrollmentPK id) {
        return this.takesRepository.getById(id);
    }

    public void deleteById(EnrollmentPK id) {
        this.takesRepository.deleteById(id);
    }

    public Boolean existsById(EnrollmentPK id) {
        return this.takesRepository.existsById(id);
    }
}
