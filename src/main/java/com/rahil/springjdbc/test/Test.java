package com.rahil.springjdbc.test;

import com.rahil.springjdbc.api.Student;
import com.rahil.springjdbc.dao.StudentDAO;
import com.rahil.springjdbc.dao.StudentDAOImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
//        StudentDAO studentDAO = new StudentDAOImpl();
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("Application context is loaded");

        StudentDAOImpl studentDAO = context.getBean("studentDAO", StudentDAOImpl.class);

        Student newStudent1 = new Student();
        newStudent1.setRollNo(003);
        newStudent1.setName("Eminem");
        newStudent1.setAddress("Mart");
        studentDAO.insert(newStudent1);
//        studentDAO.insert(newStudent1);


    }
}
