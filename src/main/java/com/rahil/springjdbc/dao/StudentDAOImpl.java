package com.rahil.springjdbc.dao;

import com.rahil.springjdbc.domain.Student;
import com.rahil.springjdbc.resultsetextractor.StudentResultSetExtractor;
import com.rahil.springjdbc.rowmapper.StudentRowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("studentDao")
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
    public void insert(List<Student> students) {

        System.out.println("Inside the method");

        String sql = "INSERT INTO STUDENT VALUES (?,?,?)";

        List<Object[]> sqlArgs = new ArrayList<>();

        for (Student tempStudent : students) {
            Object[] studentData = {tempStudent.getRollNo(), tempStudent.getName(), tempStudent.getAddress()};
            sqlArgs.add(studentData);
        }


        jdbcTemplate.batchUpdate(sql, sqlArgs);


        System.out.println("Batch update done!!");

    }

    @Override
    public List<Student> findAllStudents() {
        String sql = "SELECT * FROM STUDENT";

        List<Student> studentList = jdbcTemplate.query(sql, new StudentRowMapper());

        return studentList;
    }

    @Override
    public void cleanUp() {
        String sql = "TRUNCATE TABLE STUDENT";
        jdbcTemplate.update(sql);
        System.out.println("Table cleaned up>>>>");
    }

/*
    @Override
    public Student findStudentByRollNo(int rollNo) {
        String sql = "SELECT * FROM STUDENT" +
                " WHERE ROLL_NO = ?";
        Student student = jdbcTemplate.queryForObject(sql, new StudentRowMapper(), rollNo);
        return student;
    }
*/

    /*
    Example of BeanPropertyRowMapper
     */
    @Override
    public Student findStudentByRollNo(int rollNo) {
        System.out.println("queryForObject() using BeanPropertyRowMapper default spring implementation!!");
        String sql = "SELECT ROLL_NO as rollNo," +
                " STUDENT_NAME as name," +
                " STUDENT_ADDRESS as address" +
                " FROM STUDENT" +
                " WHERE ROLL_NO = ?";
        Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), rollNo);
        return student;
    }


    @Override
    public List<Student> findStudentByName(String name) {
        String sql = "SELECT * FROM STUDENT WHERE STUDENT_NAME = ?";

        List<Student> students = jdbcTemplate.query(sql, new StudentResultSetExtractor(), name);

        return students;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
