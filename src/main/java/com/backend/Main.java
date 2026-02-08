package com.backend;

import com.backend.application.RepoInputAdapter;
import com.backend.domain.entity.*;
import com.backend.infrastructure.StudentManager;
import com.backend.infrastructure.SubjectManager;
import com.backend.infrastructure.TakesManager;

public class Main {
    public static void main(String[] args) {
        // define the adapters where we injected our repository implementation
        // depending on the operation entity you want to manage you use one or another
        RepoInputAdapter<Student, Long> studentManager = new RepoInputAdapter<>(new StudentManager());
        RepoInputAdapter<Subject, Long> subjectManager = new RepoInputAdapter<>(new SubjectManager());
        RepoInputAdapter<Takes, TakesPK> takesManager = new RepoInputAdapter<>(new TakesManager());

        System.out.println(studentManager.getAll());
    }
}