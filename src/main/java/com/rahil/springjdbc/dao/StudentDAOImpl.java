package com.rahil.springjdbc.dao;

import com.rahil.springjdbc.api.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private JdbcTemplate jdbcTemplate;


    @Override
    public void insert(Student student) {

        String sql = "INSERT INTO STUDENT VALUES (?,?,?)";

        Object[] arg =  {student.getRollNo(), student.getName(), student.getAddress()};

        int numOfRecordInserted = jdbcTemplate.update(sql, arg);

        System.out.println("Number of record inserted successfully:: " + numOfRecordInserted);

    }

    @Override
    public void insert(List<Student> students) {wrw
        String sql = "INSERT INTO STUDENT VALUES (?,?,?)";

        List<Object[]> sqlArgs = new ArrayList<>();

        for (Student tempStudent : students) {
            Object[] studentData = {tempStudent.getRollNo(), tempStudent.getName(), tempStudent.getAddress()};
            sqlArgs.add(studentData);
        }

        int[] batchUpdate = jdbcTemplate.batchUpdate(sql, sqlArgs);

        System.out.println("Number of record inserted successfully:: " + batchUpdate);

        System.out.println("Batch update done!!");

    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
