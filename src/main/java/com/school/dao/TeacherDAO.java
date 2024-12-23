package com.school.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.school.model.Teacher;

public class TeacherDAO {
    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/school_db", "root", "");
    }

    public List<Teacher> getAllTeachers() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM teachers";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setName(rs.getString("name"));
                teacher.setEmail(rs.getString("email"));
                teachers.add(teacher);
            }
        }
        return teachers;
    }

    public void addTeacher(Teacher teacher) throws SQLException {
        String query = "INSERT INTO teachers (name, email) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, teacher.getName());
            pstmt.setString(2, teacher.getEmail());
            pstmt.executeUpdate();
        }
    }
}