package com.school.controller;

import java.io.IOException;
import java.util.List;

import com.school.dao.StudentDAO;
import com.school.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Student> students = studentDAO.getAllStudents();
            request.setAttribute("students", students);
            RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Student student = new Student();
            student.setName(request.getParameter("name"));
            student.setEmail(request.getParameter("email"));
            student.setAge(Integer.parseInt(request.getParameter("age")));
            studentDAO.addStudent(student);
            response.sendRedirect("students");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}