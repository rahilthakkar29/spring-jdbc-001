package com.rahil.springjdbc.dao;

import com.rahil.springjdbc.api.Student;

import java.util.List;

public interface StudentDAO {
    void insert(Student student);

    void insert(List<Student> students);


}
