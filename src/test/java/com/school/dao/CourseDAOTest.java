package com.school.dao;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.school.model.Course;

public class CourseDAOTest {
    private CourseDAO courseDAO;

    @BeforeEach
    public void setUp() {
        courseDAO = new CourseDAO();
    }

    @Test
    public void testAddCourse() throws Exception {
        Course course = new Course();
        course.setName("Mathematics");
        course.setDescription("Basic mathematics course");

        courseDAO.addCourse(course);
        List<Course> courses = courseDAO.getAllCourses();
        assertTrue(courses.stream().anyMatch(c -> c.getName().equals("Mathematics")));
    }

    @Test
    public void testGetAllCourses() throws Exception {
        List<Course> courses = courseDAO.getAllCourses();
        assertNotNull(courses);
    }
}
