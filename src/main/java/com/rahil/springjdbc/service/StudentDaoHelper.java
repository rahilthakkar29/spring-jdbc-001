package com.rahil.springjdbc.service;

import com.rahil.springjdbc.dao.StudentDAO;
import com.rahil.springjdbc.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("studentDaoHelper")
public class StudentDaoHelper {

    @Autowired
    private StudentDAO studentDAO;

    public void setUpStudentTable() {
        Student student1 = new Student();
        student1.setRollNo(1);
        student1.setName("Rahil");
        student1.setAddress("ON");

        Student student2 = new Student();
        student2.setRollNo(2);
        student2.setName("John");
        student2.setAddress("NY");

        Student student3 = new Student();
        student3.setRollNo(3);
        student3.setName("Noura");
        student3.setAddress("AUS");

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        System.out.println(studentList);

        studentDAO.insert(studentList);
    }

    public void showResult() {
        List<Student> students = studentDAO.findAllStudents();
        students.forEach(System.out::println);
    }

    public void cleanTable() {
        studentDAO.cleanUp();
    }

    public void printSingleStudent(int rollNo) {
        Student studentByRollNo = studentDAO.findStudentByRollNo(rollNo);
        System.out.println(studentByRollNo);
    }

    public void queryUsingExtractor(String name) {
        List<Student> studentByName = studentDAO.findStudentByName(name);
        studentByName.forEach(System.out::println);
    }

    public void groupByAddress() {
        studentDAO.groupByAddress();
    }

    public void update(Student student) {
        studentDAO.updateStudent(student);
    }

    public void update(List<Student> students) {
        studentDAO.updateStudent(students);
    }
}
