package com.backend.infrastructure.servlet;

import com.backend.application.*;
import com.backend.domain.Helper;
import com.backend.domain.entity.*;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

public class TakesServlet extends HttpServlet {
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final TakesService takesService;
    private final Gson gson;

    public TakesServlet(StudentService studentService, SubjectService subjectService, TakesService takesService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.takesService = takesService;
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentIdStr = req.getParameter("student_id");
        String subjectIdStr = req.getParameter("subject_id");
        String path = req.getRequestURI() + studentIdStr + subjectIdStr;

        if (Helper.matchesRegex("^/takes$", path)) {
            resp.getWriter().write(this.gson.toJson(this.takesService.getAll()));
            return;
        }

        TakesPK id;
        try {
            Long studentId = Long.parseLong(studentIdStr);
            Long subjectId = Long.parseLong(subjectIdStr);
            id = TakesPK
                .builder()
                .studentId(studentId)
                .subjectId(subjectId)
                .build();

        } catch (NumberFormatException e) {
            resp.sendError(403, "Error...");
            return;
        }

        if (Helper.matchesRegex("^/takes[0-9]+$", path)) {
            resp.getWriter().write(this.gson.toJson(this.takesService.getById(id)));
            return;
        }

        if (Helper.matchesRegex("^/takes/exists[0-9]+$", path)) {
            resp.getWriter().write(this.takesService.existsById(id).toString());
            return;
        }

        resp.sendError(404, "URL not found...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // fetch the body from the request
        BufferedReader reader = req.getReader();
        // get the primary keys from the body
        TakesPK takesPK = this.gson.fromJson(reader, TakesPK.class);

        Student student = this.studentService.getById(takesPK.getStudentId());
        Subject subject = this.subjectService.getById(takesPK.getSubjectId());

        if (student == null || subject == null) {
            resp.sendError(403, "Not found...");
            return;
        }

        Takes takes = Takes
            .builder()
            .takesPK(takesPK)
            .student(student)
            .subject(subject)
            .build();

        // save the user in the database
        takes = this.takesService.save(takes);
        if (takes == null) {
            resp.sendError(403, "Already exists");
            return;
        }

        // return a response as json
        resp.getWriter().write(this.gson.toJson(takes, Takes.class));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // fetch the body from the request
        BufferedReader reader = req.getReader();
        Takes takes = this.gson.fromJson(reader, Takes.class);

        this.takesService.update(takes);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentIdStr = req.getParameter("student_id");
        String subjectIdStr = req.getParameter("subject_id");

        if (studentIdStr.isEmpty() || subjectIdStr.isEmpty()) {
            resp.sendError(403, "Error...");
            return;
        }

        TakesPK id;
        try {
            Long studentId = Long.parseLong(studentIdStr);
            Long subjectId = Long.parseLong(subjectIdStr);
            id = TakesPK
                .builder()
                .studentId(studentId)
                .subjectId(subjectId)
                .build();

        } catch (NumberFormatException e) {
            resp.sendError(403, "Error... (test)");
            return;
        }

        this.takesService.deleteById(id);
    }
}
