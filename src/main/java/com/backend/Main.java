package com.backend;

import com.backend.application.StudentInputAdapter;
import com.backend.domain.entity.Student;
import com.backend.infrastructure.InMemStorage;

public class Main {
    public static void main(String[] args) {
        StudentInputAdapter adapter = new StudentInputAdapter(new InMemStorage());
        adapter.save(
            Student
                .builder()
                .firstName("Christian")
                .surname("Sánchez")
                .id(1L)
                .age(20)
                .build()
        );

        System.out.println(adapter.getById(0L));
    }
}