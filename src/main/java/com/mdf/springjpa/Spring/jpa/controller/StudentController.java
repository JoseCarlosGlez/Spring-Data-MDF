package com.mdf.springjpa.Spring.jpa.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdf.springjpa.Spring.jpa.model.Student;
import com.mdf.springjpa.Spring.jpa.service.IStudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private IStudentService _studentService;
	
	@PostMapping("/register")
	public ResponseEntity<Student> createNewStudent(@Valid @RequestBody Student student){
		
		Student studentAdded = this._studentService.addStudent(student);
	
		
		return new ResponseEntity<>(studentAdded,HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/byEmail")
	public ResponseEntity<Student> getStudentByEmail (
				@RequestParam(name="email",required = true) String email
			){
		Student getStudentByEmail= this._studentService.findStudentByEmail(email);
		return new ResponseEntity<>(getStudentByEmail,HttpStatus.OK) ;
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean>updateStudent(
			@Valid @RequestBody Student student
			){
		Boolean success= this._studentService.updateStudent(student);
		
		
		return new ResponseEntity<>(success, HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<?>RetieveAllStudents() throws IOException{
		List<Student> _students =this._studentService.retrieveAllStudent();
		System.out.println(_students);
		return new ResponseEntity<>(_students,HttpStatus.OK);
	}
	
	

}
