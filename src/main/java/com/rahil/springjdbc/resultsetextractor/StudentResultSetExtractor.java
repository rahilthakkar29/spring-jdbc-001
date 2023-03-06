package com.rahil.springjdbc.resultsetextractor;

import com.rahil.springjdbc.domain.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentResultSetExtractor implements ResultSetExtractor<List<Student>> {

    @Override
    public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Student> studentList = new ArrayList<>();
        while (rs.next()) {
            Student student = new Student();
            student.setRollNo(rs.getInt("ROLL_NO"));
            student.setName(rs.getString("STUDENT_NAME"));
            student.setAddress(rs.getString("STUDENT_ADDRESS"));
            studentList.add(student);
        }

        System.out.println("StudentResultSetExtractor --> extractData() called!!!!!!");

        return studentList;
    }

}
