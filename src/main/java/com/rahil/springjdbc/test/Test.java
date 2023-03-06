package com.rahil.springjdbc.test;

import com.rahil.springjdbc.domain.Student;
import com.rahil.springjdbc.dao.StudentDAOImpl;
import com.rahil.springjdbc.service.StudentDaoHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("Application context is loaded");

        /*
        Adding students
        */
        StudentDaoHelper studentDaoHelper = context.getBean("studentDaoHelper", StudentDaoHelper.class);
       // studentDaoHelper.setUpStudentTable();

        /*
        fetching all the students
         */
        //studentDaoHelper.showResult();

        /*
         fetch student using rollNo
         */
        //studentDaoHelper.printSingleStudent(2);

        /*
        fetch students with name using result set extractor
         */
        studentDaoHelper.queryUsingExtractor("John");

        /*
        Group by address call
         */
        studentDaoHelper.groupByAddress();


        /*
        Calling update method
         */
       /* Student john = new Student();
        john.setRollNo(2);
        john.setAddress("ON");
        studentDaoHelper.update(john);*/



        /*
        Batch update
         */
        Student john = new Student();
        john.setRollNo(2);
        john.setAddress("NY");
        studentDaoHelper.update(john);

        Student marry = new Student();
        marry.setRollNo(6);
        marry.setAddress("FRA");
        studentDaoHelper.update(marry);

        Student frank = new Student();
        frank.setRollNo(8);
        frank.setAddress("ON");
        studentDaoHelper.update(frank);

        List<Student> students = new ArrayList<>();
        students.add(john);
        students.add(marry);
        students.add(frank);

        studentDaoHelper.update(students);







        /*
         cleanup the table
         */
        //studentDaoHelper.cleanTable();


    }
}
