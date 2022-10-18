package com.mdf.springjpa.Spring.jpa.service;



import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.mdf.springjpa.Spring.jpa.model.Student;

public interface IStudentService {
	
	 Student addStudent(Student student);
	 
	 Student findStudentByEmail(String email);

	 List<Student> retrieveAllStudent() throws IOException;
	 
	 Boolean updateStudent(Student student);
	 
	 Boolean removeStudent(Student student);
	 

}
