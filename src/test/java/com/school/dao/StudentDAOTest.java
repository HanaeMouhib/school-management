package com.school.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.school.model.Student;

public class StudentDAOTest {
    private StudentDAO studentDAO;

    @BeforeEach
    public void setUp() {
        studentDAO = new StudentDAO();
    }

    @Test
    public void testAddStudent() throws Exception {
        Student student = new Student();
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
        student.setAge(20);

        studentDAO.addStudent(student);
        List<Student> students = studentDAO.getAllStudents();
        assertTrue(students.stream().anyMatch(s -> s.getName().equals("John Doe")));
    }

    @Test
    public void testGetAllStudents() throws Exception {
        List<Student> students = studentDAO.getAllStudents();
        assertNotNull(students);
    }
}
