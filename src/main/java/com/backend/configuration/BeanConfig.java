package com.backend.configuration;

import com.backend.application.*;
import com.backend.domain.entity.*;
import com.backend.infrastructure.*;
import com.backend.domain.ports.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Project beans configuration: managed by the spring container
 * This allows me to configure the beans manually and not depend on any XML-based configuration
 * */
@Configuration
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
    public TakesService takesService() {
        return new TakesService(takesRepository());
    }

    // takesRepository returns the class which implements a Repository interface
    @Bean
    public Repository<Takes, TakesPK> takesRepository() {
        return new TakesImpl();
    }
}
