package com.rahil.springjdbc.dao;

import com.rahil.springjdbc.domain.Student;
import com.rahil.springjdbc.resultsetextractor.StudentResultSetExtractor;
import com.rahil.springjdbc.rowmapper.StudentRowMapper;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

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

    @Override
    public void groupByAddress() {
        Map<String, ArrayList<String>> studentMap = new HashMap<>();
        String sql = "SELECT * FROM STUDENT";
        List<Student> query = jdbcTemplate.query(sql, new StudentResultSetExtractor());
        for (Student temp : query) {
            if (studentMap.containsKey(temp.getAddress())) {
                studentMap.get(temp.getAddress()).add(temp.getName());
            } else {
                ArrayList<String> name = new ArrayList<>();
                name.add(temp.getName());
                studentMap.put(temp.getAddress(), name);
            }
        }
        studentMap.forEach((key,value) -> System.out.println(key + " :: { " + value + " }"));
        System.out.println(studentMap);
    }

    @Override
    public int updateStudent(Student student) {
        String sql = "UPDATE STUDENT SET STUDENT_ADDRESS = ?" +
                " WHERE ROLL_NO = ?";

        Object[] arg = {student.getAddress(), student.getRollNo()};

        return jdbcTemplate.update(sql, arg);
    }


    @Override
    public void updateStudent(List<Student> students) {
        // This is my prepared statement
        // Which will send to the db by application then db will compile this ps
        // that compiled ps returns and execute the queries
        String sql = "UPDATE STUDENT SET STUDENT_ADDRESS = ?" +
                " WHERE ROLL_NO = ?";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                // we need to set the args for the prepared statement

                ps.setString(1, students.get(i).getAddress());
                ps.setInt(2, students.get(i).getRollNo());
            }

            @Override
            public int getBatchSize() {
                // in this method we need to define how many times our query will execute
                // how many times the setValues() is going to execute?
                return students.size();
            }
        });
/*
        List<Object[]> args = new ArrayList<>();

        for (Student temp : students) {
            Object[] tempArg = {temp.getAddress(), temp.getRollNo()};
            args.add(tempArg);
        }

        jdbcTemplate.batchUpdate(sql, args);

        System.out.println("Done");*/
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
