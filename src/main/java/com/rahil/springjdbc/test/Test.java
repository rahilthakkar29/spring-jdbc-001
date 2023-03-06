package com.rahil.springjdbc.test;

import com.rahil.springjdbc.domain.Student;
import com.rahil.springjdbc.dao.StudentDAOImpl;
import com.rahil.springjdbc.service.StudentDaoHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
         cleanup the table
         */
        //studentDaoHelper.cleanTable();


    }
}
