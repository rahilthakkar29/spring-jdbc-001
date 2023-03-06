package com.rahil.springjdbc.dao;

import com.rahil.springjdbc.domain.Student;

import java.util.List;

public interface StudentDAO {
    void insert(Student student);

    void insert(List<Student> students);

    List<Student> findAllStudents();

    void cleanUp();

    Student findStudentByRollNo(int rollNo);

    List<Student> findStudentByName(String name);

    void groupByAddress();

    int updateStudent(Student student);

    void updateStudent(List<Student> students);


}
