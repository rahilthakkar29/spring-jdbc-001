package com.rahil.springjdbc.rowmapper;

import com.rahil.springjdbc.domain.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setRollNo(rs.getInt("ROLL_NO"));
        student.setName(rs.getString("STUDENT_NAME"));
        student.setAddress(rs.getString("STUDENT_ADDRESS"));
        return student;
    }


}
