package com.backend;

import com.backend.application.StudentService;
import com.backend.configuration.BeanConfig;
import com.backend.infrastructure.servlet.StudentServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    // set up the context to get beans from
    static ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        // set the execution port in the 8080
        tomcat.setPort(8080);
        // empty context
        Context ctx = tomcat.addContext("", null);

        // Add servlets into the server
        Tomcat.addServlet(ctx, "student", new StudentServlet(context.getBean(StudentService.class)));

        // Add the request mappings from the context
        ctx.addServletMappingDecoded("/student/*", "student");

        // create the HTTP connector to listen to our port
        tomcat.getConnector();

        // start the server
        try {
            tomcat.start();
            System.out.println("Running on port 8080");
            // keep the server running at the specified port
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}