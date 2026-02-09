package com.backend;

import com.backend.configuration.BeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // set up the context to get beans from
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        System.out.println(context.getClass());
    }
}