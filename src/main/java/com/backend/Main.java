package com.backend;

import com.backend.domain.entity.Student;

public class Main {
    public static void main(String[] args) {
        Student s = Student
                .builder()
                .surname("juan")
                .build();

        System.out.println(s.getSurname());
    }
}