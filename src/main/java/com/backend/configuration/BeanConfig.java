package com.backend.configuration;

import com.backend.application.*;
import com.backend.domain.entity.*;
import com.backend.domain.ports.Repository;
import com.backend.infrastructure.implementations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Project beans configuration: managed by the spring container
 * This allows me to configure the beans manually and not depend on any XML-based configuration
 * */
@Configuration
@ComponentScan("com.backend")
public class BeanConfig {
    // studentService returns the bean which calls out the repository defined methods
    @Bean
    public StudentService studentService() {
        return new StudentService(studentRepository());
    }

    // studentRepository returns the class which implements a Repository interface
    @Bean
    public Repository<Student, Long> studentRepository() {
        return new StudentImpl();
    }

    // Subject beans
    @Bean
    public SubjectService subjectService() {
        return new SubjectService(subjectRepository());
    }

    // subjectRepository returns the class which implements a Repository interface
    @Bean
    public Repository<Subject, Long> subjectRepository() {
        return new SubjectImpl();
    }

    // Takes beans
    @Bean
    public EnrollmentService enrollmentService() {
        return new EnrollmentService(enrollmentRepository());
    }

    // takesRepository returns the class which implements a Repository interface
    @Bean
    public Repository<Enrollment, EnrollmentPK> enrollmentRepository() {
        return new EnrollmentImpl();
    }
}
