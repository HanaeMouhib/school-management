package com.school.controller;

import java.io.IOException;
import java.util.List;

import com.school.dao.TeacherDAO;
import com.school.model.Teacher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TeacherServlet extends HttpServlet {
    private final TeacherDAO teacherDAO = new TeacherDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Teacher> teachers = teacherDAO.getAllTeachers();
            request.setAttribute("teachers", teachers);
            RequestDispatcher dispatcher = request.getRequestDispatcher("teachers.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Teacher teacher = new Teacher();
            teacher.setName(request.getParameter("name"));
            teacher.setEmail(request.getParameter("email"));
            teacherDAO.addTeacher(teacher);
            response.sendRedirect("teachers");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
