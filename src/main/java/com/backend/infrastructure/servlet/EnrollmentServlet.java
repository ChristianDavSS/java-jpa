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

public class EnrollmentServlet extends HttpServlet {
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final EnrollmentService enrollmentService;
    private final Gson gson;

    public EnrollmentServlet(StudentService studentService, SubjectService subjectService, EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.enrollmentService = enrollmentService;
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentIdStr = req.getParameter("student_id");
        String subjectIdStr = req.getParameter("subject_id");
        String path = req.getRequestURI() + studentIdStr + subjectIdStr;

        if (Helper.matchesRegex("^/takes$", path)) {
            resp.getWriter().write(this.gson.toJson(this.enrollmentService.getAll()));
            return;
        }

        EnrollmentPK id;
        try {
            Long studentId = Long.parseLong(studentIdStr);
            Long subjectId = Long.parseLong(subjectIdStr);
            id = EnrollmentPK
                .builder()
                .studentId(studentId)
                .subjectId(subjectId)
                .build();

        } catch (NumberFormatException e) {
            resp.sendError(400, "Data type error...");
            return;
        }

        if (Helper.matchesRegex("^/takes[0-9]+$", path)) {
            resp.getWriter().write(this.gson.toJson(this.enrollmentService.getById(id)));
            return;
        }

        if (Helper.matchesRegex("^/takes/exists[0-9]+$", path)) {
            resp.getWriter().write(this.enrollmentService.existsById(id).toString());
            return;
        }

        resp.sendError(404, "URL not found...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // fetch the body from the request
        BufferedReader reader = req.getReader();
        // get the primary keys from the body
        EnrollmentPK enrollmentPK = this.gson.fromJson(reader, EnrollmentPK.class);

        Student student = this.studentService.getById(enrollmentPK.getStudentId());
        Subject subject = this.subjectService.getById(enrollmentPK.getSubjectId());

        if (student == null || subject == null) {
            resp.sendError(400, "Not found...");
            return;
        }

        Enrollment enrollment = Enrollment
            .builder()
            .takesPK(enrollmentPK)
            .student(student)
            .subject(subject)
            .build();

        // save the user in the database
        enrollment = this.enrollmentService.save(enrollment);
        if (enrollment == null) {
            resp.sendError(406, "Already exists");
            return;
        }

        // return a response as json
        resp.getWriter().write(this.gson.toJson(enrollment, Enrollment.class));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // fetch the body from the request
        BufferedReader reader = req.getReader();
        Enrollment enrollment = this.gson.fromJson(reader, Enrollment.class);

        this.enrollmentService.update(enrollment);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentIdStr = req.getParameter("student_id");
        String subjectIdStr = req.getParameter("subject_id");

        if (studentIdStr.isEmpty() || subjectIdStr.isEmpty()) {
            resp.sendError(400, "Not enough parameters given...");
            return;
        }

        EnrollmentPK id;
        try {
            Long studentId = Long.parseLong(studentIdStr);
            Long subjectId = Long.parseLong(subjectIdStr);
            id = EnrollmentPK
                .builder()
                .studentId(studentId)
                .subjectId(subjectId)
                .build();

        } catch (NumberFormatException e) {
            resp.sendError(400, "Data type error...");
            return;
        }

        this.enrollmentService.deleteById(id);
    }
}
