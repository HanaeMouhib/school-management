package com.school.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.school.model.Teacher;

public class TeacherDAOTest {
    private TeacherDAO teacherDAO;

    @BeforeEach
    public void setUp() {
        teacherDAO = new TeacherDAO();
    }

    @Test
    public void testAddTeacher() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setName("Jane Smith");
        teacher.setEmail("jane.smith@example.com");

        teacherDAO.addTeacher(teacher);
        List<Teacher> teachers = teacherDAO.getAllTeachers();
        assertTrue(teachers.stream().anyMatch(t -> t.getName().equals("Jane Smith")));
    }

    @Test
    public void testGetAllTeachers() throws Exception {
        List<Teacher> teachers = teacherDAO.getAllTeachers();
        assertNotNull(teachers);
    }
}
