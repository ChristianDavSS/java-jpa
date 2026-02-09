package com.backend.infrastructure.servlet;

import com.backend.application.StudentService;
import com.backend.domain.Helper;
import com.backend.domain.entity.Student;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        // Path treatment (path info, params, etc...)
        String idStr = Objects.requireNonNullElse(req.getParameter("id"), "");
        // join the final path
        String path = req.getRequestURI() + idStr;

        // get all method
        if (Helper.matchesRegex("^/student$", path)) {
            resp.getWriter().write(this.studentService.getAll().toString());
            return;
        }

        // get the ID from the parameters of the request
        Long id = Long.parseLong(idStr);
        // get a specific student
        if (Helper.matchesRegex("^/student[0-9]{1,10}$", path)) {
            resp.getWriter().write(this.gson.toJson(this.studentService.getById(id)));
            return;
        }

        // see if a student exists
        if (Helper.matchesRegex("^/student/exist[0-9]{1,10}$", path)) {
            resp.getWriter().write(this.studentService.existsById(id).toString());
            return;
        }
        // if none of the last conditions were true, we send an error in the http response
        resp.sendError(400, "Error");
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
