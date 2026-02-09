package com.backend.infrastructure.servlet;

import com.backend.application.StudentService;
import com.backend.domain.entity.Student;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * StudentServlet is my own implementation of a HttpServlet to manage requests
 * related with the 'Student' entity via HTTP requests.
 * */
public class StudentServlet extends HttpServlet {
    private final StudentService studentService;
    private final Gson gson;

    public  StudentServlet(StudentService studentService) {
        this.studentService = studentService;
        this.gson = new Gson();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get the data from the body of the request
        BufferedReader reader = req.getReader();

        // parse the body into a java native object
        Student student = this.gson.fromJson(reader, Student.class);

        student = this.studentService.save(student);

        resp.getWriter().write(this.gson.toJson(student));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch (path) {
            case "/student":
                resp.getWriter().write(this.studentService.getAll().toString());
                break;
            default:
                resp.sendError(400, "Invalid endpoint... D:");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        this.studentService.deleteById(id);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        Student s = this.gson.fromJson(reader, Student.class);
        this.studentService.update(s);
    }
}
