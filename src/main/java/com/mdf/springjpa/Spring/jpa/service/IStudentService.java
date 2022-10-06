package com.mdf.springjpa.Spring.jpa.service;



import java.util.List;

import com.mdf.springjpa.Spring.jpa.model.Student;

public interface IStudentService {
	
	 Student addStudent(Student student);
	 
	 Student findStudentByEmail(String email);

	 List<Student> retrieveAllStudent();
	 
	 Boolean updateStudent(Student student);
	 
	 Boolean removeStudent(Student student);
	 

}
