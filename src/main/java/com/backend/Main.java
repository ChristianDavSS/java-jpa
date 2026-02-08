package com.backend;

import com.backend.application.RepoInputAdapter;
import com.backend.domain.entity.Student;
import com.backend.domain.entity.Subject;
import com.backend.domain.entity.Takes;
import com.backend.domain.entity.TakesPK;
import com.backend.infrastructure.StudentManager;
import com.backend.infrastructure.SubjectManager;
import com.backend.infrastructure.TakesManager;

public class Main {
    public static void main(String[] args) {
        // define the managers
        RepoInputAdapter<Student, Long> studentManager = new RepoInputAdapter<>(new StudentManager());
        RepoInputAdapter<Subject, Long> subjectManager = new RepoInputAdapter<>(new SubjectManager());
        RepoInputAdapter<Takes, TakesPK> takesManager = new RepoInputAdapter<>(new TakesManager());

        System.out.println(takesManager.getById(TakesPK.builder().studentId(1L).subjectId(1L).build()));
    }
}