package com.backend.infrastructure.servlet;

import com.backend.application.SubjectService;
import com.backend.domain.Helper;
import com.backend.domain.entity.Subject;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class SubjectServlet extends HttpServlet {
    private final SubjectService subjectService;
    private final Gson gson;

    public SubjectServlet(SubjectService subjectService) {
        this.subjectService = subjectService;
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = Objects.requireNonNullElse(req.getParameter("id"), "");
        String path = req.getRequestURI() + idStr;


        if (Helper.matchesRegex("^/subject$", path)) {
            resp.getWriter().write(this.gson.toJson(this.subjectService.getAll()));
            return;
        }

        Long id = null;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            resp.getWriter().write("Error parsing the ID into Long type");
            return;
        }

        if (Helper.matchesRegex("^/subject[0-9]{1,10}$", path)) {
            resp.getWriter().write(this.gson.toJson(this.subjectService.getById(id)));
            return;
        }

        if (Helper.matchesRegex("^/subject/exist[0-9]{1,10}$", path)) {
            resp.getWriter().write(this.subjectService.existsById(id).toString());
            return;
        }

        resp.sendError(404, "URL Not Found...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        Subject subject = this.gson.fromJson(reader, Subject.class);

        subject = this.subjectService.save(subject);

        resp.getWriter().write(this.gson.toJson(subject));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get and parse the request body
        BufferedReader reader = req.getReader();
        Subject subject = this.gson.fromJson(reader, Subject.class);

        // execute the update clause
        this.subjectService.update(subject);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get the ID parameter from the request
        String idStr = req.getParameter("id");
        if (idStr.isEmpty()) {
            resp.sendError(403, "Error... not id to delete");
            return;
        }

        // parse the id from string to long (managing the error)
        Long id = null;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            resp.sendError(403, "Error parsing the ID into Long type");
            return;
        }

        // execute the delete clause
        this.subjectService.deleteById(id);
    }
}
