package com.backend.infrastructure.servlet;

import com.backend.application.TakesService;
import com.backend.domain.Helper;
import com.backend.domain.entity.Takes;
import com.backend.domain.entity.TakesPK;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class TakesServlet extends HttpServlet {
    private final TakesService takesService;
    private final Gson gson;

    public TakesServlet(TakesService takesService) {
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

        if (Helper.matchesRegex("^/takes/exists[0-9+]$", path)) {
            resp.getWriter().write(this.takesService.existsById(id).toString());
            return;
        }

        resp.sendError(404, "URL not found...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // fetch the body from the request
        BufferedReader reader = req.getReader();
        Takes takes = this.gson.fromJson(reader, Takes.class);

        // save the user in the database
        takes = this.takesService.save(takes);

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
