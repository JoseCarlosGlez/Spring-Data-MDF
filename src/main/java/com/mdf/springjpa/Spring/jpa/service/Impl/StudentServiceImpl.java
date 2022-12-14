package com.mdf.springjpa.Spring.jpa.service.Impl;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdf.springjpa.Spring.jpa.model.Guardian;
import com.mdf.springjpa.Spring.jpa.model.Student;
import com.mdf.springjpa.Spring.jpa.repository.StudentRepository;
import com.mdf.springjpa.Spring.jpa.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	StudentRepository _studentRepository;
	
	@Autowired
	PasswordEncoder _passwordEncoder;
	
	@Override
	public Student addStudent(Student student) {
		// TODO Auto-generated method stub
		
		Guardian guardian = Guardian.builder()
								.name(student.getGuardian().getName())
								.email(student.getGuardian().getEmail())
								.mobile(student.getGuardian().getMobile())
								.build();
		
		Student bldStudent = Student.builder()
							.firstName(student.getFirstName())
							.lastName(student.getLastName())
							.emailId(student.getEmailId())
							.password(_passwordEncoder.encode( student.getPassword()))
							.role(student.getRole())
							.guardian(guardian)
							.build();
		this._studentRepository.save(bldStudent);
		return bldStudent;
	}

	@Override
	public Student findStudentByEmail(String email) {
		// TODO Auto-generated method stub
		return this._studentRepository.findByEmailId(email);
		
	}

	@Override
	public Boolean updateStudent(Student student) {
		// TODO Auto-generated method stub
		try {
			
			Guardian _guardian =  Guardian.builder()
									.name(student.getGuardian().getName())
									.email(student.getGuardian().getEmail())
									.mobile(student.getGuardian().getMobile())
									.build();
			
			Student studentUpdated =  Student.builder()
									.studentId(student.getStudentId())
									.firstName(student.getFirstName())
									.lastName(student.getLastName())
									.emailId(student.getEmailId())
									.guardian(_guardian)
									.build();
			
			
			this._studentRepository.save(studentUpdated);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	@Override
	public Boolean removeStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<Student> retrieveAllStudent() throws IOException {
		// TODO Auto-generated method stub
		try {
		List<Student> students =this._studentRepository.GET_ALL_STUDENTS();
		return students;
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
			// TODO: handle exception
		}
	}

}
