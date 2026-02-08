package com.backend;

import com.backend.application.RepoInputAdapter;
import com.backend.domain.entity.Student;
import com.backend.infrastructure.StudentManager;

public class Main {
    public static void main(String[] args) {
        RepoInputAdapter<Student, Long> repo = new RepoInputAdapter<>(new StudentManager());
        System.out.println(repo.existsById(1L));
    }
}