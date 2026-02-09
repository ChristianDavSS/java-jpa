package com.backend.application;

import com.backend.domain.entity.Student;
import com.backend.domain.ports.Repository;

import java.util.List;

public class StudentService {
    private final Repository<Student, Long> studentRepository;

    public StudentService(Repository<Student, Long> studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAll() {
        return this.studentRepository.getAll();
    }

    public Student save(Student student) {
        return this.studentRepository.save(student);
    }

    public void update(Student student) {
        this.studentRepository.update(student);
    }

    public Student getById(Long id) {
        return this.studentRepository.getById(id);
    }

    public void deleteById(Long id) {
        this.studentRepository.deleteById(id);
    }

    public Boolean existsById(Long id) {
        return this.studentRepository.existsById(id);
    }
}
