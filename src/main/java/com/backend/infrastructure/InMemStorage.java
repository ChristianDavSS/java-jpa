package com.backend.infrastructure;

import com.backend.domain.entity.Student;
import com.backend.domain.ports.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemStorage implements StudentRepository {
    List<Student> memory = new ArrayList<>();

    @Override
    public Student getById(Long id) {
        return memory.get(id.intValue());
    }

    @Override
    public Student save(Student student) {
        memory.add(student);
        return student;
    }

    @Override
    public void update(Student student) {
        memory.set(student.getId().intValue(), student);
    }

    @Override
    public void deleteById(Long id) {
        memory.remove(id.intValue());
    }

    @Override
    public Boolean existsById(Long id) {
        return id >= memory.size();
    }
}
