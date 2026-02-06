package com.backend.application;

import com.backend.domain.entity.Student;
import com.backend.domain.ports.StudentRepository;

/**
 * StudentInputAdapter is an adapter that executes the repository methods defined by an infrastructure
 * */
public class StudentInputAdapter {
    private final StudentRepository studentRepository;
    public StudentInputAdapter(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student getById(Long id) {
        return studentRepository.getById(id);
    }
}
