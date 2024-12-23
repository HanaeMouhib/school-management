package com.school.controller;

import java.io.IOException;
import java.util.List;

import com.school.dao.CourseDAO;
import com.school.model.Course;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CourseServlet extends HttpServlet {
    private final CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Course> courses = courseDAO.getAllCourses();
            request.setAttribute("courses", courses);
            RequestDispatcher dispatcher = request.getRequestDispatcher("courses.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Course course = new Course();
            course.setName(request.getParameter("name"));
            course.setDescription(request.getParameter("description"));
            courseDAO.addCourse(course);
            response.sendRedirect("courses");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
